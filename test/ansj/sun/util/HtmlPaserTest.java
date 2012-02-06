package ansj.sun.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.junit.Test;

public class HtmlPaserTest {

	@Test
	public void getExtractedTextTest() {
		try {
			BufferedReader br = IOUtil.getBufferedReader(new URL("http://news.baidu.com/ns?word=浙江&tn=news&from=news&cl=2&rn=20&ct=1").openStream(),"UTF-8") ;
			String temp = null ;
			StringBuilder sb = new StringBuilder() ;
			while((temp=br.readLine())!=null){
				sb.append(temp) ;
			}
			System.out.println(sb);
			long start = System.currentTimeMillis() ;
			String txt = HtmlPaser.getExtractedText(sb.toString()) ;
			System.out.println(txt);
			System.out.println(System.currentTimeMillis()-start);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void paserUrlsTest() {
		try {
			BufferedReader br = IOUtil.getBufferedReader("C:\\Users\\ansj\\Desktop\\test.txt","GBK") ;
			String temp = null ;
			StringBuilder sb = new StringBuilder() ;
			while((temp=br.readLine())!=null){
				sb.append(temp) ;
			}
			long start = System.currentTimeMillis() ;
			HtmlPaser.paserUrls(sb.toString(), "http://www.qq.com") ;
			System.out.println(System.currentTimeMillis()-start);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void getTitleTest(){
//		try {
//			URL url = new URL("http://www.qq.com/") ;
//			BufferedReader br = IOUtil.getBufferedReader(url.openStream(),"GBK") ;
//			String temp = null ;
//			StringBuilder sb = new StringBuilder() ;
//			while((temp=br.readLine())!=null){
//				sb.append(temp) ;
//			}
//			String txt = HtmlPaser.getTitle(sb.toString()) ;
//			System.out.println(txt);
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	@Test
	public void test(){
//		String str = "<script language=\"javascript\" type=\"text/javascript\" defer>dfasdfasou3u2u3rn2j38942374897sdfsdf</script>bbb" ;
//		System.out.println(str.replaceAll("<script[\\s\\S]*?</script>", "aaaa")); ;
		
//		AnsjPaser ap = new AnsjPaser("<a\\s+href\\s*=\\s*[\"']*", "[\"'>]{1}");
//		String str1 = "<a href = aaa></a>sdf" ;
//		String str2 = "<a href =  'aaa' ></a>sdf" ;
//		String str3 = "<a href=\"aaa\"></a>sdf" ;
//		System.out.println(ap.reset(str1).getText());
//		System.out.println(ap.reset(str2).getText());
//		System.out.println(ap.reset(str3).getText());
		
//		AnsjPaser ap = new AnsjPaser("[[<title>][<TITLE>]]", "[<title>][</TITLE>]", "<TITLE>百度搜索帮助中心</TITLE>",
//				AnsjPaser.TEXTEGEXANDNRT);
//		String temp = ap.getText();
//		System.out.println(temp);
		
		System.out.println("http://www.baidu.com/search/cang_tools.html#n4".replaceAll("#.*+", ""));
	}
	
	@Test
	public void pageEncodingTest() throws IOException{
		URL url = new URL("http://www.sina.com.cn/intro/lawfirm.shtml") ;
		URLConnection conn = url.openConnection(); 
		
		System.out.println(conn.getContentEncoding());
		System.out.println(conn.getContentType());
		
		System.out.println(HtmlPaser.getPageEncoding(conn));
		BufferedReader br = IOUtil.getBufferedReader(url.openStream(),"ISO8859-1") ;
		String temp = null ;
		StringBuilder sb = new StringBuilder() ;
		while((temp=br.readLine())!=null){
			sb.append(temp) ;
		}
		System.out.println(sb);
		System.out.println(HtmlPaser.getPageEncoding(sb.toString()));
		
		System.out.println(new String(sb.toString().getBytes(),"sdfsdf"));
	}
	
}
