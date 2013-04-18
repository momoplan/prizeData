package com.ruyicai.prizedata.missvalue.elevenduojin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruyicai.prizedata.domain.PrizeInfo;
import com.ruyicai.prizedata.service.PrizeInfoService;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
/**
 * 前二组选(选号栏)
 * @author liuhongxing
 *
 */
public class ElevenDuoJinQ2Z {

	public static final String KEY = "T01012MV_Q2Z";
	private static final String LOTNO = "T01012";
	
	private int[] miss = new int[11];
	
	
	

	
	
	public int[] getMiss() {
		return miss;
	}

	public void setMiss(int[] miss) {
		this.miss = miss;
	}

	public void onPrize(PrizeInfo prizeInfo) {
		int prizeShi = Integer.parseInt(prizeInfo.getWinbasecode().substring(0, 2));
		int prizeGe = Integer.parseInt(prizeInfo.getWinbasecode().substring(2, 4));
		plusOne(miss);
		miss[prizeShi-1] = 0;
		miss[prizeGe-1] = 0;
	}
	
	public ElevenDuoJinQ2Z refresh(PrizeInfoService prizeInfoService,int index) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (int j = 1; j <= 11; j++) {
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
				if(this.refreshQ2(p.getWinbasecode(), map, i)){
					i++;
				}else{
					flag = false;
					break;
				}
			}
		}
		return this;
	}
	
	
	private boolean refreshQ2(String win,Map<String, Object> map,int i) {
		int prizeShi = Integer.parseInt(win.substring(0, 2));
		int prizeGe = Integer.parseInt(win.substring(2, 4));
		
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
	
	public static ElevenDuoJinQ2Z fromJsonToElevenDuoJinQ2Z(String json) {
		return new JSONDeserializer<ElevenDuoJinQ2Z>().use(null,
				ElevenDuoJinQ2Z.class).deserialize(json);
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
