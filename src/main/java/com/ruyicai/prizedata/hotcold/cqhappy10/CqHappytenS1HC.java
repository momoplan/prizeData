package com.ruyicai.prizedata.hotcold.cqhappy10;

import java.util.List;

import com.ruyicai.prizedata.domain.PrizeInfo;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

/**
 * 选一数投
 * 
 * @author liuhongxing
 * 
 */
public class CqHappytenS1HC {

	public static final String KEY = "F47109HC_S1";
	
	private int[] hc = new int[18];

	public int[] getHc() {
		return hc;
	}

	public void setHc(int[] hc) {
		this.hc = hc;
	}
	
	public void onPrize(PrizeInfo prizeInfo) {
		int prizeGe = Integer.parseInt(prizeInfo.getWinbasecode().substring(0, 2));
		if(prizeGe<=18) {
			hc[prizeGe-1] = hc[prizeGe-1] + 1;
		}
	}
	
	
	public CqHappytenS1HC refresh(List<PrizeInfo> prizeInfos,int countBatch) {
		for(PrizeInfo prizeInfo:prizeInfos) {
			onPrize(prizeInfo);
		}
		return this;
	}
	
	public String toJson() {
		return new JSONSerializer().exclude("*.class").deepSerialize(this);
	}
	
	public static CqHappytenS1HC fromJsonToCqHappytenS1HC(String json) {
		return new JSONDeserializer<CqHappytenS1HC>().use(null,
				CqHappytenS1HC.class).deserialize(json);

	}
}
