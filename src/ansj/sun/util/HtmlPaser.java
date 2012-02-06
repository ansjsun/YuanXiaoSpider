package ansj.sun.util;

import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * 替换了htmlpaser的几个功能
 * 
 * @author ansj
 * @mail ansj-sun@163.com
 */
public class HtmlPaser {

	private static final char C = '/';
	private static final char D = '.';
	private static final char J = '#';
	private static final String CHARSET = "charset\\s*+=";
	public static final String ISO = "ISO-8859-1";

	/**
	 * 抽取网页的正文
	 * 
	 * @author ansj
	 * @mail ansj-sun@163.com
	 * @param page
	 * @return
	 */
	public static String getExtractedText(String raw) {
		AnsjPaser ap = new AnsjPaser("<body.*?>", "</body>", raw,
				AnsjPaser.TEXTEGEXANDNRT);
		String temp = ap.getText();
		if (StringUtils.isBlank(temp)) {
			temp = raw;
		}
		temp = temp.replaceAll("<!--[\\s\\S]*?-->", "")
				.replaceAll("<script[\\s\\S]*?</script>", "")
				.replaceAll("<style[\\s\\S]*?</style>", "")
				.replaceAll("<[\\s\\S]*?>", " ").replace("&nbsp;", " ")
				.replace("&gt;", " ").replaceAll("\\s+", " ");
		return temp;
	}

	/**
	 * 抽取网页的标题
	 * 
	 * @author ansj
	 * @mail ansj-sun@163.com
	 * @param raw
	 * @return
	 */
	public static String getTitle(String raw) {
		AnsjPaser ap = new AnsjPaser("<title>", "</title>", raw,
				AnsjPaser.TEXTEGEXANDNRT);
		String temp = ap.getText();
		if (StringUtils.isNotBlank(temp)) {
			return temp.trim();
		}
		return null;
	}

	/**
	 * 抽取网址
	 * 
	 * @author ansj
	 * @mail ansj-sun@163.com
	 * @param parser
	 * @param page
	 * @return
	 */
	public static List<String> paserUrls(String raw, String baseUrl) {
		String url = null;
		List<String> urls = new ArrayList<String>();
		AnsjPaser ap = new AnsjPaser("<a\\s+.*?href\\s*=\\s*[\"']*",
				"[\"'# >]{1}");
		ap.reset(raw);
		while ((url = ap.getText()) != null) {
			// 如果网址为空则跳过
			if (StringUtils.isBlank(url)) {
				continue;
			}
			// 如果不是以http开头则调整为标准形式
			if (!url.toLowerCase().startsWith("http")) {
				url = makeURL(baseUrl, url);
			}
			if (url != null)
				urls.add(url);
		}
		return urls;
	}

	/**
	 * 对飞标准化的url标准化
	 * 
	 * @author ansj
	 * @mail ansj-sun@163.com
	 * @param baseUrl
	 * @param url
	 * @return
	 */
	private static String makeURL(String baseUrl, String url) {

		if (url.startsWith("mailto") || url.startsWith("javascript")) {
			return null;
		}
		switch (url.charAt(0)) {
		case C:
			int count = 0;
			for (int i = 0; i < baseUrl.length(); i++) {
				if (baseUrl.charAt(i) == C) {
					count++;
					if (count == 3) {
						baseUrl = baseUrl.substring(0, i);
						break;
					}
				}
			}
			return baseUrl += url;
		case D:
			int num = (url.length() - url.replace("../", "").length()) / 3;

			for (int i = baseUrl.length() - 1; i > 8; i--) {
				if (baseUrl.charAt(i) == C) {
					baseUrl = baseUrl.substring(0, i + 1);
					if (num > 0) {
						num--;
					} else {
						return baseUrl += url.replace("./", "");
					}
				}
			}
		default:
			for (int i = baseUrl.length() - 1; i > 8; i--) {
				if (baseUrl.charAt(i) == C) {
					baseUrl = baseUrl.substring(0, i + 1);
					return baseUrl += url;
				}
			}
			break;
		}

		return baseUrl + C + url;
	}

	/**
	 * 取得网页的编码.
	 * 
	 * @author ansj
	 * @mail ansj-sun@163.com
	 * @param conn
	 * @return
	 */
	public static String getPageEncoding(URLConnection conn) {
		// TODO Auto-generated method stub

		String contentEncoding = null;

		contentEncoding = conn.getContentEncoding();

		if (StringUtils.isNotBlank(contentEncoding)
				&& !"none".equals(contentEncoding)) {
			return contentEncoding;
		}

		String contentType = conn.getContentType();

		if (StringUtils.isNotBlank(contentType)) {
			String[] str = contentType.toLowerCase().split(CHARSET);

			if (str.length == 2) {
				contentEncoding = str[1].trim();
			}
		}

		if (StringUtils.isBlank(contentEncoding) || "gzip".equals(contentType)) {
			return ISO ;
		}

		return contentEncoding;
	}

	public static String getPageEncoding(String content) {
		// TODO Auto-generated method stub
		AnsjPaser ap = new AnsjPaser("charset=\\W*", "[\"'>\\s]");
		return ap.reset(content).getText();
	}
}
