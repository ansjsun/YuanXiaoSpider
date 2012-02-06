package ansj.sun.spider;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import ansj.sun.spider.io.SpiderIO;

public class URLManager {
	/**
	 * 待采集网址列表
	 */
	private static LinkedList<String> urls = new LinkedList<String>() ;
	
	/**
	 * 次类的对象锁
	 */
	private static final Lock lock = new ReentrantLock() ;
	
	/**
	 * 增加网址
	 * @param all
	 */
	public static void addList(List<String> all){
		try {
			lock.lock() ; 
			urls.addAll(all) ;
		}finally{
			lock.unlock() ;
		}
	}
	
	/**
	 * 获得一个网址
	 * @return
	 * @throws InterruptedException
	 */
	public static String poolUrl() throws InterruptedException{
		lock.lock() ;
		try {
			while(urls.size()==0){
				System.out.println("内存中网址采集完毕!");
				List<String> all = SpiderIO.getUrls() ;
				if(all!=null){
					urls.addAll(all) ;
				}
				Thread.sleep(1000) ;
			}
System.out.println(urls.size());
			return urls.pollFirst() ;
		}finally{
			lock.unlock() ;
		}
	}
	
	/**
	 * 返回当前在内存中的网址列表
	 */
	public static int getUrlsSize(){
		return urls.size() ;
	}
}
