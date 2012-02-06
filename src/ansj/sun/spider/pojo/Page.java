package ansj.sun.spider.pojo;

import java.util.List;

/**
 * 封装了一个网页的实体对象
 * 
 * @author ansj
 * 
 */
public class Page {
	private String title ;
	private String url;
	private String raw;
	private String text;
	private long offsetRaw;
	private int offsetRawLength;
	private long offsetTxt;
	private int offsetTxtLength;

	private List<String> urls;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRaw() {
		return raw;
	}

	public void setRaw(String raw) {
		this.raw = raw;
	}

	public long getOffsetRaw() {
		return offsetRaw;
	}

	public void setOffsetRaw(long offsetRaw) {
		this.offsetRaw = offsetRaw;
	}

	public int getOffsetRawLength() {
		return offsetRawLength;
	}

	public void setOffsetRawLength(int offsetRawLength) {
		this.offsetRawLength = offsetRawLength;
	}

	public long getOffsetTxt() {
		return offsetTxt;
	}

	public void setOffsetTxt(long offsetTxt) {
		this.offsetTxt = offsetTxt;
	}

	public int getOffsetTxtLength() {
		return offsetTxtLength;
	}

	public void setOffsetTxtLength(int offsetTxtLength) {
		this.offsetTxtLength = offsetTxtLength;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<String> getUrls() {
		return urls;
	}

	public void setUrls(List<String> urls) {
		this.urls = urls;
	}
	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public static final String LINE = System.getProperty("line.separator");

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<page>");
		sb.append(LINE);
		sb.append("<title>");
		sb.append(title);
		sb.append("</title>");
		sb.append(LINE);
		sb.append("<url>");
		sb.append(url);
		sb.append("</url>");
		sb.append(LINE);
		sb.append("<offsetRaw>");
		sb.append(offsetRaw);
		sb.append("</offsetRaw>");
		sb.append(LINE);
		sb.append("<offsetRawLength>");
		sb.append(offsetRawLength);
		sb.append("</offsetRawLength>");
		sb.append(LINE);
		sb.append("<offsetTxt>");
		sb.append(offsetTxt);
		sb.append("</offsetTxt>");
		sb.append(LINE);
		sb.append("<offsetTxtLength>");
		sb.append(offsetTxtLength);
		sb.append("</offsetTxtLength>");
		sb.append(LINE);
		sb.append("<text>");
		sb.append(text);
		sb.append("</text>");
		sb.append(LINE);
		sb.append("</page>");
		sb.append(LINE);
		return sb.toString();
	}
}
