package ansj.sun.bloomFilter.filter;

import ansj.sun.bloomFilter.bitMap.IntMap;
import ansj.sun.bloomFilter.bitMap.LongMap;
import ansj.sun.bloomFilter.iface.BitMap;
import ansj.sun.bloomFilter.iface.Filter;

public class FNVFilter implements Filter {

	private BitMap bm = null;

	private long size = 0;

	public FNVFilter(long maxValue, int MACHINENUM) throws Exception {
		this.size = maxValue;
		if (MACHINENUM == 32) {
			bm = new IntMap((int) (size / MACHINENUM));
		} else if (MACHINENUM == 64) {
			bm = new LongMap((int) (size / MACHINENUM));
		} else {
			throw new Exception("传入的机器位数有误");
		}
	}

	public FNVFilter(long maxValue) {
		this.size = maxValue;
		bm = new IntMap((int) (size / 32));
	}

	@Override
	public boolean contains(String str) {
		// TODO Auto-generated method stub
		long hash = this.myHashCode(str);
		return bm.contains(hash);
	}

	@Override
	public void add(String str) {
		// TODO Auto-generated method stub
		long hash = this.myHashCode(str);
		bm.add(hash);
	}

	@Override
	public boolean containsAndAdd(String str) {
		// TODO Auto-generated method stub
		long hash = this.myHashCode(str);
		if (bm.contains(hash)) {
			return true;
		} else {
			bm.add(hash);
		}
		return false;
	}

	private static final int p = 16777619;

	@Override
	public long myHashCode(String str) {
		int hash = (int) 2166136261L;
		for (int i = 0; i < str.length(); i++)
			hash = (hash ^ str.charAt(i)) * p;
		hash += hash << 13;
		hash ^= hash >> 7;
		hash += hash << 3;
		hash ^= hash >> 17;
		hash += hash << 5;
		return hash % size;
	}

}
