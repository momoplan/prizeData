package com.ruyicai.prizedata.missvalue.guangdong11c5;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruyicai.prizedata.domain.PrizeInfo;
import com.ruyicai.prizedata.service.PrizeInfoService;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
/**
 * 任选2345678(选号栏)
 * @author liuhongxing
 *
 */
public class Guangdong11c5RX {

	public static final String KEY = "T01014MV_RX";
	private static final String LOTNO = "T01014";

	private int[] miss = new int[11];

	public void setMiss(int[] miss) {
		this.miss = miss;
	}

	public int[] getMiss() {
		return miss;
	}

	public void onPrize(PrizeInfo prizeInfo) {
		String win = prizeInfo.getWinbasecode();
		int[] winInt = { Integer.parseInt(win.substring(0, 2)),
				Integer.parseInt(win.substring(2, 4)),
				Integer.parseInt(win.substring(4, 6)),
				Integer.parseInt(win.substring(6, 8)),
				Integer.parseInt(win.substring(8, 10)) };
		
		plusOne();
		for(int i = 0;i<winInt.length;i++) {
			miss[winInt[i]-1] = 0;
		}
		
	}
	
	
	
	public Guangdong11c5RX refresh(PrizeInfoService prizeInfoService,int index) {
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
				if(this.refreshRX(p.getWinbasecode(), map, i)){
					i++;
				}else{
					flag = false;
					break;
				}
			}
		}
		return this;
	}
	
	private boolean refreshRX(String win,Map<String, Object> map,int i) {
		int[] winInt = { Integer.parseInt(win.substring(0, 2)),
				Integer.parseInt(win.substring(2, 4)),
				Integer.parseInt(win.substring(4, 6)),
				Integer.parseInt(win.substring(6, 8)),
				Integer.parseInt(win.substring(8, 10)) };
		
		for(int j:winInt) {
			if(map.get(j+"")!=null) {
				miss[j-1] = i;
				map.remove(j+"");
			}
		}
		
		if(map.isEmpty()) {
			return false;
		}else {
			return true;
		}
		
	}

	
	private void plusOne() {
		for(int i = 0;i < miss.length;i++) {
			miss[i] = miss[i] + 1;
		}
	}
	
	
	public String toJson() {
		return new JSONSerializer().exclude("*.class").deepSerialize(this);
	}

	public static Guangdong11c5RX fromJsonToGuangdong11c5RX(String json) {
		return new JSONDeserializer<Guangdong11c5RX>().use(null,
				Guangdong11c5RX.class).deserialize(json);
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
