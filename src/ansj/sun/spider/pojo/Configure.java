package ansj.sun.spider.pojo;

import java.util.HashMap;
import java.util.Map;

import ansj.sun.util.AnsjPaser;

/**
 * 正则规则的实体类
 * @author ansj
 * @mail ansj-sun@163.com
 */
public class Configure{
	
	private String listUrlRegex ;
	private String urlRegex ;
	private static final String TEXT = "ansjtext" ;
	
	private Map<String , AnsjPaser> fieldMap = new HashMap<String , AnsjPaser>() ;
	
	
	public void put(String name , String value){
		
		if("listUrlRegex".equals(name)){
			this.listUrlRegex = value ;
			return ;
		}
		if("urlRegex".equals(name)){
			this.urlRegex = value ;
			return ;
		}
		
		value = value.toLowerCase() ;
		
		String[] strs = value.split(TEXT) ;
		
		AnsjPaser ap = new AnsjPaser(strs[0],strs[1]) ;
		
		fieldMap.put(name, ap) ;
	}
	
	public AnsjPaser getPaser(String name){
		return fieldMap.get(name) ;
	}

	public String getListUrlRegex() {
		return listUrlRegex;
	}

	public void setListUrlRegex(String listUrlRegex) {
		this.listUrlRegex = listUrlRegex;
	}

	public String getUrlRegex() {
		return urlRegex;
	}

	public void setUrlRegex(String urlRegex) {
		this.urlRegex = urlRegex;
	}
	
}

