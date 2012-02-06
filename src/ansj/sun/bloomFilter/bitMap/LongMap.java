package ansj.sun.bloomFilter.bitMap;

import ansj.sun.bloomFilter.iface.BitMap;

/**
 * 
* @��Ŀ���ƣ�Test   
* @�����ƣ�LongMap   
* @��������   ������BitMap��64λ������.������ܷ������õ�Ч��.һ�����������ʹ��
* @�����ˣ�Ansj   
* @����ʱ�䣺2011-9-8 ����03:17:20  
* @�޸ı�ע��   
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
