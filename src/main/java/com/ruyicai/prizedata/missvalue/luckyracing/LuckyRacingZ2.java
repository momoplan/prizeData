package com.ruyicai.prizedata.missvalue.luckyracing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruyicai.prizedata.domain.PrizeInfo;
import com.ruyicai.prizedata.service.PrizeInfoService;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

public class LuckyRacingZ2 {

	public static final String KEY = "T01018MV_Z2";
	private static final String LOTNO = "T01018";
	
	private int[] miss = new int[12];

	public int[] getMiss() {
		return miss;
	}

	public void setMiss(int[] miss) {
		this.miss = miss;
	}
	
	public void onPrize(PrizeInfo prizeInfo) {
		int prizeShi = Integer.parseInt(prizeInfo.getWinbasecode().substring(0, 2));
		int prizeGe = Integer.parseInt(prizeInfo.getWinbasecode().substring(3, 5));
		plusOne(miss);
		miss[prizeShi-1] = 0;
		miss[prizeGe-1] = 0;
	}
	
	public LuckyRacingZ2 refresh(PrizeInfoService prizeInfoService,int index) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (int j = 1; j <= 12; j++) {
			map.put(j+"", 0);
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
				if(this.refreshZ2(p.getWinbasecode(), map, i)){
					i++;
				}else{
					flag = false;
					break;
				}
			}
		}
		return this;
	}
	
	
	private boolean refreshZ2(String win,Map<String, Object> map,int i) {
		int prizeShi = Integer.parseInt(win.substring(0, 2));
		int prizeGe = Integer.parseInt(win.substring(3, 5));
		
		if(map.get(prizeGe+"")!=null) {
			miss[prizeGe-1] = i;
			map.remove(prizeGe+"");
		}
		
		if(map.get(prizeShi+"")!=null) {
			miss[prizeShi-1] = i;
			map.remove(prizeShi+"");
		}
		
		if(map.isEmpty()) {
			return false;
		}else {
			return true;
		}
		
	}
	
	
	private void plusOne(int[] miss) {
		for(int i = 0;i < miss.length;i++) {
			miss[i] = miss[i] + 1;
		}
	}
	
	
	public String toJson() {
		return new JSONSerializer().exclude("*.class").deepSerialize(this);
	}
	
	public static LuckyRacingZ2 fromJsonToLuckyRacingZ2(String json) {
		return new JSONDeserializer<LuckyRacingZ2>().use(null,
				LuckyRacingZ2.class).deserialize(json);
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
