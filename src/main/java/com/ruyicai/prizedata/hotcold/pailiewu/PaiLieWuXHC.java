package com.ruyicai.prizedata.hotcold.pailiewu;

import java.util.List;

import com.ruyicai.prizedata.domain.PrizeInfo;
import com.ruyicai.prizedata.service.PrizeInfoService;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

public class PaiLieWuXHC {

	public static final String KEY = "T01011HC_X";
	private static final String LOTNO = "T01011";
	
	private int[] wan = new int[10];
	private int[] qian = new int[10];
	private int[] bai = new int[10];
	private int[] shi = new int[10];
	private int[] ge = new int[10];
	public int[] getWan() {
		return wan;
	}
	public void setWan(int[] wan) {
		this.wan = wan;
	}
	public int[] getQian() {
		return qian;
	}
	public void setQian(int[] qian) {
		this.qian = qian;
	}
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
		String win = prizeInfo.getWinbasecode();
		int[] winInt = { Integer.parseInt(win.charAt(0) + ""),
				Integer.parseInt(win.charAt(1) + ""),
				Integer.parseInt(win.charAt(2) + ""),
				Integer.parseInt(win.charAt(3) + ""),
				Integer.parseInt(win.charAt(4) + "") };
		
		wan[winInt[0]] = wan[winInt[0]] + 1;
		qian[winInt[1]] = qian[winInt[1]] + 1;
		bai[winInt[2]] = bai[winInt[2]] + 1;
		shi[winInt[3]] = shi[winInt[3]] + 1;
		ge[winInt[4]] = ge[winInt[4]] + 1;
		
	}
	
	
	/**
	 * 指定期数刷新冷热号
	 * @param prizeInfoService
	 * @param countBatch 指定期数
	 * @return
	 */
	public PaiLieWuXHC refresh(PrizeInfoService prizeInfoService,int countBatch) {
		List<PrizeInfo> prizeInfos = prizeInfoService.find(LOTNO, 0, countBatch);
		for(PrizeInfo prizeInfo:prizeInfos) {
			onPrize(prizeInfo);
		}
		return this;
	}
	
	
	
	public String toJson() {
		return new JSONSerializer().exclude("*.class").deepSerialize(this);
	}

	public static PaiLieWuXHC fromJsonToPaiLieWuXHC(String json) {
		return new JSONDeserializer<PaiLieWuXHC>().use(null,
				PaiLieWuXHC.class).deserialize(json);

	}
	
}
