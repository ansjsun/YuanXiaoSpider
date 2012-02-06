package ansj.sun.bloomFilter.bitMap;

import ansj.sun.bloomFilter.iface.BitMap;

/**
 * 
* @项目名称：Test   
* @类名称：LongMap   
* @类描述：   过滤器BitMap在64位机器上.这个类能发生更好的效果.一般机器不建议使用
* @创建人：Ansj   
* @创建时间：2011-9-8 下午03:17:20  
* @修改备注：   
* @version    
*
 */
public class LongMap implements BitMap{

	private static final long MAX = Long.MAX_VALUE;


	public LongMap() {
		longs = new long[93750000];
	}

	public LongMap(int size) {
		longs = new long[size];
	}

	private long[] longs = null;

	public void add(long i) {
		int r = (int) (i / 64);
		int c = (int) (i % 64);
		longs[r] = (int) (longs[r] | (1 << c));
	}

	public boolean contains(long i) {
		int r = (int) (i / 64);
		int c = (int) (i % 64);
		if (((int) ((longs[r] >>> c)) & 1) == 1) {
			return true;
		}
		return false;
	}

	public void remove(long i) {
		int r = (int) (i / 32);
		int c = (int) (i % 32);
		longs[r] = (int) (longs[r] & (((1 << (c + 1)) - 1) ^ MAX));
	}

}
