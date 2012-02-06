package ansj.sun.spider.util.impl;

import java.util.List;

import ansj.sun.spider.pojo.Configure;
import ansj.sun.spider.pojo.Document;
import ansj.sun.spider.pojo.Page;
import ansj.sun.spider.util.PageUtils;
import ansj.sun.util.HtmlPaser;

/**
 * 新浪文章抽取
 * 
 * @author ansj
 * @mail ansj-sun@163.com
 */
public class NewsPageUtils extends PageUtils {
	private Configure conf;

	public NewsPageUtils(Configure conf) {
		this.conf = conf;
	}

	@Override
	public Page paserPage(Page page) {
		// TODO Auto-generated method stub
		// 将所有html正文变成小写
		String raw = page.getRaw().toLowerCase();
		
		Document doc = new Document(page) ;

		// 抽取title
		doc.setTitle(conf.getPaser("title").getText(raw));


		// 抽取网页中text正文
		doc.setText(conf.getPaser("content").getText(raw));
		
		//抽取发布时间
		doc.setPublishTime(conf.getPaser("publishTime").getText(raw)) ;

		//抽取作者
		doc.setAuthor(conf.getPaser("author").getText(raw)) ;
		
		return doc;
	}

}
