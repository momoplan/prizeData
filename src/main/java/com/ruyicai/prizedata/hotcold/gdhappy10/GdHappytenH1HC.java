package com.ruyicai.prizedata.hotcold.gdhappy10;

import java.util.List;

import com.ruyicai.prizedata.domain.PrizeInfo;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

/**
 * 选一红投
 * @author liuhongxing
 *
 */
public class GdHappytenH1HC {

	public static final String KEY = "T01015HC_H1";
	
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
	
	public GdHappytenH1HC refresh(List<PrizeInfo> prizeInfos,int countBatch) {
		for(PrizeInfo prizeInfo:prizeInfos) {
			onPrize(prizeInfo);
		}
		return this;
	}
	
	public String toJson() {
		return new JSONSerializer().exclude("*.class").deepSerialize(this);
	}
	
	public static GdHappytenH1HC fromJsonToGdHappytenH1HC(String json) {
		return new JSONDeserializer<GdHappytenH1HC>().use(null,
				GdHappytenH1HC.class).deserialize(json);

	}
	
}
