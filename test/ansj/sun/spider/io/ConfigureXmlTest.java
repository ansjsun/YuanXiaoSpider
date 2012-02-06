package ansj.sun.spider.io;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import ansj.sun.spider.pojo.Configure;
import ansj.sun.spider.pojo.Page;
import ansj.sun.spider.util.PageUtilFactory;
import ansj.sun.spider.util.PageUtils;

public class ConfigureXmlTest {

	@Test
	public void test() throws IOException {

//		String path = "d:/data//html.txt" ;
//		
//		String temp = null ;
//		
//		BufferedReader br = IOUtil.getBufferedReader(path,"GBK") ;
//		
//		temp=br.readLine() ;
//		
//		Configure conf = ConfigureXml.makeConfigure("D:/data/wrapper/news-mil.news.sina.com.cn.xml") ;
//		
//		AnsjPaser ap = conf.getPaser("urlBlock") ;
//		
//		System.out.println(ap.regexStr);
//		temp = temp.replaceAll("\n", " ") ;
//		System.out.println(temp);
//		
//		
//		temp = ap.getHtml(temp) ;
//		
//		System.out.println(temp);
		//System.out.println(HtmlPaser.paserUrls(temp, "http://www.baidu.com/"));
			
			//http://roll.mil.news.sina.com.cn/col/zgjq/./index_2.shtml
//		String str = "http://mil.news.sina.com.cn/2012-02-03/1009681422.html" ;
//		String str1 = "http://mil.news.sina.com.cn/2012-02-02/0936681365_2.html" ;
//		String regex = "http://mil.news.sina.com.cn/(p/)?+\\d{4}-\\d{2}-\\d{2}/\\d+(_?+)\\d+.html" ;
//		
//		System.out.println(str.replaceAll(regex, "a"));
//		System.out.println(str1.replaceAll(regex, "a"));
	}
	
	@Test
	public void xmlTest(){
		File f = new File("D:/data/wrapper");
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
		
		String urlStr = "http://mil.news.sina.com.cn/2012-02-04/1024681471.html" ;
		
		PageUtils pu = PageUtilFactory.create(urlStr) ;
		Page page = pu.downPage(urlStr) ;
		
		System.out.println(page);
	}
}
