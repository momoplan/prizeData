package com.ruyicai.prizedata.hotcold.shandong11c5;

import java.util.List;

import com.ruyicai.prizedata.domain.PrizeInfo;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

public class Shandong11c5Q2ZHC {

	public static final String KEY = "T01012HC_Q2Z";

	private int[] hc = new int[11];

	public int[] getHc() {
		return hc;
	}

	public void setHc(int[] hc) {
		this.hc = hc;
	}

	public void onPrize(PrizeInfo prizeInfo) {
		int prizeShi = Integer.parseInt(prizeInfo.getWinbasecode().substring(0,
				2));
		int prizeGe = Integer.parseInt(prizeInfo.getWinbasecode().substring(2,
				4));

		hc[prizeShi - 1] = hc[prizeShi - 1] + 1;
		hc[prizeGe - 1] = hc[prizeGe - 1] + 1;
	}

	public Shandong11c5Q2ZHC refresh(List<PrizeInfo> prizeInfos, int countBatch) {
		for (PrizeInfo prizeInfo : prizeInfos) {
			onPrize(prizeInfo);
		}
		return this;
	}

	public String toJson() {
		return new JSONSerializer().exclude("*.class").deepSerialize(this);
	}

	public static Shandong11c5Q2ZHC fromJsonToShandong11c5Q2ZHC(String json) {
		return new JSONDeserializer<Shandong11c5Q2ZHC>().use(null,
				Shandong11c5Q2ZHC.class).deserialize(json);

	}
}
