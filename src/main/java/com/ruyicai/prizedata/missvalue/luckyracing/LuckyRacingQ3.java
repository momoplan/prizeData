package com.ruyicai.prizedata.missvalue.luckyracing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruyicai.prizedata.domain.PrizeInfo;
import com.ruyicai.prizedata.service.PrizeInfoService;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

public class LuckyRacingQ3 {

	public static final String KEY = "T01018MV_Q3";
	private static final String LOTNO = "T01018";
	
	private int[] ge = new int[12];
	private int[] shi = new int[12];
	private int[] bai = new int[12];
	public int[] getGe() {
		return ge;
	}
	public void setGe(int[] ge) {
		this.ge = ge;
	}
	public int[] getShi() {
		return shi;
	}
	public void setShi(int[] shi) {
		this.shi = shi;
	}
	public int[] getBai() {
		return bai;
	}
	public void setBai(int[] bai) {
		this.bai = bai;
	}
	
	
	public void onPrize(PrizeInfo prizeInfo) {
		int prizeBai = Integer.parseInt(prizeInfo.getWinbasecode().substring(0, 2));
		int prizeShi = Integer.parseInt(prizeInfo.getWinbasecode().substring(3, 5));
		int prizeGe = Integer.parseInt(prizeInfo.getWinbasecode().substring(6, 8));
		plusOne(bai);
		plusOne(shi);
		plusOne(ge);
		bai[prizeBai-1] = 0;
		shi[prizeShi-1] = 0;
		ge[prizeGe-1] = 0;
	}
	
	
	public LuckyRacingQ3 refresh(PrizeInfoService prizeInfoService,int index) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (int j = 1; j <= 12; j++) {
			map.put(j+"", 0);
		}
		for (int j = 21; j <= 32; j++) {
			map.put(j+"", 0);
		}
		for (int j = 41; j <= 52; j++) {
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
				if(this.refreshQ3(p.getWinbasecode(), map, i)){
					i++;
				}else{
					flag = false;
					break;
				}
			}
		}
		return this;
	}
	
	private boolean refreshQ3(String win,Map<String, Object> map,int i) {
		int prizeBai = Integer.parseInt(win.substring(0, 2));
		int prizeShi = Integer.parseInt(win.substring(3, 5));
		int prizeGe = Integer.parseInt(win.substring(6, 8));
		
		if(map.get(prizeGe+"")!=null) {
			ge[prizeGe-1] = i;
			map.remove(prizeGe+"");
		}
		
		if(map.get((prizeShi+20)+"")!=null) {
			shi[prizeShi-1] = i;
			map.remove((prizeShi+20)+"");
		}
		
		if(map.get((prizeBai+40)+"")!=null) {
			bai[prizeBai-1] = i;
			map.remove((prizeBai+40)+"");
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
	
	public static LuckyRacingQ3 fromJsonToLuckyRacingQ3(String json) {
		return new JSONDeserializer<LuckyRacingQ3>().use(null,
				LuckyRacingQ3.class).deserialize(json);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i:bai) {
			sb.append(i).append(",");
		}
		if(sb.toString().endsWith(",")) {
			sb.deleteCharAt(sb.length()-1);
		}
		sb.append(";");
		
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
