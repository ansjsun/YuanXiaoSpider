package ansj.sun.spider.util.impl;

import java.util.List;

import ansj.sun.spider.pojo.Page;
import ansj.sun.spider.util.PageUtils;
import ansj.sun.util.HtmlPaser;

/**
 * 默认的网页内容抽取.这里用户可继承PageUtils 进行复写
 * @author ansj
 * @mail ansj-sun@163.com
 */
public class DefaultPageUtils extends PageUtils {


	@Override
	public Page paserPage(Page page) {
		// TODO Auto-generated method stub
		//将所有html正文变成小写
		String raw = page.getRaw().toLowerCase() ;
		
		//抽取title
		page.setTitle(HtmlPaser.getTitle(raw)) ;
		
		// 抽取网页中包含的链接
		List<String> urls = HtmlPaser.paserUrls(raw,page.getUrl());
		page.setUrls(urls);
		

		// 抽取网页中text正文
		page.setText(HtmlPaser.getExtractedText(raw));
		
		return page;
	}

}
