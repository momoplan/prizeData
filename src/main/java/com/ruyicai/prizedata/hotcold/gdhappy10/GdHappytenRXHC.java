package com.ruyicai.prizedata.hotcold.gdhappy10;

import java.util.List;

import com.ruyicai.prizedata.domain.PrizeInfo;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

/**
 * 任选2、3、4、5 选二连直  选二连组  选三前组 
 * @author liuhongxing
 *
 */
public class GdHappytenRXHC {

	public static final String KEY = "T01015HC_RX";
	
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
	
	
	public GdHappytenRXHC refresh(List<PrizeInfo> prizeInfos,int countBatch) {
		for(PrizeInfo prizeInfo:prizeInfos) {
			onPrize(prizeInfo);
		}
		return this;
	}
	
	public String toJson() {
		return new JSONSerializer().exclude("*.class").deepSerialize(this);
	}
	
	public static GdHappytenRXHC fromJsonToGdHappytenRXHC(String json) {
		return new JSONDeserializer<GdHappytenRXHC>().use(null,
				GdHappytenRXHC.class).deserialize(json);

	}
	
	
}
