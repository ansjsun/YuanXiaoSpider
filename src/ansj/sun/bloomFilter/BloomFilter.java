package ansj.sun.bloomFilter;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import ansj.sun.bloomFilter.filter.JavaFilter;
import ansj.sun.bloomFilter.filter.PHPFilter;
import ansj.sun.bloomFilter.filter.JSFilter;
import ansj.sun.bloomFilter.filter.PJWFilter;
import ansj.sun.bloomFilter.filter.SDBMFilter;
import ansj.sun.bloomFilter.iface.Filter;

/**
 * BlommFilter ʵ�� 1.����hash�㷨 2.ɢ��hashӳ�䵽�����bitλ�� 3.��֤
 * 
 * @author Ansj
 */
public class BloomFilter {
	
	private static int length = 5 ;
	
	Filter[] filters = new Filter[length] ;
	
	
	public BloomFilter(int m){
		float mNum = m/5 ;
		long size = (long) (1L*mNum*1024*1024*8) ;
		filters[0] = new JavaFilter(size) ;
		filters[1] = new PHPFilter(size) ;
		filters[2] = new JSFilter(size) ;
		filters[3] = new PJWFilter(size) ;
		filters[4] = new SDBMFilter(size) ;
	}
	
	public void add(String str){
		for (int i = 0; i < length; i++) {
			filters[i].add(str) ;
		}
	}
	
	public boolean contains(String str){
		for (int i = 0; i < length; i++) {
			if(!filters[i].contains(str)){
				return false ;
			}
		}
		return true ;
	}
	
	public boolean containsAndAdd(String str){
		boolean flag = true ;
		for (int i = 0; i < length; i++) {
			flag = flag&&filters[i].containsAndAdd(str) ;
		}
		return flag ;
	}

}
