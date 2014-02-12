package com.ruyicai.prizedata.hotcold.pailiesan;

import java.util.List;

import com.ruyicai.prizedata.domain.PrizeInfo;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

public class PaiLieSanXHC {

	public static final String KEY = "T01002HC_X";
	
	private int[] bai = new int[10];
	private int[] shi = new int[10];
	private int[] ge = new int[10];
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
	
	/**
	 * 指定期数刷新冷热号
	 * @param prizeInfoService
	 * @param countBatch 指定期数
	 * @return
	 */
	public PaiLieSanXHC refresh(List<PrizeInfo> prizeInfos,int countBatch) {
		for(PrizeInfo prizeInfo:prizeInfos) {
			onPrize(prizeInfo);
		}
		return this;
	}
	
	
	private void onPrize(PrizeInfo prizeInfo) {
		String win = prizeInfo.getWinbasecode();
		int[] winInt = { Integer.parseInt(win.charAt(0) + ""),
				Integer.parseInt(win.charAt(1) + ""),
				Integer.parseInt(win.charAt(2) + "") };
		
		bai[winInt[0]] = bai[winInt[0]] + 1;
		shi[winInt[1]] = shi[winInt[1]] + 1;
		ge[winInt[2]] = ge[winInt[2]] + 1;
		
	}
	
	
	public String toJson() {
		return new JSONSerializer().exclude("*.class").deepSerialize(this);
	}

	public static PaiLieSanXHC fromJsonToPaiLieSanXHC(String json) {
		return new JSONDeserializer<PaiLieSanXHC>().use(null, PaiLieSanXHC.class)
				.deserialize(json);
	}
	
	
	
}
