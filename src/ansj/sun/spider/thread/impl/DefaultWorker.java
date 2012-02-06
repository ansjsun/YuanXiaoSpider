package ansj.sun.spider.thread.impl;

import ansj.sun.spider.URLManager;
import ansj.sun.spider.io.SpiderIO;
import ansj.sun.spider.pojo.Page;
import ansj.sun.spider.thread.Worker;
import ansj.sun.spider.util.PageUtilFactory;
import ansj.sun.spider.util.PageUtils;
import ansj.sun.spider.util.impl.DefaultPageUtils;
import ansj.sun.util.StringUtils;

public class DefaultWorker extends Worker {

	PageUtils pageUtils = null ;

	@Override
	public Page downPage(String urlStr) {
		// TODO Auto-generated method stub
		pageUtils = PageUtilFactory.create(urlStr) ;
		return pageUtils.downPage(urlStr);
	}

	@Override
	public String getUrl() {
		// TODO Auto-generated method stub
		try {
			return URLManager.poolUrl();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private String urlStr;
	private Page page;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (flag) {
			try {
				urlStr = this.getUrl();
				if (StringUtils.isBlank(urlStr)) {
					continue;
				}
				page = downPage(urlStr);
				SpiderIO.savePage(page);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
