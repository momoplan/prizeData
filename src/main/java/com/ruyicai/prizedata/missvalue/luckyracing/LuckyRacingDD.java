package com.ruyicai.prizedata.missvalue.luckyracing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruyicai.prizedata.domain.PrizeInfo;
import com.ruyicai.prizedata.service.PrizeInfoService;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

public class LuckyRacingDD {

	public static final String KEY = "T01018MV_DD";
	private static final String LOTNO = "T01018";
	
	private int[] miss = new int[4];

	public int[] getMiss() {
		return miss;
	}

	public void setMiss(int[] miss) {
		this.miss = miss;
	}
	
	
	public void onPrize(PrizeInfo prizeInfo) {
		int win = Integer.parseInt(prizeInfo.getWinbasecode().substring(0, 2));

		plusOne(miss);
		
		if(win<=6&&win%2==0) {
			miss[1] = 0;
		}else if(win<=6&&win%2==1) {
			miss[0] = 0;
		}else if(win>6&&win%2==0) {
			miss[3] = 0;
		}else if(win>6&&win%2==1) {
			miss[2] = 0;
		}
		
	}
	
	public LuckyRacingDD refresh(PrizeInfoService prizeInfoService,int index) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (int j = 0; j <= 3; j++) {
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
				if(this.refresh(p.getWinbasecode(), map, i)){
					i++;
				}else{
					flag = false;
					break;
				}
			}
		}
		return this;
	}
	
	
	private boolean refresh(String win,Map<String, Object> map,int i) {
		int winstr = Integer.parseInt(win.substring(0, 2));

		
		if(winstr<=6&&winstr%2==0) {
			if(map.get("1")!=null) {
				miss[1] = i;
				map.remove("1");
			}
		}else if(winstr<=6&&winstr%2==1) {
			if(map.get("0")!=null) {
				miss[0] = i;
				map.remove("0");
			}
		}else if(winstr>6&&winstr%2==0) {
			if(map.get("3")!=null) {
				miss[3] = i;
				map.remove("3");
			}
		}else if(winstr>6&&winstr%2==1) {
			if(map.get("2")!=null) {
				miss[2] = i;
				map.remove("2");
			}
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
	
	public static LuckyRacingDD fromJsonToLuckyRacingDD(String json) {
		return new JSONDeserializer<LuckyRacingDD>().use(null,
				LuckyRacingDD.class).deserialize(json);
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
