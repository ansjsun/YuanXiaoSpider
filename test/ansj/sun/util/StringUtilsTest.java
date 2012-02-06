package ansj.sun.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringUtilsTest {

	@Test
	public void test() {
		assertTrue(StringUtils.isBlank("")) ;
		assertTrue(StringUtils.isBlank(" ")) ;
		assertTrue(StringUtils.isBlank("  ")) ;
		assertTrue(StringUtils.isBlank(" ")) ;
		assertTrue(StringUtils.isBlank(null)) ;
	}

}
