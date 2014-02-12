package com.ruyicai.prizedata.missvalue.cqhappyten;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruyicai.prizedata.domain.PrizeInfo;
import com.ruyicai.prizedata.service.PrizeInfoService;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

/**
 * 任选2、3、4、5 选二连直  选二连组  选三前组 
 * 
 * @author liuhongxing
 * 
 */
public class CqHappytenRX {

	public static final String KEY = "F47109MV_RX";
	private static final String LOTNO = "F47109";

	private int[] miss = new int[20];

	public int[] getMiss() {
		return miss;
	}

	public void setMiss(int[] miss) {
		this.miss = miss;
	}

	public void onPrize(PrizeInfo prizeInfo) {
		String win = prizeInfo.getWinbasecode();
		int[] winInt = { Integer.parseInt(win.substring(0, 2)), Integer.parseInt(win.substring(2, 4)),
				Integer.parseInt(win.substring(4, 6)), Integer.parseInt(win.substring(6, 8)),
				Integer.parseInt(win.substring(8, 10)), Integer.parseInt(win.substring(10, 12)),
				Integer.parseInt(win.substring(12, 14)), Integer.parseInt(win.substring(14, 16)) };

		plusOne(miss);
		for (int i = 0; i < winInt.length; i++) {
			miss[winInt[i] - 1] = 0;
		}

	}
	
	
	
	public CqHappytenRX refresh(PrizeInfoService prizeInfoService,int index) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (int j = 1; j <= 20; j++) {
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
		int[] winInt = { Integer.parseInt(win.substring(0, 2)), Integer.parseInt(win.substring(2, 4)),
				Integer.parseInt(win.substring(4, 6)), Integer.parseInt(win.substring(6, 8)),
				Integer.parseInt(win.substring(8, 10)), Integer.parseInt(win.substring(10, 12)),
				Integer.parseInt(win.substring(12, 14)), Integer.parseInt(win.substring(14, 16)) };
		
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

	public String toJson() {
		return new JSONSerializer().exclude("*.class").deepSerialize(this);
	}

	public static CqHappytenRX fromJsonToCqHappytenRX(String json) {
		return new JSONDeserializer<CqHappytenRX>().use(null,
				CqHappytenRX.class).deserialize(json);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i : miss) {
			sb.append(i).append(",");
		}
		if (sb.toString().endsWith(",")) {
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}

	private void plusOne(int[] miss) {
		for (int i = 0; i < miss.length; i++) {
			miss[i] = miss[i] + 1;
		}
	}

}
