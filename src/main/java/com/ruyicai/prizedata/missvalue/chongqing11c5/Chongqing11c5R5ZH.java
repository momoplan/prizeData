package com.ruyicai.prizedata.missvalue.chongqing11c5;

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
 * 任选五组合遗漏(右侧边栏)
 * @author liuhongxing
 *
 */
public class Chongqing11c5R5ZH {

	public static final String KEY = "T01016MV_R5ZH";
	private static final String LOTNO = "T01016";
	
	private Map<String,Integer> result = new HashMap<String,Integer>();
	
	public Map<String, Integer> getResult() {
		return result;
	}

	public void setResult(Map<String, Integer> result) {
		this.result = result;
	}


	public void onPrize(PrizeInfo prizeInfo) {
		String win = getSortWin(prizeInfo.getWinbasecode());
		plusOne();
		result.put(win,0);
	}
	
	
	public Chongqing11c5R5ZH refresh(PrizeInfoService prizeInfoService,int index) {
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
				if(this.refreshR5ZH(p.getWinbasecode(), map, i)){
					i++;
				}else{
					flag = false;
					break;
				}
			}
		}
		return this;
	}
	
	private boolean refreshR5ZH(String win,Map<String, Object> mark,int i) {
		String winPrize = getSortWin(win);
		if(mark.get(winPrize)!=null) {
			result.put(winPrize, i);
			mark.remove(winPrize);
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
		BaseMath.combine(a, 11, 5, new int[5], 5, list);
		
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
	
	
	private String getSortWin(String win) {
		int[] winInt = { Integer.parseInt(win.substring(0, 2)),
				Integer.parseInt(win.substring(2, 4)),
				Integer.parseInt(win.substring(4, 6)),
				Integer.parseInt(win.substring(6, 8)),
				Integer.parseInt(win.substring(8, 10)) };
		Arrays.sort(winInt);
		
		StringBuilder sb = new StringBuilder();
		
		for(int i:winInt) {
			sb.append(i>=10?""+i:"0"+i);
		}
		
		return sb.toString();
	}
	
	
	public String toJson() {
		return new JSONSerializer().exclude("*.class").deepSerialize(this);
	}
	
	public static Chongqing11c5R5ZH fromJsonToChongqing11c5R5ZH(String json) {
		return new JSONDeserializer<Chongqing11c5R5ZH>().use(null,
				Chongqing11c5R5ZH.class).deserialize(json);
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
