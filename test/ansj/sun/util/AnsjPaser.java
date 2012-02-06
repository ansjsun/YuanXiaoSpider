package ansj.sun.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnsjPaser {

	private String beginRegex;

	private String endRegex;

	private Matcher matcher;

	public final static String TEXTTEGEX = "(.*?)";

	public final static String W = "\\W*?";

	public final static String N = "";

	public final static String TEXTEGEXANDNRT = "[\\s\\S]*?";

	public String regexStr;

	private List<String> filterRegexList = new ArrayList<String>();

	/**
	 * 
	 * @param beginRegex
	 *            璧峰姝ｅ垯
	 * @param endRegex
	 *            缁撴潫姝ｅ垯
	 * @param content
	 *            闇�瑙ｆ瀽鐨勬鏂�濡傛灉娌℃湁姝ら」蹇呴』涓簉est璁剧疆)
	 * @param textRegex
	 *            鍏跺疄鍜岀粨鏉熸鍒欎腑闂寸殑閮ㄥ垎,榛樿涓�*?
	 */
	public AnsjPaser(String beginRegex, String endRegex, String content,
			String textRegex) {

		this.beginRegex = beginRegex;

		this.endRegex = endRegex;

		StringBuilder sb = new StringBuilder();

		sb.append(beginRegex);

		sb.append(textRegex);

		sb.append(endRegex);

		regexStr = sb.toString();

		matcher = Pattern.compile(regexStr).matcher(content);
	}

	/**
	 * 
	 * @param beginRegex
	 *            璧峰姝ｅ垯
	 * @param endRegex
	 *            缁撴潫姝ｅ垯
	 * @param textRegex
	 *            鍏跺疄鍜岀粨鏉熸鍒欎腑闂寸殑閮ㄥ垎,榛樿涓�*?
	 */
	public AnsjPaser(String beginRegex, String endRegex, String textRegex) {

		this.beginRegex = beginRegex;

		this.endRegex = endRegex;

		StringBuilder sb = new StringBuilder();

		sb.append(beginRegex);

		sb.append(textRegex);

		sb.append(endRegex);

		regexStr = sb.toString();

		matcher = Pattern.compile(regexStr).matcher(N);
	}

	/**
	 * @param beginRegex
	 *            璧峰姝ｅ垯
	 * @param endRegex
	 *            缁撴潫姝ｅ垯
	 */
	public AnsjPaser(String beginRegex, String endRegex) {

		this.beginRegex = beginRegex;

		this.endRegex = endRegex;

		StringBuilder sb = new StringBuilder();

		sb.append(beginRegex);

		sb.append(TEXTTEGEX);

		sb.append(endRegex);

		regexStr = sb.toString();

		matcher = Pattern.compile(regexStr).matcher(N);
	}

	/**
	 * @鍒涘缓浜猴細Ansj -鍒涘缓鏃堕棿锛�011-8-16 涓嬪崍09:30:56
	 * @鏂规硶鎻忚堪锛�@return 
	 *                  杩斿洖姝ｅ垯鍐呯殑鍐呭鍘婚櫎浜嗗紑濮嬪拰缁撴潫鏍囩,鍜岄渶瑕佽繃婊ょ殑姝ｅ垯杩斿洖鐢ㄦ埛闇�鐨勭湡姝ｇ殑鍐呭
	 *                  
	 */
	public String getText() {
		if (matcher.find()) {
			String str = matcher.group().trim().replaceFirst(beginRegex, N)
					.replaceAll(endRegex, N);
			Iterator<String> it = filterRegexList.iterator();
			while (it.hasNext()) {
				str = str.replaceAll(it.next(), N);
			}
			return str;
		}
		return null;
	}

	public String getText(String content) {
		Matcher matcher = Pattern.compile(regexStr).matcher(content);
		if (matcher.find()) {
			String str = matcher.group().trim().replaceFirst(beginRegex, N)
					.replaceAll(endRegex, N);
			Iterator<String> it = filterRegexList.iterator();
			while (it.hasNext()) {
				str = str.replaceAll(it.next(), N);
			}
			return str;
		}
		return null;
	}
	
	public String getHtml(String content) {
		Matcher matcher = Pattern.compile(regexStr).matcher(content);
		if (matcher.find()) {
			return matcher.group();
		}
		return null ;
	}

	/*
	 * 寰楀埌涓嬩竴涓�
	 */
	public String getNext() {
		return matcher.group();
	}

	/*
	 * 鏄惁鍖呭惈涓嬩竴涓�
	 */
	public boolean hasIn() {
		return matcher.find();
	}

	/**
	 * @鍒涘缓浜猴細Ansj -鍒涘缓鏃堕棿锛�011-8-17 涓婂崍12:11:12
	 * @鏂规硶鎻忚堪锛� @param content 闇�瑙ｆ瀽鐨勬鏂�
	 * @鏂规硶鎻忚堪锛� @return 杩斿洖鏈韩 杩欎釜鏂规硶鏄皢姝よВ鏋愬櫒閲嶇疆,鐩稿綋浜庨噸澶村紑濮�浣嗘槸涓�簺姝ｅ垯閰嶇疆缁欎簣淇濈暀
	 */
	public AnsjPaser reset(String content) {
		this.matcher.reset(content);
		return this;
	}

	/*
	 * 娣诲姞getText鐨勬鍒欒繃婊ゆ潯浠�
	 */
	public AnsjPaser addFilterRegex(String filterRegex) {
		filterRegexList.add(filterRegex);
		return this;
	}

}
