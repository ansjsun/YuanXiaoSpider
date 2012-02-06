package ansj.sun.spider.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import ansj.sun.spider.pojo.Page;
import ansj.sun.util.AnsjPaser;
import ansj.sun.util.HtmlPaser;
import ansj.sun.util.StringUtils;
import ansj.sun.util.URLFilter;

/**
 * 网页解析类 主要是分析HTML文件,最后取得一个一个的Page对象
 * 
 * @author ansj
 * 
 */
public abstract class PageUtils {

	private static final String LINE = "\n";

	private static final String CHARSET = "charset=";

	private static final int TIMEOUT = 60000;
	
	private static final AnsjPaser ap = new AnsjPaser("<meta .*? content=0;url=","[\"> ]{1}") ;

	/**
	 * 记录错误.如果超过5次就放弃本页
	 */
	private int pageErrorCount = 5 ;
	/**
	 * 下载网页取得page对象
	 * 
	 * @param urlStr
	 * @return
	 * @throws IOException
	 */
	public Page downPage(String urlStr) {
		Page page = new Page();
		page.setUrl(urlStr);
		try {
			URL url = new URL(urlStr);
			URLConnection conn = url.openConnection();
			//设置超时时间
			conn.setConnectTimeout(TIMEOUT) ;
			conn.setReadTimeout(TIMEOUT) ;
			//网址contenttype验证
			if(!URLFilter.pageDownValite(conn.getContentType())){
				return null ;
			}
			
			String charset = HtmlPaser.getPageEncoding(conn) ;
			BufferedReader br = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), HtmlPaser.ISO));
			StringBuilder sb = new StringBuilder();
			String temp = null;
			while ((temp = br.readLine()) != null) {
				sb.append(temp);
			}

			String content = sb.toString();

			if (content == null || content.length() < 100) {
				System.out.println(page.getUrl()
						+ " content less than 100 char give up it");
				return null;
			}

			if(charset==null||charset==HtmlPaser.ISO){
				charset = HtmlPaser.getPageEncoding(content) ;
			}
			
			//如果网页编码抽取失败
			if(StringUtils.isBlank(charset)){
					System.out.println(page.getUrl()
							+ " I can not get this page encoding!");
					return null;
			}
			
			try {
				content = new String(content.getBytes(HtmlPaser.ISO), charset);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				pageErrorCount-- ;
				if(pageErrorCount==0){
					return null ;
				}
				content = content.toLowerCase() ;
				urlStr = ap.getText(content) ;
				return this.downPage(urlStr) ;
			}
			
			page.setRaw(content);
			return paserPage(page);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println(page.getUrl());
		} 

		return null;
	}

	

	public abstract Page paserPage(Page page) ;
}
