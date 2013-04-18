package com.ruyicai.prizedata.missvalue.shishicai;

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
 * 时时彩二星直选(右侧边栏)
 * @author liuhongxing
 *
 */
public class ShiShiCai2D{

	public static final String KEY = "T01007MV_2D";
	private static final String LOTNO = "T01007";

	private Map<String,Integer> result = new HashMap<String,Integer>();
	
	public Map<String, Integer> getResult() {
		return result;
	}

	public void setResult(Map<String, Integer> result) {
		this.result = result;
	}


	public void onPrize(PrizeInfo prizeInfo) {
		String win = prizeInfo.getWinbasecode().substring(3);
		plusMap();
		result.put(win, 0);
		
	}


	public ShiShiCai2D refresh(PrizeInfoService prizeInfoService,int index) {
		Map<String, Object> mark = new HashMap<String, Object>();
		mark = initMap(mark);
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
				if(this.refresh2D(p.getWinbasecode(), mark, i)){
					i++;
				}else{
					flag = false;
					break;
				}
			}
		}
		return this;
		
	}

	
	private void plusMap() {
		Set<String> keySet = result.keySet();
		for (Iterator<String> iterator = keySet.iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			result.put(key, result.get(key)+1);
		}
	}
	
	private boolean refresh2D(String win,Map<String,Object> mark,int i) {
		win = win.substring(3);
		if(mark.get(win)!=null) {
			result.put(win, i);
			mark.remove(win);
			
		}
		if(mark.isEmpty()) {
			return false;
		}
		return true;
	}
	
	private Map<String,Object> initMap(Map<String,Object> map) {
		for(int i = 0;i <= 9;i++) {
			for(int j = 0;j<=9;j++) {
				map.put(""+i+j, 0);
			}
		}
		return map;
		
	}
	
	public String toJson() {
		return new JSONSerializer().exclude("*.class").deepSerialize(this);
    }
	
	public static ShiShiCai2D fromJsonToShiShiCai2D(String json) {
		return new JSONDeserializer<ShiShiCai2D>().use(null, ShiShiCai2D.class)
							.deserialize(json);

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
