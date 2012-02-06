package ansj.sun.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Pattern;

import org.junit.Test;

import ansj.sun.bloomFilter.BloomFilter;

public class URLFilterTest {

	@Test
	public void testValite() throws IOException {
		File f = new File("d:/data/url.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream(f)));

		String temp = null;
		URLFilter uf = URLFilter.getInstance(new BloomFilter(32));
		while ((temp = br.readLine()) != null) {
			System.out.println(uf.valite(temp));
			;
		}
	}

	@Test
	public void test() throws IOException {
		AnsjPaser ap = new AnsjPaser("charset=\\W*", "[\"'\\s]");
		URL url = new URL("http://tech.sina.com.cn/focus/sinahelp.shtml");
		URLConnection conn = url.openConnection() ;
		System.out.println(conn.getContentType());
		System.out.println(conn.getContentEncoding());
		
		BufferedReader br = IOUtil.getBufferedReader(conn.getInputStream());

		StringBuilder sb = new StringBuilder();
		String temp = null;
		while ((temp = br.readLine()) != null) {
			sb.append(temp);
		}
		System.out.println(sb);
		ap.reset(sb.toString());
		System.out.println(ap.getText());
	}
	
	@Test
	public void regexFilterTest(){
		String str = "http://mil.news.sina.com.cn/bbs/2012/0116/164693289.html" ;
		
		Pattern p = Pattern.compile("^http(s{0,1})://(\\w)*sina.com.cn.*?") ;
		
		System.out.println(p.matcher(str).matches());
	}

}
