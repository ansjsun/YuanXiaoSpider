package ansj.sun.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.junit.Test;

import ansj.sun.spider.util.impl.DefaultPageUtils;

public class PageUtilsTest {

	@Test
	public void test() {
		String str;
		try {
			URL url = new URL("http://view.news.qq.com/");
			URLConnection conn = url.openConnection() ;
			System.out.println(conn.getContentType());
			System.out.println(conn.getContentEncoding());
			
			BufferedReader br = IOUtil.getBufferedReader(conn.getInputStream());

			StringBuilder sb = new StringBuilder();
			String temp = null;
			while ((temp = br.readLine()) != null) {
				sb.append(temp);
			}
			//System.out.println(sb);
			str = HtmlPaser.getPageEncoding(sb.toString());
			System.out.println(str);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
