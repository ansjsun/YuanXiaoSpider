

import static org.junit.Assert.*;

import org.junit.Test;


public class IOUtilTest {

	@Test
	public void test() {
		System.out.println("http://www.sina.com.cn/".replaceAll("+^http://([\\w])sina.com.*", "aaa"));
	}

}
