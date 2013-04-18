package com.ruyicai.prizedata.missvalue.elevenduojin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruyicai.prizedata.domain.PrizeInfo;
import com.ruyicai.prizedata.service.PrizeInfoService;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
/**
 * 前一直选(选号栏)
 * @author liuhongxing
 *
 */
public class ElevenDuoJinQ1 {

	public static final String KEY = "T01012MV_Q1";
	private static final String LOTNO = "T01012";
	
	private int[] ge = new int[11];
	


	public int[] getGe() {
		return ge;
	}

	public void setGe(int[] ge) {
		this.ge = ge;
	}

	
	
	public void onPrize(PrizeInfo prizeInfo) {
		int prizeGe = Integer.parseInt(prizeInfo.getWinbasecode().substring(0, 2));
		plusOne(ge);
		ge[prizeGe-1] = 0;
	}
	
	public ElevenDuoJinQ1 refresh(PrizeInfoService prizeInfoService,int index) {
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
			ge[prizeGe-1] = i;
			map.remove(prizeGe+"");
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
	
	public static ElevenDuoJinQ1 fromJsonToElevenDuoJinQ1(String json) {
		return new JSONDeserializer<ElevenDuoJinQ1>().use(null,
				ElevenDuoJinQ1.class).deserialize(json);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i:ge) {
			sb.append(i).append(",");
		}
		if(sb.toString().endsWith(",")) {
			sb.deleteCharAt(sb.length()-1);
		}
		return sb.toString();
	}
}
