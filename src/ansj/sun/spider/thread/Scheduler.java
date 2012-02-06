package ansj.sun.spider.thread;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ansj.sun.spider.io.SpiderIO;
import ansj.sun.spider.thread.impl.DefaultWorker;

public class Scheduler {
	public static int threadSize = 1;

	private static final LinkedList<Worker> workers = new LinkedList<Worker>();

	private static ExecutorService es = null;

	public static void main(String[] args) throws IOException {
		//加载系统文件
		SpiderIO.initFileSystem() ;
		
		start(args);
		System.out.println("程序已经开始运行.如果你要做以下操作请按照说明输入!");
		while (true) {
			System.out.println("1.修改线程数 请输入:-ts [threadSize]");
			System.out.println("2.退出程序请输入:quit");
			System.out.print("控制台:");
			Scanner sc = new Scanner(System.in);
			String str = sc.nextLine();
			if ("quit".equals(str)) {
				System.out.println("程序正在退出请稍等..................");
				exit();
				System.out.println("程序退出成功!");
				System.exit(0);
			} else if (str.startsWith("-ts ")) {
				int num = Integer.parseInt(str.substring(4));
				change(num);
				System.out.println("修改线程数成功!当前线程数为:" + num);
			}
		}
	}

	private static void change(int num) {
		// TODO Auto-generated method stub
		int size = workers.size() - num;

		// 如果新任务数小于当前线程说明要停止一些线程
		if (size > 0) {
			for (int i = 0; i < size; i++) {
				Worker worker = workers.poll();
				worker.changeFlag(false);
			}
		} else {
			size = Math.abs(size);
			Worker worker = null;
			for (int i = 0; i < size; i++) {
				worker = new DefaultWorker();
				workers.add(worker);
				es.execute(worker);
			}
		}
	}

	private static void exit() {
		// TODO Auto-generated method stub
		/**
		 * 将列表内所有状态都为false
		 */
		change(0);

		// 将线程停止掉
		es.shutdown();

		// 储存待采集的列表
		SpiderIO.shoutDown();
	}

	/**
	 * 启动采集器
	 * @author ansj
	 * @mail ansj-sun@163.com
	 * @param args
	 */
	public static void start(String[] args) {
		if (args != null && args.length > 0) {
			threadSize = Integer.parseInt(args[0]);
		}
		es = Executors.newCachedThreadPool(new MyThreadFactory());
		Worker worker = null;
		for (int i = 0; i < threadSize; i++) {
			worker = new DefaultWorker();
			workers.add(worker);
			es.execute(worker);
		}

	}
}
