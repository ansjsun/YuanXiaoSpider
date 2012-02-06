package ansj.sun.spider.util.impl;

import java.util.List;

import ansj.sun.spider.pojo.Configure;
import ansj.sun.spider.pojo.Document;
import ansj.sun.spider.pojo.Page;
import ansj.sun.spider.util.PageUtils;
import ansj.sun.util.HtmlPaser;

public class NewsListPageUtils extends PageUtils{
	
	private Configure conf ;
	
	public NewsListPageUtils(Configure conf){
		this.conf = conf ;
	}

	@Override
	public Page paserPage(Page page) {
		// TODO Auto-generated method stub
		String raw = page.getRaw().toLowerCase();
		
		Document doc = new Document(page) ;
		//抽取文章列表块
		//抽取列表块内的网址
		String block = conf.getPaser("urlBlock").getHtml(raw) ;
		List<String> urls = HtmlPaser.paserUrls(block, page.getUrl()) ;
		
		//抽取下一页
		String nextPage = conf.getPaser("nextPage").getHtml(raw) ;
		List<String> all = HtmlPaser.paserUrls(nextPage, page.getUrl()) ;
		
		urls.addAll(all) ;
		
		doc.setUrls(urls) ;

		//清空正文.意思就是不保存了
		page.setRaw(null) ;
		
		return doc;
	}

}
