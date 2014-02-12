package com.ruyicai.prizedata.missvalue.shanghai11c5;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ruyicai.prizedata.domain.PrizeInfo;
import com.ruyicai.prizedata.service.PrizeInfoService;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
/**
 * 前三直选组合遗漏(右侧边栏)
 * @author liuhongxing
 *
 */
public class Shanghai11c5Q3ZH {

	public static final String KEY = "T01017MV_Q3ZH";
	private static final String LOTNO = "T01017";
	
	private Map<String,Integer> result = new HashMap<String,Integer>();
	
	public Map<String, Integer> getResult() {
		return result;
	}

	public void setResult(Map<String, Integer> result) {
		this.result = result;
	}


	public void onPrize(PrizeInfo prizeInfo) {
		String win = prizeInfo.getWinbasecode().substring(0,6);
		plusOne();
		result.put(win,0);
	}
	
	
	public Shanghai11c5Q3ZH refresh(PrizeInfoService prizeInfoService,int index) {
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
				if(this.refreshQ3ZH(p.getWinbasecode(), map, i)){
					i++;
				}else{
					flag = false;
					break;
				}
			}
		}
		return this;
	}
	
	private boolean refreshQ3ZH(String win,Map<String, Object> mark,int i) {
		String winPrize = win.substring(0,6);
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
		for(int i=1;i<=11;i++) {
			for(int j=1;j<=11;j++) {
				for(int k=1;k<=11;k++) {
					if(isSame(i,j,k)) {
						map.put(getKey(i,j,k), 0);
					}
				}
			}
		}
		
		return map;
	}
	
	
	private boolean isSame(int i,int j,int k){
		if(i==j||j==k||i==k) {
			return false;
		}
		return true;
	}

	private String getKey(int i,int j,int k) {
		String is = i>=10?i+"":"0"+i;
		String js = j>=10?i+"":"0"+j;
		String ks = k>=10?k+"":"0"+k;
		return is+js+ks;
	}
	
	public String toJson() {
		return new JSONSerializer().exclude("*.class").deepSerialize(this);
	}
	
	public static Shanghai11c5Q3ZH fromJsonToShanghai11c5Q3ZH(String json) {
		return new JSONDeserializer<Shanghai11c5Q3ZH>().use(null,
				Shanghai11c5Q3ZH.class).deserialize(json);
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
