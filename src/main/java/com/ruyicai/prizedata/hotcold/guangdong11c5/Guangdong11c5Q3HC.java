package com.ruyicai.prizedata.hotcold.guangdong11c5;

import java.util.List;

import com.ruyicai.prizedata.domain.PrizeInfo;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

public class Guangdong11c5Q3HC {

	public static final String KEY = "T01014HC_Q3";
	
	private int[] ge = new int[11];
	private int[] shi = new int[11];
	private int[] bai = new int[11];

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

	public int[] getBai() {
		return bai;
	}

	public void setBai(int[] bai) {
		this.bai = bai;
	}


	public void onPrize(PrizeInfo prizeInfo) {
		int prizeBai = Integer.parseInt(prizeInfo.getWinbasecode().substring(0, 2));
		int prizeShi = Integer.parseInt(prizeInfo.getWinbasecode().substring(2, 4));
		int prizeGe = Integer.parseInt(prizeInfo.getWinbasecode().substring(4, 6));
		ge[prizeGe-1] = ge[prizeGe-1] + 1;
		shi[prizeShi-1] = shi[prizeShi-1] + 1;
		bai[prizeBai-1] = bai[prizeBai-1] + 1;
	}
	
	
	public Guangdong11c5Q3HC refresh(List<PrizeInfo> prizeInfos,int countBatch) {
		for(PrizeInfo prizeInfo:prizeInfos) {
			onPrize(prizeInfo);
		}
		return this;
	}
	
	
	public String toJson() {
		return new JSONSerializer().exclude("*.class").deepSerialize(this);
	}
	
	public static Guangdong11c5Q3HC fromJsonToGuangdong11c5Q3HC(String json) {
		return new JSONDeserializer<Guangdong11c5Q3HC>().use(null,
				Guangdong11c5Q3HC.class).deserialize(json);

	}
}
