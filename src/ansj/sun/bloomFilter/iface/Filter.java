package ansj.sun.bloomFilter.iface;

public interface Filter {

	/**
	 * 
	* @创建人：Ansj  -创建时间：2011-9-8 下午03:23:07    
	* @方法描述：   @param str
	* @方法描述：   @return 判断一个字符串是否bitMap中存在
	 */
	public boolean contains(String str);

	/**
	 * 
	* @创建人：Ansj  -创建时间：2011-9-8 下午03:22:42    
	* @方法描述：   @param str 在boolean的bitMap中增加一个字符串
	 */
	public void add(String str);
	
	/**
	 * 
	* @创建人：Ansj  -创建时间：2011-9-8 下午03:21:51    
	* @方法描述：   @param str 需要增加并且查询的方法
	* @方法描述：   @return 如果存在就返回true .如果不存在.先增加这个字符串.再返回false
	 */
	public boolean containsAndAdd(String str) ;
	
	/**
	 * 
	* @创建人：Ansj  -创建时间：2011-9-8 下午04:36:21    
	* @方法描述：   @param chars 传入char数组
	* @方法描述：   @return 
	 */
	public long myHashCode(String str) ;

}
