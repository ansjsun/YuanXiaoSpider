package ansj.sun.bloomFilter.bitMap;

import ansj.sun.bloomFilter.iface.BitMap;


/**
 * 
* @项目名称：Test   
* @类名称：LongMap   
* @类描述：   过滤器BitMap在32位机器上.这个类能发生更好的效果.一般情况下建议使用此类
* @创建人：Ansj   
* @创建时间：2011-9-8 下午03:17:20  
* @修改备注：   
* @version    
*
 */
public class IntMap implements BitMap{

	private static final int MAX = Integer.MAX_VALUE;


	public IntMap() {
		ints = new int[93750000];
	}

	public IntMap(int size) {
		ints = new int[size];
	}

	private int[] ints = null;

	public void add(long i) {
		int r = (int) (i / 32);
		int c = (int) (i % 32);
		ints[r] = (int) (ints[r] | (1 << c));
	}

	public boolean contains(long i) {
		int r = (int) (i / 32);
		int c = (int) (i % 32);
		if (((int) ((ints[r] >>> c)) & 1) == 1) {
			return true;
		}
		return false;
	}

	public void remove(long i) {
		int r = (int) (i / 32);
		int c = (int) (i % 32);
		ints[r] = (int) (ints[r] & (((1 << (c + 1)) - 1) ^ MAX));
	}

}
