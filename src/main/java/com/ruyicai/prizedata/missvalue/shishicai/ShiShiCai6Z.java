package com.ruyicai.prizedata.missvalue.shishicai;

import java.util.Arrays;
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
 * 时时彩六行组选(右侧边栏)
 * @author liuhongxing
 *
 */
public class ShiShiCai6Z {

	public static final String KEY = "T01007MV_6Z";
	private static final String LOTNO = "T01007";
	
	private Map<String,Integer> result = new HashMap<String,Integer>();

	public Map<String, Integer> getResult() {
		return result;
	}

	public void setResult(Map<String, Integer> result) {
		this.result = result;
	}
	
	public void onPrize(PrizeInfo prizeInfo) {
		String win = prizeInfo.getWinbasecode();
		int bai = Integer.parseInt(win.substring(2,3));
		int shi = Integer.parseInt(win.substring(3,4));
		int ge = Integer.parseInt(win.substring(4,5));
		plusMap();
		if(isZuliu(bai,shi,ge)) {
			result.put(getKey(bai,shi,ge), 0);
		}
		
	}
	
	public ShiShiCai6Z refresh(PrizeInfoService prizeInfoService,int index) {
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
				if(this.refresh3Z(p.getWinbasecode(), mark, i)){
					i++;
				}else{
					flag = false;
					break;
				}
			}
		}
		return this;
		
	}
	
	
	private boolean refresh3Z(String win,Map<String,Object> mark,int i) {
		int bai = Integer.parseInt(win.substring(2,3));
		int shi = Integer.parseInt(win.substring(3,4));
		int ge = Integer.parseInt(win.substring(4,5));
		
		if(isZuliu(bai,shi,ge)) {
			String key = getKey(bai,shi,ge);
			if(mark.get(key)!=null) {
				result.put(key, i);
				mark.remove(key);
				
			}
		}
		
		if(mark.isEmpty()) {
			return false;
		}
		return true;
	}
	
	

	
	private void plusMap() {
		Set<String> keySet = result.keySet();
		for (Iterator<String> iterator = keySet.iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			result.put(key, result.get(key)+1);
		}
	}
	
	
	private Map<String,Object> initMap(Map<String,Object> map) {
		for(int i = 0;i <= 9;i++) {
			for(int j = 0;j<=9;j++) {
				for(int k = 0;k <= 9;k++) {
					if(isZuliu(i,j,k)) {
						map.put(getKey(i,j,k), 0);
					}
				}
			}
		}
		
		return map;
	}
	
	
	private String getKey(int a,int b,int c) {
		int[] sort = {a,b,c};
		Arrays.sort(sort);
		return ""+sort[0]+sort[1]+sort[2];
	}
	
	
	private boolean isZuliu(int a,int b,int c) {
		int i = 0;
		if(a==b) {
			i++;
		}
			
		if(b==c) {
			i++;
		}
			
		if(c==a) {
			i++;
		}
		if(i==0) {
			return true;
		}
		return false;
			
	}

	
	public String toJson() {
        return new JSONSerializer().exclude("*.class").deepSerialize(this);
    }
	
	public static ShiShiCai6Z fromJsonToShiShiCai3Z(String json) {
		return new JSONDeserializer<ShiShiCai6Z>().use(null, ShiShiCai6Z.class)
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
