package com.ruyicai.prizedata.missvalue.nmkuai3;

import java.util.List;
import java.util.Map;

import com.ruyicai.prizedata.domain.PrizeInfo;
import com.ruyicai.prizedata.service.PrizeInfoService;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

/**
 * 三同号通选
 * @author liuhongxing
 *
 */
public class NmK340 {

	public static final String KEY = "F47107MV_40";
	private static final String LOTNO = "F47107";

	private int[] miss = new int[1];

	public int[] getMiss() {
		return miss;
	}

	public void setMiss(int[] miss) {
		this.miss = miss;
	}
	
	
	public void onPrize(PrizeInfo prizeInfo) {
		String ge = prizeInfo.getWinbasecode().substring(4, 6);
		String shi = prizeInfo.getWinbasecode().substring(2, 4);
		String bai = prizeInfo.getWinbasecode().substring(0, 2);
		plusOne(miss);
		
		if(ge.equals(shi)&&shi.equals(bai)) {
			miss[0] = 0;
		}
	}
	
	public NmK340 refresh(PrizeInfoService prizeInfoService,int index) {
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
				if(this.refresh40(p.getWinbasecode(), null, i)){
					i++;
				}else{
					flag = false;
					break;
				}
			}
		}
		return this;
	}
	
	
	
	private boolean refresh40(String win,Map<String, Object> map,int i) {
		String ge = win.substring(4, 6);
		String shi = win.substring(2, 4);
		String bai = win.substring(0, 2);
		
		if(ge.equals(shi)&&shi.equals(bai)) {
			miss[0] = i;
			return false;
		}
		return true;
	}
	
	
	
	private void plusOne(int[] miss) {
		for(int i = 0;i < miss.length;i++) {
			miss[i] = miss[i] + 1;
		}
	}
	
	
	public String toJson() {
		return new JSONSerializer().exclude("*.class").deepSerialize(this);
	}

	public static NmK340 fromJsonToNmK340(String json) {
		return new JSONDeserializer<NmK340>().use(null, NmK340.class)
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
