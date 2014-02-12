package com.ruyicai.prizedata.hotcold.nmkuai3;

import java.util.Arrays;
import java.util.List;

import com.ruyicai.prizedata.domain.PrizeInfo;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

//三连号通选
public class NmK350HC {

	public static final String KEY = "F47107HC_50";
	
	private int[] hc = new int[1];
	
	public int[] getHc() {
		return hc;
	}

	public void setHc(int[] hc) {
		this.hc = hc;
	}


	public void onPrize(PrizeInfo prizeInfo) {
		int[] wins = new int[] {
				Integer.parseInt(prizeInfo.getWinbasecode().substring(0, 2)),
				Integer.parseInt(prizeInfo.getWinbasecode().substring(2, 4)),
				Integer.parseInt(prizeInfo.getWinbasecode().substring(4, 6)) };
		
		Arrays.sort(wins);
		if (wins[2]-wins[1]==1&&wins[1]-wins[0]==1) {
			hc[0] = hc[0] + 1;
		}

		
	}
	
	
	/**
	 * 指定期数刷新冷热号
	 * @param prizeInfoService
	 * @param countBatch 指定期数
	 * @return
	 */
	public NmK350HC refresh(List<PrizeInfo> prizeInfos,int countBatch) {
		for(PrizeInfo prizeInfo:prizeInfos) {
			onPrize(prizeInfo);
		}
		return this;
	}
	
	
	
	public String toJson() {
		return new JSONSerializer().exclude("*.class").deepSerialize(this);
	}
	
	public static NmK350HC fromJsonToNmK350HC(String json) {
		return new JSONDeserializer<NmK350HC>().use(null,
				NmK350HC.class).deserialize(json);

	}
	
}
