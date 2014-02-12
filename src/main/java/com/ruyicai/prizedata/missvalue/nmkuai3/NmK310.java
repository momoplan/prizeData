package com.ruyicai.prizedata.missvalue.nmkuai3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruyicai.prizedata.domain.PrizeInfo;
import com.ruyicai.prizedata.service.PrizeInfoService;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
/**
 * 快三
 * 和值
 * @author liuhongxing
 *
 */
public class NmK310 {

	public static final String KEY = "F47107MV_10";
	private static final String LOTNO = "F47107";

	private int[] miss = new int[14];

	public int[] getMiss() {
		return miss;
	}

	public void setMiss(int[] miss) {
		this.miss = miss;
	}

	
	public void onPrize(PrizeInfo prizeInfo) {
		int ge = Integer.parseInt(prizeInfo.getWinbasecode().substring(4, 6));
		int shi = Integer.parseInt(prizeInfo.getWinbasecode().substring(2, 4));
		int bai = Integer.parseInt(prizeInfo.getWinbasecode().substring(0, 2));
		plusOne(miss);
		if(ge+shi+bai>=4&&ge+shi+bai<=17) {
			miss[ge+shi+bai-4] = 0;
		}
		
	}
	
	
	public NmK310 refresh(PrizeInfoService prizeInfoService,int index) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (int j = 4; j <= 17; j++) {
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
				if(this.refresh10(p.getWinbasecode(), map, i)){
					i++;
				}else{
					flag = false;
					break;
				}
			}
		}
		return this;
	}
	
	
	
	private boolean refresh10(String win,Map<String, Object> map,int i) {
		int ge = Integer.parseInt(win.substring(4, 6));
		int shi = Integer.parseInt(win.substring(2, 4));
		int bai = Integer.parseInt(win.substring(0, 2));
		
		if(ge+shi+bai>=4&&ge+shi+bai<=17) {
			if(map.get((ge+shi+bai)+"")!=null) {
				miss[ge+shi+bai-4] = i;
				map.remove((ge+shi+bai)+"");
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

	public static NmK310 fromJsonToNmK310(String json) {
		return new JSONDeserializer<NmK310>().use(null, NmK310.class)
				.deserialize(json);
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
