package com.ruyicai.prizedata.missvalue.guangdong11c5;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruyicai.prizedata.domain.PrizeInfo;
import com.ruyicai.prizedata.service.PrizeInfoService;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
/**
 * 前二直选(选号栏)
 * @author liuhongxing
 *
 */
public class Guangdong11c5Q2 {

	public static final String KEY = "T01014MV_Q2";
	private static final String LOTNO = "T01014";
	
	private int[] shi = new int[11];
	private int[] ge = new int[11];
	
	public int[] getShi() {
		return shi;
	}

	public void setShi(int[] shi) {
		this.shi = shi;
	}

	public int[] getGe() {
		return ge;
	}

	public void setGe(int[] ge) {
		this.ge = ge;
	}

	
	
	public void onPrize(PrizeInfo prizeInfo) {
		int prizeShi = Integer.parseInt(prizeInfo.getWinbasecode().substring(0, 2));
		int prizeGe = Integer.parseInt(prizeInfo.getWinbasecode().substring(2, 4));
		plusOne(shi);
		plusOne(ge);
		shi[prizeShi-1] = 0;
		ge[prizeGe-1] = 0;
	}
	
	public Guangdong11c5Q2 refresh(PrizeInfoService prizeInfoService,int index) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (int j = 1; j <= 11; j++) {
			map.put(j+"", 0);
		}
		for (int j = 21; j <= 31; j++) {
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
			ge[prizeGe-1] = i;
			map.remove(prizeGe+"");
		}
		
		if(map.get((prizeShi+20)+"")!=null) {
			shi[prizeShi-1] = i;
			map.remove((prizeShi+20)+"");
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
	
	public static Guangdong11c5Q2 fromJsonToGuangdong11c5Q2(String json) {
		return new JSONDeserializer<Guangdong11c5Q2>().use(null,
				Guangdong11c5Q2.class).deserialize(json);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i:shi) {
			sb.append(i).append(",");
		}
		if(sb.toString().endsWith(",")) {
			sb.deleteCharAt(sb.length()-1);
		}
		sb.append(";");
		for(int i:ge) {
			sb.append(i).append(",");
		}
		if(sb.toString().endsWith(",")) {
			sb.deleteCharAt(sb.length()-1);
		}
		return sb.toString();
	}
}
