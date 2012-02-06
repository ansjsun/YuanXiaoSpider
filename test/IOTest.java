import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

public class IOTest {
	public static void main(String[] args) throws IOException {
		FileInputStream fis = new FileInputStream(new File("D:\\data\\url.txt")) ;
		InputStreamReader isr = new InputStreamReader(
				fis) ;
		LineNumberReader lnr = new LineNumberReader(isr);
		
		
		System.out.println(System.getProperty("file.encoding"));
		
	}
}
