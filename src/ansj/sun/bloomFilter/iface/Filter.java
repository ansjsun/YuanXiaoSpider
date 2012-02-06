package ansj.sun.bloomFilter.iface;

public interface Filter {

	/**
	 * 
	* @�����ˣ�Ansj  -����ʱ�䣺2011-9-8 ����03:23:07    
	* @����������   @param str
	* @����������   @return �ж�һ���ַ����Ƿ�bitMap�д���
	 */
	public boolean contains(String str);

	/**
	 * 
	* @�����ˣ�Ansj  -����ʱ�䣺2011-9-8 ����03:22:42    
	* @����������   @param str ��boolean��bitMap������һ���ַ���
	 */
	public void add(String str);
	
	/**
	 * 
	* @�����ˣ�Ansj  -����ʱ�䣺2011-9-8 ����03:21:51    
	* @����������   @param str ��Ҫ���Ӳ��Ҳ�ѯ�ķ���
	* @����������   @return ������ھͷ���true .���������.����������ַ���.�ٷ���false
	 */
	public boolean containsAndAdd(String str) ;
	
	/**
	 * 
	* @�����ˣ�Ansj  -����ʱ�䣺2011-9-8 ����04:36:21    
	* @����������   @param chars ����char����
	* @����������   @return 
	 */
	public long myHashCode(String str) ;

}
