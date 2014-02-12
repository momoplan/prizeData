package com.ruyicai.prizedata.hotcold.cqhappy10;

import java.util.List;

import com.ruyicai.prizedata.domain.PrizeInfo;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

/**
 * 选三前直
 * @author liuhongxing
 *
 */
public class CqHappytenQ3HC {

	public static final String KEY = "F47109HC_Q3";
	private int[] bai = new int[20];
	private int[] shi = new int[20];
	private int[] ge = new int[20];
	public int[] getBai() {
		return bai;
	}
	public void setBai(int[] bai) {
		this.bai = bai;
	}
	public int[] getShi() {
		return shi;
	}
	public void setShi(int[] shi) {
		this.shi = shi;
	}
	public int[] getGe() {
		return ge;
	}
	public void setGe(int[] ge) {
		this.ge = ge;
	}
	
	public void onPrize(PrizeInfo prizeInfo) {
		int prizeBai = Integer.parseInt(prizeInfo.getWinbasecode().substring(0, 2));
		int prizeShi = Integer.parseInt(prizeInfo.getWinbasecode().substring(2, 4));
		int prizeGe = Integer.parseInt(prizeInfo.getWinbasecode().substring(4, 6));
		bai[prizeBai-1] = bai[prizeBai-1] + 1;
		shi[prizeShi-1] = shi[prizeShi-1] + 1;
		ge[prizeGe-1] = ge[prizeGe-1] + 1;
	}
	
	public CqHappytenQ3HC refresh(List<PrizeInfo> prizeInfos,int countBatch) {
		for(PrizeInfo prizeInfo:prizeInfos) {
			onPrize(prizeInfo);
		}
		return this;
	}
	
	public String toJson() {
		return new JSONSerializer().exclude("*.class").deepSerialize(this);
	}
	
	public static CqHappytenQ3HC fromJsonToCqHappytenQ3HC(String json) {
		return new JSONDeserializer<CqHappytenQ3HC>().use(null,
				CqHappytenQ3HC.class).deserialize(json);

	}
}
