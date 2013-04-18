package com.ruyicai.prizedata.hotcold.threed;

import java.util.List;

import com.ruyicai.prizedata.domain.PrizeInfo;
import com.ruyicai.prizedata.service.PrizeInfoService;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

public class ThreeDXHC {

	public static final String KEY = "F47103HC_X";
	private static final String LOTNO = "F47103";
	
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
	public ThreeDXHC refresh(PrizeInfoService prizeInfoService,int countBatch) {
		List<PrizeInfo> prizeInfos = prizeInfoService.find(LOTNO, 0, countBatch);
		for(PrizeInfo prizeInfo:prizeInfos) {
			onPrize(prizeInfo);
		}
		return this;
	}
	
	
	
	
	
	private void onPrize(PrizeInfo prizeInfo) {
		String win = prizeInfo.getWinbasecode();
		int[] winInt = { Integer.parseInt(win.substring(0, 2)),
				Integer.parseInt(win.substring(2, 4)),
				Integer.parseInt(win.substring(4, 6)) };
		
		bai[winInt[0]] = bai[winInt[0]] + 1;
		shi[winInt[1]] = shi[winInt[1]] + 1;
		ge[winInt[2]] = ge[winInt[2]] + 1;
		
	}
	public String toJson() {
		return new JSONSerializer().exclude("*.class").deepSerialize(this);
	}

	public static ThreeDXHC fromJsonToThreeDXHC(String json) {
		return new JSONDeserializer<ThreeDXHC>().use(null, ThreeDXHC.class)
				.deserialize(json);
	}
}
