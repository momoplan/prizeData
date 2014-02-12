package com.ruyicai.prizedata.hotcold.cqhappy10;

import java.util.List;

import com.ruyicai.prizedata.domain.PrizeInfo;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

/**
 * 选一红投
 * @author liuhongxing
 *
 */
public class CqHappytenH1HC {

	public static final String KEY = "F47109HC_H1";
	
	private int[] hc = new int[2];

	public int[] getHc() {
		return hc;
	}

	public void setHc(int[] hc) {
		this.hc = hc;
	}
	
	
	public void onPrize(PrizeInfo prizeInfo) {
		int prizeGe = Integer.parseInt(prizeInfo.getWinbasecode().substring(0, 2));
		if(prizeGe==19) {
			hc[0] = hc[0]+1;
		}else if(prizeGe==20) {
			hc[1] = hc[1]+1;
		}
	}
	
	public CqHappytenH1HC refresh(List<PrizeInfo> prizeInfos,int countBatch) {
		for(PrizeInfo prizeInfo:prizeInfos) {
			onPrize(prizeInfo);
		}
		return this;
	}
	
	public String toJson() {
		return new JSONSerializer().exclude("*.class").deepSerialize(this);
	}
	
	public static CqHappytenH1HC fromJsonToCqHappytenH1HC(String json) {
		return new JSONDeserializer<CqHappytenH1HC>().use(null,
				CqHappytenH1HC.class).deserialize(json);

	}
	
}
