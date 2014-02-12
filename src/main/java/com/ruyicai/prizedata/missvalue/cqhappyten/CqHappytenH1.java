package com.ruyicai.prizedata.missvalue.cqhappyten;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruyicai.prizedata.domain.PrizeInfo;
import com.ruyicai.prizedata.service.PrizeInfoService;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

/**
 * 选一红投
 * @author liuhongxing
 *
 */
public class CqHappytenH1 {

	public static final String KEY = "F47109MV_H1";
	private static final String LOTNO = "F47109";
	
	private int[] miss = new int[2];

	public int[] getMiss() {
		return miss;
	}

	public void setMiss(int[] miss) {
		this.miss = miss;
	}
	
	
	public void onPrize(PrizeInfo prizeInfo) {
		int prizeGe = Integer.parseInt(prizeInfo.getWinbasecode().substring(0, 2));
		plusOne(miss);
		if(prizeGe==19) {
			miss[0] = 0;
		}else if(prizeGe==20) {
			miss[1]=0;
		}
	}
	
	
	public CqHappytenH1 refresh(PrizeInfoService prizeInfoService,int index) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(19+"", 0);
		map.put(20+"", 0);

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
				if(this.refreshH1(p.getWinbasecode(), map, i)){
					i++;
				}else{
					flag = false;
					break;
				}
			}
		}
		return this;
	}
	
	
	private boolean refreshH1(String win,Map<String, Object> map,int i) {
		int prizeGe = Integer.parseInt(win.substring(0, 2));
		
		if(map.get(prizeGe+"")!=null) {
			if(prizeGe==19) {
				miss[0]=i;
			}else if(prizeGe==20) {
				miss[1]=i;
			}
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
	
	public static CqHappytenH1 fromJsonToCqHappytenH1(String json) {
		return new JSONDeserializer<CqHappytenH1>().use(null,
				CqHappytenH1.class).deserialize(json);
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
