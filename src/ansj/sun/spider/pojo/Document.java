package ansj.sun.spider.pojo;

public class Document extends Page {

	public Document(Page page){
		this.setUrl(page.getUrl()) ;
		this.setRaw(page.getRaw()) ;
	}

	private String publishTime;
	
	private String author;

	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<page>");
		sb.append(LINE);
		sb.append("<title>");
		sb.append(this.getTitle());
		sb.append("</title>");
		sb.append(LINE);
		sb.append("<url>");
		sb.append(this.getUrl());
		sb.append("</url>");
		sb.append(LINE);
		
		//新加的哦
		sb.append("<publishTime>");
		sb.append(this.getPublishTime());
		sb.append("</publishTime>");
		sb.append(LINE);
		sb.append("<author>");
		sb.append(this.getAuthor());
		sb.append("</author>");
		sb.append(LINE);
		
		sb.append("<offsetRaw>");
		sb.append(this.getOffsetRaw());
		sb.append("</offsetRaw>");
		sb.append(LINE);
		sb.append("<offsetRawLength>");
		sb.append(this.getOffsetRawLength());
		sb.append("</offsetRawLength>");
		sb.append(LINE);
		sb.append("<offsetTxt>");
		sb.append(this.getOffsetTxt());
		sb.append("</offsetTxt>");
		sb.append(LINE);
		sb.append("<offsetTxtLength>");
		sb.append(this.getOffsetTxtLength());
		sb.append("</offsetTxtLength>");
		sb.append(LINE);
		sb.append("<text>");
		sb.append(this.getText());
		sb.append("</text>");
		sb.append(LINE);
		sb.append("</page>");
		sb.append(LINE);
		return sb.toString();
	}
}
