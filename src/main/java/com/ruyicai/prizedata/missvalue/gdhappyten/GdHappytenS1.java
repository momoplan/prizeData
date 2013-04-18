package com.ruyicai.prizedata.missvalue.gdhappyten;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruyicai.prizedata.domain.PrizeInfo;
import com.ruyicai.prizedata.service.PrizeInfoService;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

/**
 * 选一数投
 * @author liuhongxing
 *
 */
public class GdHappytenS1 {

	public static final String KEY = "T01015MV_S1";
	private static final String LOTNO = "T01015";
	
	private int[] miss = new int[18];

	public int[] getMiss() {
		return miss;
	}

	public void setMiss(int[] miss) {
		this.miss = miss;
	}
	
	
	public void onPrize(PrizeInfo prizeInfo) {
		int prizeGe = Integer.parseInt(prizeInfo.getWinbasecode().substring(0, 2));
		plusOne(miss);
		if(prizeGe<=18) {
			miss[prizeGe-1] = 0;
		}
		
	}
	
	
	public GdHappytenS1 refresh(PrizeInfoService prizeInfoService,int index) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (int j = 1; j <= 18; j++) {
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
				if(this.refreshQ1(p.getWinbasecode(), map, i)){
					i++;
				}else{
					flag = false;
					break;
				}
			}
		}
		return this;
	}
	
	
	private boolean refreshQ1(String win,Map<String, Object> map,int i) {
		int prizeGe = Integer.parseInt(win.substring(0, 2));
		
		if(map.get(prizeGe+"")!=null) {
			miss[prizeGe-1] = i;
			map.remove(prizeGe+"");
		}
		
		if(map.isEmpty()) {
			return false;
		}else {
			return true;
		}
		
	}
	
	
	
	public String toJson() {
		return new JSONSerializer().exclude("*.class").deepSerialize(this);
	}
	
	
	public static GdHappytenS1 fromJsonToGdHappytenS1(String json) {
		return new JSONDeserializer<GdHappytenS1>().use(null,
				GdHappytenS1.class).deserialize(json);
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
	
	private void plusOne(int[] miss) {
		for(int i = 0;i < miss.length;i++) {
			miss[i] = miss[i] + 1;
		}
	}
}
