package com.ruyicai.prizedata.hotcold.jiangxi11c5;

import java.util.List;

import com.ruyicai.prizedata.domain.PrizeInfo;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

public class Jiangxi11c5Q1HC {

	public static final String KEY = "T01010HC_Q1";
	
	private int[] ge = new int[11];

	public int[] getGe() {
		return ge;
	}

	public void setGe(int[] ge) {
		this.ge = ge;
	}


	public void onPrize(PrizeInfo prizeInfo) {
		int prizeGe = Integer.parseInt(prizeInfo.getWinbasecode().substring(0, 2));
		ge[prizeGe-1] = ge[prizeGe-1] + 1;
	}
	
	
	public Jiangxi11c5Q1HC refresh(List<PrizeInfo> prizeInfos,int countBatch) {
		for(PrizeInfo prizeInfo:prizeInfos) {
			onPrize(prizeInfo);
		}
		return this;
	}
	
	
	public String toJson() {
		return new JSONSerializer().exclude("*.class").deepSerialize(this);
	}
	
	public static Jiangxi11c5Q1HC fromJsonToJiangxi11c5Q1HC(String json) {
		return new JSONDeserializer<Jiangxi11c5Q1HC>().use(null,
				Jiangxi11c5Q1HC.class).deserialize(json);

	}
}
