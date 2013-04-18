package com.ruyicai.prizedata.missvalue.five22;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruyicai.prizedata.domain.PrizeInfo;
import com.ruyicai.prizedata.service.PrizeInfoService;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

public class Five22X {

	public static final String KEY = "T01013MV_X";
	private static final String LOTNO = "T01013";
	
	private int[] miss = new int[22];

	public int[] getMiss() {
		return miss;
	}

	public void setMiss(int[] miss) {
		this.miss = miss;
	}
	
	
	public void onPrize(PrizeInfo prizeInfo) {
		String winbase = prizeInfo.getWinbasecode();
		int[] winPrize = { Integer.parseInt(winbase.substring(0, 2)),
				Integer.parseInt(winbase.substring(2, 4)),
				Integer.parseInt(winbase.substring(4, 6)),
				Integer.parseInt(winbase.substring(6, 8)),
				Integer.parseInt(winbase.substring(8, 10))};
		
		plusOne(miss);
		
		for(int i:winPrize) {
			miss[i-1] = 0;
		}
		
	}
	
	
	
	public Five22X refresh(PrizeInfoService prizeInfoService,int index) {
		Map<String, Object> map = new HashMap<String, Object>();
		for(int i = 1;i<=22;i++) {
			map.put(""+i, 0);
		}
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
				if(this.refreshX(p.getWinbasecode(), map, i)){
					i++;
				}else{
					flag = false;
					break;
				}
			}
		}
		return this;
	}
	
	
	
	private boolean refreshX(String winbase,Map<String, Object> map,int i) {
		int[] winPrize = { Integer.parseInt(winbase.substring(0, 2)),
				Integer.parseInt(winbase.substring(2, 4)),
				Integer.parseInt(winbase.substring(4, 6)),
				Integer.parseInt(winbase.substring(6, 8)),
				Integer.parseInt(winbase.substring(8, 10))};
		
		for(int j:winPrize) {
			if(map.get(""+j)!=null) {
				miss[j-1] = i;
				map.remove(""+j);
			}
		}
		if(map.isEmpty()) {
			return false;
		}else {
			return true;
		}
	}
	

	
	private void plusOne(int[] miss) {
		for (int i = 0; i < miss.length; i++) {
			miss[i] = miss[i] + 1;
		}
	}

	public String toJson() {
		return new JSONSerializer().exclude("*.class").deepSerialize(this);
	}

	public static Five22X fromJsonToFive22X(String json) {
		return new JSONDeserializer<Five22X>().use(null,
				Five22X.class).deserialize(json);

	}
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i:miss) {
			sb.append(i).append(",");
		}
		if(sb.toString().endsWith(",")) {
			sb.deleteCharAt(sb.length()-1);
		}
		return sb.toString();
	}
}
