package com.ruyicai.prizedata.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class BaseUtil {

	
	public static String toStringFromMap(Map<String,Integer> result) {
		StringBuilder sb = new StringBuilder();
		Set<String> keySet = result.keySet();
		String key = "";
		for (Iterator<String> iterator = keySet.iterator(); iterator.hasNext();) {
			key = (String) iterator.next();
			int i = result.get(key);
			sb.append(key).append(":").append(i).append(";");
		}
		if(sb.toString().endsWith(";")) {
			sb.deleteCharAt(sb.length()-1);
		}
		return sb.toString();
	}
	
	
	public static String getKey(int[] a) {
		Arrays.sort(a);
		StringBuilder sb = new StringBuilder();
		for(int i:a) {
			sb.append(i>=10?""+i:"0"+i);
		}
		return sb.toString();
	}
	
	public static String getKey(String ...strs) {
		Arrays.sort(strs);
		StringBuilder sb = new StringBuilder();
		for(String str:strs) {
			sb.append(str);
		}
		return sb.toString();
	}
	
	public static boolean isSame(int ...is) {
		Arrays.sort(is);
		for(int i=0;i<is.length-1;i++) {
			for(int j=i+1;j<is.length;j++) {
				if(is[i]==is[j]) {
					return true;
				}
			}
		}
		return false;
	}
	
	
	public static Map<String,Integer> plusOne(Map<String,Integer> result) {
		Set<String> keySet = result.keySet();
		for (Iterator<String> iterator = keySet.iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			result.put(key, result.get(key)+1);
		}
		return result;
	}
	
	
}
