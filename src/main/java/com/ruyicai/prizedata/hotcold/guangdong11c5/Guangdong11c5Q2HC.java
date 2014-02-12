package com.ruyicai.prizedata.hotcold.guangdong11c5;

import java.util.List;

import com.ruyicai.prizedata.domain.PrizeInfo;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

public class Guangdong11c5Q2HC {

	public static final String KEY = "T01014HC_Q2";
	
	private int[] ge = new int[11];
	private int[] shi = new int[11];

	public int[] getGe() {
		return ge;
	}

	public void setGe(int[] ge) {
		this.ge = ge;
	}

	public int[] getShi() {
		return shi;
	}

	public void setShi(int[] shi) {
		this.shi = shi;
	}

	public void onPrize(PrizeInfo prizeInfo) {
		int prizeShi = Integer.parseInt(prizeInfo.getWinbasecode().substring(0, 2));
		int prizeGe = Integer.parseInt(prizeInfo.getWinbasecode().substring(2, 4));
		ge[prizeGe-1] = ge[prizeGe-1] + 1;
		shi[prizeShi-1] = shi[prizeShi-1] + 1;
		
	}
	
	
	public Guangdong11c5Q2HC refresh(List<PrizeInfo> prizeInfos,int countBatch) {
		for(PrizeInfo prizeInfo:prizeInfos) {
			onPrize(prizeInfo);
		}
		return this;
	}
	
	
	public String toJson() {
		return new JSONSerializer().exclude("*.class").deepSerialize(this);
	}
	
	public static Guangdong11c5Q2HC fromJsonToGuangdong11c5Q2HC(String json) {
		return new JSONDeserializer<Guangdong11c5Q2HC>().use(null,
				Guangdong11c5Q2HC.class).deserialize(json);

	}
}
