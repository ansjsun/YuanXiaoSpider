package ansj.sun.spider.thread;

import java.util.concurrent.ThreadFactory;

public class MyThreadFactory implements ThreadFactory {

	private int k = 0 ;
	@Override
	public Thread newThread(Runnable r) {
		// TODO Auto-generated method stub
		Thread t = new Thread(r,"Thread-"+(k++)) ;
		return t ;
	}
	
 
}

