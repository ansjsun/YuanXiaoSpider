package ansj.sun.util.impl;

import org.junit.Test;

import ansj.sun.spider.pojo.Page;
import ansj.sun.spider.util.impl.DefaultPageUtils;

public class DefaultPageUtilsTest {

	@Test
	public void test() {
		try {
			Page page = new DefaultPageUtils().downPage("http://www.baidu.com/search/favo/help.html/") ;
			System.out.println(page.getRaw());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testUrl(){
		DefaultPageUtils dpu = new DefaultPageUtils() ;
		Page page = dpu.downPage("http://www.sina.com.cn/intro/lawfirm.shtml") ;
		System.out.println(page);
//		String url = dpu.makeURL("http://www.sina.com.cn/sdfdfsdfd/sdf/sdf/e", "../../more/") ;
//		System.out.println(url);
	}
}
