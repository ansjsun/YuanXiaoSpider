import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;


public class Test {

	public static void main(String[] args) throws  IOException {
//		URL url = new URL("https://passport.baidu.com/?reg&tpl=mn");
//		URLConnection urlConn = url.openConnection() ;
//System.out.println(urlConn.getContentEncoding());
//System.out.println(urlConn.getContentType());
//		
//		InputStream is = urlConn.getInputStream();
//		BufferedReader br = new BufferedReader(new InputStreamReader(is,"ISO8859-1"));
//		StringBuilder sb = new StringBuilder();
//		String temp = null;
//		while ((temp = br.readLine()) != null) {
//			sb.append(temp) ;
//			sb.append("\n") ;
//		}
////		System.out.println(sb);
//		AnsjPaser ap = new AnsjPaser("charset=", "\"") ;
//		ap.reset(sb.toString()) ;
//		System.out.println(ap.getText());
		
//		File f = new File("D:\\data\\url.txt") ;
//		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f))) ;
//		String temp = null ;
//		HashSet hs = new HashSet() ;
//		int count = 0  ;
//		while((temp=br.readLine())!=null){
//			hs.add(temp) ;
//			count++ ;
//		}
//		System.out.println(hs.size());
//		System.out.println(count);
		
		
		String str = "<a title=\"下一页\" href=\"./index_2.shtml\">下一页" ;
		
		System.out.println(str);
		//String regex = "<a\\s+.*?href\\s*=\\s*[\"']*(.*?)[\"'# >]{1}" ;
		String regex = "<a\\s+.*?href\\s*=\\s*[\"']*.*?[\"'# >]{1}" ;
		
		System.out.println(str.replaceAll(regex, "")); ;
	}

}

