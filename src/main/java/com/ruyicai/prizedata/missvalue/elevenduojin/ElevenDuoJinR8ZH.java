package com.ruyicai.prizedata.missvalue.elevenduojin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ruyicai.prizedata.domain.PrizeInfo;
import com.ruyicai.prizedata.service.PrizeInfoService;
import com.ruyicai.prizedata.util.BaseMath;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
/**
 * 任选八组合遗漏(右侧边栏)
 * @author liuhongxing
 *
 */
public class ElevenDuoJinR8ZH {

	public static final String KEY = "T01012MV_R8ZH";
	private static final String LOTNO = "T01012";
	
	private Map<String,Integer> result = new HashMap<String,Integer>();
	
	public Map<String, Integer> getResult() {
		return result;
	}

	public void setResult(Map<String, Integer> result) {
		this.result = result;
	}


	public void onPrize(PrizeInfo prizeInfo) {
		String win = prizeInfo.getWinbasecode();
		String wan = win.substring(0,2);
		String qian = win.substring(2,4);
		String bai = win.substring(4,6);
		String shi = win.substring(6,8);
		String ge = win.substring(8,10);
		plusOne();
		Set<String> set = result.keySet();
		Map<String,Object> keymap = new HashMap<String,Object>();
		for (Iterator<String> iterator = set.iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			keymap = getKeyMap(keymap, key);
			if(keymap.containsKey(wan)&&keymap.containsKey(qian)&&keymap.containsKey(bai)&&keymap.containsKey(shi)&&keymap.containsKey(ge)) {
				result.put(key, 0);
			}
			keymap.clear();
		}
	}
	
	
	public ElevenDuoJinR8ZH refresh(PrizeInfoService prizeInfoService,int index) {
		Map<String, Object> map = new HashMap<String, Object>();
		map = initMap(map);

		int i = 0;
		int j = 0;
		boolean flag = true;
		while(flag){
			List<PrizeInfo> prizeInfos = prizeInfoService.find(LOTNO, j+index, 50);
			j = j + 50;
			if (prizeInfos == null || prizeInfos.size() == 0) {
				break;
			}
			for(PrizeInfo p:prizeInfos) {
				if(this.refreshR8ZH(p.getWinbasecode(), map, i)){
					i++;
				}else{
					flag = false;
					break;
				}
			}
		}
		return this;
	}
	
	private boolean refreshR8ZH(String win,Map<String, Object> mark,int i) {
		String wan = win.substring(0,2);
		String qian = win.substring(2,4);
		String bai = win.substring(4,6);
		String shi = win.substring(6,8);
		String ge = win.substring(8,10);
		
		Set<String> set = mark.keySet();
		Map<String,Object> keymap = new HashMap<String,Object>();
		List<String> keyList = new ArrayList<String>();
		for (Iterator<String> iterator = set.iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			keymap = getKeyMap(keymap, key);
			if(key.contains(wan)&&key.contains(qian)&&key.contains(bai)&&key.contains(shi)&&key.contains(ge)) {
				result.put(key, i);
				keyList.add(key);
			}
			keymap.clear();
		}
		
		for(String key:keyList) {
			mark.remove(key);
		}
		if(mark.isEmpty()) {
			return false;
		}
		return true;
	}
	
	
	
	
	private void plusOne() {
		Set<String> keySet = result.keySet();
		for (Iterator<String> iterator = keySet.iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			result.put(key, result.get(key)+1);
		}
	}
	
	
	private Map<String, Object> initMap(Map<String, Object> map) {
		int[] a = {1,2,3,4,5,6,7,8,9,10,11};
		List<int[]> list = new ArrayList<int[]>();
		BaseMath.combine(a, 11, 8, new int[8], 8, list);
		
		for(int[] b:list) {
			map.put(getKey(b), 0);
		}
		
		return map;
	}
	


	private String getKey(int[] a) {
		Arrays.sort(a);
		StringBuilder sb = new StringBuilder();
		for(int i:a) {
			sb.append(i>=10?""+i:"0"+i);
		}
		return sb.toString();
	}
	
	private Map<String,Object> getKeyMap(Map<String,Object> map,String win) {
		map.put(win.substring(0, 2), 0);
		map.put(win.substring(2, 4), 0);
		map.put(win.substring(4, 6), 0);
		map.put(win.substring(6, 8), 0);
		map.put(win.substring(8, 10), 0);
		map.put(win.substring(10, 12), 0);
		map.put(win.substring(12, 14), 0);
		map.put(win.substring(14, 16), 0);
		return map;
	}
	
	
	
	public String toJson() {
		return new JSONSerializer().exclude("*.class").deepSerialize(this);
	}
	
	public static ElevenDuoJinR8ZH fromJsonToElevenDuoJinR8ZH(String json) {
		return new JSONDeserializer<ElevenDuoJinR8ZH>().use(null,
				ElevenDuoJinR8ZH.class).deserialize(json);
	}
	
	@Override
	public String toString() {
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
	
	
}
