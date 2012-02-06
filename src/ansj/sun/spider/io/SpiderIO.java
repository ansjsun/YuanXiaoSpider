package ansj.sun.spider.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import ansj.sun.bloomFilter.BloomFilter;
import ansj.sun.spider.URLManager;
import ansj.sun.spider.pojo.Configure;
import ansj.sun.spider.pojo.Page;
import ansj.sun.spider.util.PageUtilFactory;
import ansj.sun.util.DateUtil;
import ansj.sun.util.IOUtil;
import ansj.sun.util.StringUtils;
import ansj.sun.util.URLFilter;

/*
 * 将采集到的数据进行保存
 */
public class SpiderIO {
	public static String data = "data";
	private static URLFilter urlFilter = null;
	private static ResourceBundle rb = ResourceBundle.getBundle("config");
	
	private static final String LINE = "\n";
	private static FileChannel txtFc;
	private static FileChannel rawFc;
	private static FileChannel urlFc;
	private static FileChannel pageFc;
	private static String lineNumPath;
	private static LineNumberReader urlBr;
	private static File urlFile = null;
	private static Lock urlLock = new ReentrantLock();
	private static int num = 1;
	private static final long maxFileSize = 20L * 1024 * 1024L;
	private static String dateStr;

	static {

		initSystemData() ;
		
		String temp = null ;

		// 保持上一次读取的行数
		lineNumPath = data + "/line.txt";

		// 加载网页定向规则
		initConfigeureXml();

		// 网页网址
		urlFile = new File(data + "/url.txt");
		try {
			if (!urlFile.isFile()) {
				urlFile.createNewFile();
			}
			urlBr = new LineNumberReader(new InputStreamReader(
					new FileInputStream(urlFile)));

			// 将urlBr调整到合适的位置
			int lineNum = 0;
			File lineNumFile = new File(lineNumPath);
			if (lineNumFile.isFile()) {
				BufferedReader br = IOUtil.getBufferedReader(lineNumFile);
				data = br.readLine();
				if (temp != null && temp.length() > 0) {
					lineNum = Integer.parseInt(temp);
				}
				for (int i = 0; i < lineNum; i++) {
					urlBr.readLine();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println(data + "/url.txt 没有建立!");
			System.exit(-1);
		}

	}
	
	/**
	 * 加载系统文件夹
	 * @return
	 */
	public static String initSystemData(){
		String temp = rb.getString("data");
		if (StringUtils.isNotBlank(temp)) {
			data = temp;
		}
		File f = new File(data);
		if (!f.isDirectory()) {
			System.err.println("系统配置文件是"+data+" 这个目录没有建立.系统已经自动建立.目录下要求包含url.txt文件.存放种子文件");
			f.mkdirs() ;
			System.exit(-1) ;
		}
		System.out.println("data dir is " + data);
		return data ;
	}

	
	/**
	 * 初始化系统文件.
	 * 
	 * @author ansj
	 * @mail ansj-sun@163.com
	 * @throws IOException
	 */
	public static synchronized void initFileSystem() throws IOException {
		if (System.currentTimeMillis()<dateZero) {
			return  ;
		}
		//重新设置系统文件路径
		resetSystemFile()  ;
		
		// url写入通道
		if(urlFc==null){
			urlFc = new FileOutputStream(urlFile, true).getChannel();
		}
		// 加载bloomfilter
		if(urlFilter==null){
			BufferedReader urlBrTemp = new BufferedReader(
					new InputStreamReader(new FileInputStream(urlFile)));
			BloomFilter bf = new BloomFilter(32);
			String temp = null;
			while ((temp = urlBrTemp.readLine()) != null) {
				bf.add(temp);  
			}
			urlFilter = URLFilter.getInstance(bf);

			urlBrTemp.close();
		}

	}
	
	//第二天零点
	private static long dateZero;
	/**
	 * 重新设置系统文件的路径
	 * @throws IOException 
	 */
	private static void resetSystemFile() throws IOException{
		
		num = 1 ;
		
		Date date = new Date();

		dateStr = DateUtil.yyyyMMdd.format(date);

		// 网页源码
		File rawFile = new File(data + "/" + dateStr + "/" + dateStr + ".raw");

		if (!rawFile.isFile()) {
			rawFile.getParentFile().mkdirs() ;
			rawFile.createNewFile();
		}
		if(rawFc!=null){
			rawFc.close() ;
			rawFc = null ;
		}
		rawFc = new FileOutputStream(rawFile, true).getChannel();

		// 抽取的文本
		File txtFile = new File(data + "/" + dateStr + "/" + dateStr + ".txt");
		if (!txtFile.isFile()) {
			txtFile.createNewFile();
		}
		if(txtFc!=null){
			txtFc.close() ;
			txtFc = null ;
		}
		txtFc = new FileOutputStream(txtFile, true).getChannel();

		// 页面对象存储
		File pageFile = new File(data + "/" + dateStr + "/" + dateStr + "-" + num
				+ ".xml");
		while (pageFile.isFile() && pageFile.length() >= maxFileSize) {
			num++;
			pageFile = new File(data + "/" + dateStr + "/" + dateStr + "-" + num
					+ ".xml");
		}
		if (!pageFile.isFile()) {
			pageFile.createNewFile();
		}
		if(pageFc!=null){
			pageFc.close() ;
			pageFc = null ;
		}
		pageFc = new FileOutputStream(pageFile, true).getChannel();
		
		
		dateZero = DateUtil.getDateZeroMillis() ;
	}

	/**
	 * 加载配置的定向文件
	 * 
	 * @author ansj
	 * @mail ansj-sun@163.com
	 */
	private static void initConfigeureXml() {
		File f = new File(data + "/wrapper");
		if (!f.isDirectory()) {
			return;
		}
		File[] files = f.listFiles();
		String name = null;
		for (int i = 0; i < files.length; i++) {
			name = files[i].getName();
			if (name.startsWith("news-") && name.endsWith(".xml")) {
				Configure conf = (Configure) ConfigureXml
						.makeConfigure(files[i].getAbsolutePath());
				PageUtilFactory.addConfigure(conf);
			}
		}
	}

	public static void savePage(Page page) throws IOException {

		if (page == null) {
			return;
		}
		// 加载系统文件
		initFileSystem();
		// 将网页源码写入
		writeRaw(page);
		// 将抽取到的正文写入
		writeTxt(page);
		// 将对象写到文件
		writePage(page);
		// 将url进行保存
		addUrls(page);
	}

	private static synchronized void writePage(Page page) {
		// TODO Auto-generated method stub
		try {
			IOUtil.write(pageFc, page.toString());
			// 当文件达到上限.重新建立一个文件
			if (pageFc.position() >= maxFileSize) {
				num++;
				pageFc.close();
				File pageFile = new File(data + "/" + dateStr+ "/" + dateStr + "-" + num
						+ ".xml");
				if (!pageFile.isFile()) {
					pageFile.createNewFile();
				}
				pageFc = new FileOutputStream(pageFile, true).getChannel();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 增加网址
	 * 
	 * @author ansj
	 * @mail ansj-sun@163.com
	 * @param page
	 */
	private static void addUrls(Page page) {
		try {

			List<String> all = page.getUrls();

			urlLock.lock();

			if (all == null || all.size() == 0) {
				return;
			}

			String url = null;

			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < all.size(); i++) {
				url = all.get(i);
				if (urlFilter.valite(url)) {
					sb.append(url);
					sb.append(LINE);
				} else {
					//System.out.println(url);
				}
			}

			ByteBuffer buffer = ByteBuffer.wrap(sb.toString().getBytes());
			try {
				urlFc.write(buffer);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} finally {
			urlLock.unlock();
		}

	}

	/**
	 * 取得需要采集的url
	 * 
	 * @author ansj
	 * @mail ansj-sun@163.com
	 * @return
	 */
	public synchronized static List<String> getUrls() {

		List<String> all = new ArrayList<String>();
		String temp = null;
		try {
			urlLock.lock();
			while (all.size() < 2000 && (temp = urlBr.readLine()) != null) {
				all.add(temp);
			}

			// 保存当前读取到的文本行
			IOUtil.write(lineNumPath, urlBr.getLineNumber() + "");
			urlLock.unlock();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return all;

	}

	/**
	 * 将文件写入到raw
	 * 
	 * @author ansj
	 * @mail ansj-sun@163.com
	 * @param fc
	 * @param content
	 */
	private static synchronized void writeRaw(Page page) {
		try {
			String raw = page.getRaw();
			if (StringUtils.isBlank(raw)) {
				return;
			}
			int length = raw.getBytes().length;
			long position = rawFc.size();
			IOUtil.write(rawFc, raw);
			page.setOffsetRaw(position);
			page.setOffsetRawLength(length);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 将文件写入到txt
	 * 
	 * @author ansj
	 * @mail ansj-sun@163.com
	 * @param fc
	 * @param content
	 */
	private static synchronized void writeTxt(Page page) {
		try {
			String text = page.getText();
			if (StringUtils.isBlank(text)) {
				return;
			}
			int length = text.getBytes().length;
			long position = txtFc.size();
			IOUtil.write(txtFc, text);
			page.setOffsetTxt(position);
			page.setOffsetTxtLength(length);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 关闭所有的文件流
	 * 
	 * @author ansj
	 * @mail ansj-sun@163.com
	 */
	public static synchronized void shoutDown() {
		try {
			rawFc.close();
			txtFc.close();
			pageFc.close();
			urlFc.close();
			// 更新列表行数
			IOUtil.write(lineNumPath,
					(urlBr.getLineNumber() - URLManager.getUrlsSize()) + "");
			urlBr.close();
			System.out.println("文件流关闭完毕");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
