package com.ruyicai.prizedata.hotcold.jiangxi11c5;

import java.util.List;

import com.ruyicai.prizedata.domain.PrizeInfo;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

public class Jiangxi11c5RXHC {

	public static final String KEY = "T01010HC_RX";

	private int[] hc = new int[11];

	public int[] getHc() {
		return hc;
	}

	public void setHc(int[] hc) {
		this.hc = hc;
	}

	public void onPrize(PrizeInfo prizeInfo) {
		String win = prizeInfo.getWinbasecode();
		int[] winInt = { Integer.parseInt(win.substring(0, 2)),
				Integer.parseInt(win.substring(2, 4)),
				Integer.parseInt(win.substring(4, 6)),
				Integer.parseInt(win.substring(6, 8)),
				Integer.parseInt(win.substring(8, 10)) };

		for (int wina : winInt) {
			hc[wina - 1] = hc[wina - 1] + 1;
		}
	}

	public Jiangxi11c5RXHC refresh(List<PrizeInfo> prizeInfos, int countBatch) {
		for (PrizeInfo prizeInfo : prizeInfos) {
			onPrize(prizeInfo);
		}
		return this;
	}

	public String toJson() {
		return new JSONSerializer().exclude("*.class").deepSerialize(this);
	}

	public static Jiangxi11c5RXHC fromJsonToJiangxi11c5RXHC(String json) {
		return new JSONDeserializer<Jiangxi11c5RXHC>().use(null,
				Jiangxi11c5RXHC.class).deserialize(json);

	}
}
