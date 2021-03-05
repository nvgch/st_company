package com.situ.company.util;

import java.util.Collection;
import java.util.Map;

/**
 * @author TaoTao
 *
 */
public class FmtEmpty {

	/**
	 * 
	 * 判断是否为空（null||length==0)
	 * 
	 * @param str
	 * @return false不为空，true为空
	 */
	public static boolean isEmpty(String str) {
		// TODO Auto-generated method stub
		if (str == null || str.trim().isEmpty()||str == "") {
			return true;
		}
		return false;
	}

	
	/**
	 * @param list
	 * @return false不为空，true为空
	 */
	public static boolean isEmpty(Collection<?> list) {
		if (list == null || isEmpty(list.toString())||list.size()==0) {//list.size()==0
			
			return true;
		}
		return false;

	}
	
	
	/**
	 * 
	 * 判断是否为空（null||isEmpty)
	 * 
	 * 
	 * @param map
	 * @return false不为空，true为空
	 */
	public static boolean isEmpty(Map<?,?> map) {
		if (map == null || map.isEmpty()) {
			return true;
		}
		return false;

	}
	
	
	/**
	 * 
	 * 判断是否为空（null||length == 0)
	 * 
	 * @param array
	 * @return false不为空，true为空
	 */
	public static boolean isEmpty(Object[] array) {
		if (array == null || array.length == 0) {
			return true;
		}
		return false;

	}
}
