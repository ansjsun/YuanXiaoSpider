package ansj.sun.spider.thread;

import ansj.sun.spider.pojo.Page;

/**
 * 线程工作抽象类，用来下载具体的网页
 * @author ansj
 *
 */
public abstract class Worker implements Runnable {
	
	
	/**
	 * 根据url下载网页
	 * @param url
	 * @return 返回网页的正文
	 */
	public abstract Page downPage(String url) ;
	
	/**
	 * 从url队列中取得一个url用来下载
	 * @return
	 */
	public abstract String getUrl()  ;
	
	
	protected boolean flag = true;
	/**
	 * 线程是否运行
	 * @param flag ture 继续 false 不运行了哦
	 */
	public void changeFlag(boolean flag){
		this.flag = flag ;
	}
}
