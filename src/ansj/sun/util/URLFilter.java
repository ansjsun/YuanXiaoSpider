package ansj.sun.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import ansj.sun.bloomFilter.BloomFilter;

public class URLFilter {
	private static BloomFilter bf = null;

	private static URLFilter instance = null;
	
	private static Pattern[] throughRegex ;
	private static Pattern[] filterRegex ;

	public static URLFilter getInstance(BloomFilter bf) {
		if (instance == null) {
			instance = new URLFilter(bf);
		}
		return instance;
	}

	private URLFilter(BloomFilter bf) {
		URLFilter.bf = bf;
	}

	/**
	 * 进行url验证判断url是否下载 . 如果url存在则返回false 否则返回true
	 * 
	 * @author ansj
	 * @mail ansj-sun@163.com
	 * @param url
	 * @return
	 */
	public boolean valite(String url) {
		if (bloomValite(url)) {
			return false;
		}
		return regexValite(url);
	}

	/**
	 * bloom过滤器进行验证
	 * 
	 * @param url
	 * @return true表示网址存在 false 表示网址不存在
	 */
	public boolean bloomValite(String url) {
		if (bf.contains(url)) {
			return true;
		}

		bf.add(url);
		return false;
	}
	
	
	/**
	 * 正则表达式验证. 
	 * @param url
	 * @return true 表示通过验证 false表示没有通过验证
	 */
	public boolean regexValite(String url){
		if(throughRegex==null||filterRegex==null){
			initRegexFile() ;
		}
		
		boolean flag = false ;
		
		if(throughRegex.length>0){
			for (int i = 0; i < throughRegex.length; i++) {
				if(throughRegex[i].matcher(url).matches()){
					flag = true ;
				}
			}
			
			if(!flag){
				return false ;
			}
		}
		
		
		for (int i = 0; i < filterRegex.length; i++) {
			if(filterRegex[i].matcher(url).matches()){
				return false ;
			}
		}
		
		return true ;
		
	}

	
	/**
	 * 加载正则文件
	 */
	public void initRegexFile(){
		try {
			
			File f = new File("regexFilter.txt") ;
			
			if(!f.isFile()||!f.canRead()){
				throughRegex = new Pattern[0] ;
				filterRegex = new Pattern[0] ;
			}
			
			
			BufferedReader br = IOUtil.getBufferedReader(f) ;
			
			List<Pattern> throughList = new ArrayList<Pattern>() ;
			List<Pattern> filterList = new ArrayList<Pattern>() ;
			
			String temp = null ;
			
			char c1 = '+' ;
			char c2 = '-' ;
			
			while((temp=br.readLine())!=null){
				if(c1==temp.charAt(0)){
					throughList.add(Pattern.compile(temp.substring(1))) ;
				}
				if(c2==temp.charAt(0)){
					filterList.add(Pattern.compile(temp.substring(1))) ;
				}
			}
			
			
			throughRegex = new Pattern[throughList.size()] ;
			filterRegex = new Pattern[filterList.size()] ;
			
			throughRegex = throughList.toArray(throughRegex) ;
			filterRegex = filterList.toArray(filterRegex) ;
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 根据网址的contentType进行页面下载
	 * @param contentType
	 * @return
	 */
	public static boolean pageDownValite(String contentType){
		if(contentType!=null&&contentType.toLowerCase().startsWith("text")){
			return true ;
		}
		return false; 
	}
}
