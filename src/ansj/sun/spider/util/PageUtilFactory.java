package ansj.sun.spider.util;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import ansj.sun.spider.pojo.Configure;
import ansj.sun.spider.util.impl.DefaultPageUtils;
import ansj.sun.spider.util.impl.NewsListPageUtils;
import ansj.sun.spider.util.impl.NewsPageUtils;

public class PageUtilFactory {
	
	private static List<Configure> all = null ;
	
	public static PageUtils create(String url){
		if(all!=null){
			Configure conf = null ;
			for (int i = 0; i < all.size(); i++) {
				conf = all.get(i) ;
				if(Pattern.matches(conf.getListUrlRegex(), url)){
					return new NewsListPageUtils(conf) ;
				}
				if(Pattern.matches(conf.getUrlRegex(), url)){
					return new NewsPageUtils(conf) ;
				}
			}
		}
		return new DefaultPageUtils();
	}
	
	public static void addConfigure(Configure config){
		if(all == null){
			all = new ArrayList<Configure>() ;
		}
		all.add(config) ;
	}
}
