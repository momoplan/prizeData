package com.ruyicai.prizedata.hotcold.cqhappy10;

import java.util.List;

import com.ruyicai.prizedata.domain.PrizeInfo;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

/**
 * 任选2、3、4、5 选二连直  选二连组  选三前组 
 * @author liuhongxing
 *
 */
public class CqHappytenRXHC {

	public static final String KEY = "F47109HC_RX";
	
	private int[] hc = new int[20];

	public int[] getHc() {
		return hc;
	}

	public void setHc(int[] hc) {
		this.hc = hc;
	}
	
	public void onPrize(PrizeInfo prizeInfo) {
		String win = prizeInfo.getWinbasecode();
		int[] winInt = { Integer.parseInt(win.substring(0, 2)), Integer.parseInt(win.substring(2, 4)),
				Integer.parseInt(win.substring(4, 6)), Integer.parseInt(win.substring(6, 8)),
				Integer.parseInt(win.substring(8, 10)), Integer.parseInt(win.substring(10, 12)),
				Integer.parseInt(win.substring(12, 14)), Integer.parseInt(win.substring(14, 16)) };

		for (int i = 0; i < winInt.length; i++) {
			hc[winInt[i] - 1] = hc[winInt[i] - 1] + 1;
		}

	}
	
	
	public CqHappytenRXHC refresh(List<PrizeInfo> prizeInfos,int countBatch) {
		for(PrizeInfo prizeInfo:prizeInfos) {
			onPrize(prizeInfo);
		}
		return this;
	}
	
	public String toJson() {
		return new JSONSerializer().exclude("*.class").deepSerialize(this);
	}
	
	public static CqHappytenRXHC fromJsonToCqHappytenRXHC(String json) {
		return new JSONDeserializer<CqHappytenRXHC>().use(null,
				CqHappytenRXHC.class).deserialize(json);

	}
	
	
}
