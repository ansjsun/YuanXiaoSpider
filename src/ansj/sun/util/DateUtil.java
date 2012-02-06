package ansj.sun.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public static final SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");

	private static final long DATETIME = 24*60*60*1000L; 
	
	/**
	 * 第二天 零时的毫秒数
	 * @return
	 */
	public static long getDateZeroMillis(){
		try {
			return yyyyMMdd.parse(yyyyMMdd.format(new Date(System.currentTimeMillis()+DATETIME))).getTime() ;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0L ;
	} 
}
