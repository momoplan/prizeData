package com.ruyicai.prizedata.hotcold.qilecai;

import java.util.List;

import com.ruyicai.prizedata.domain.PrizeInfo;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

public class QiLeCaiXHC {

	public static final String KEY = "F47102HC_X";
	
	private int[] hc = new int[30];

	public int[] getHc() {
		return hc;
	}

	public void setHc(int[] hc) {
		this.hc = hc;
	}
	
	
	
	public void onPrize(PrizeInfo prizeInfo) {
		String winbase = prizeInfo.getWinbasecode();
		int[] winPrize = { Integer.parseInt(winbase.substring(0, 2)),
				Integer.parseInt(winbase.substring(2, 4)),
				Integer.parseInt(winbase.substring(4, 6)),
				Integer.parseInt(winbase.substring(6, 8)),
				Integer.parseInt(winbase.substring(8, 10)),
				Integer.parseInt(winbase.substring(10, 12)),
				Integer.parseInt(winbase.substring(12, 14))};
		
		for(int ball:winPrize) {
			hc[ball-1] = hc[ball-1] + 1;
		}
	}
	
	
	/**
	 * 指定期数刷新冷热号
	 * @param prizeInfoService
	 * @param countBatch 指定期数
	 * @return
	 */
	public QiLeCaiXHC refresh(List<PrizeInfo> prizeInfos,int countBatch) {
		for(PrizeInfo prizeInfo:prizeInfos) {
			onPrize(prizeInfo);
		}
		return this;
	}
	
	
	public String toJson() {
		return new JSONSerializer().exclude("*.class").deepSerialize(this);
	}

	public static QiLeCaiXHC fromJsonToQiLeCaiXHC(String json) {
		return new JSONDeserializer<QiLeCaiXHC>().use(null,
				QiLeCaiXHC.class).deserialize(json);

	}
}
