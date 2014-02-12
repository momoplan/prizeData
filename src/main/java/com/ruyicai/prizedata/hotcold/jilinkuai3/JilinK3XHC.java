package com.ruyicai.prizedata.hotcold.jilinkuai3;

import java.util.List;

import com.ruyicai.prizedata.domain.PrizeInfo;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

public class JilinK3XHC {

	public static final String KEY = "F47108HC_X";
	
	private int[] bai = new int[6];
	private int[] shi = new int[6];
	private int[] ge = new int[6];
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
		int geint = Integer.parseInt(prizeInfo.getWinbasecode().substring(4, 6));
		int shiint = Integer.parseInt(prizeInfo.getWinbasecode().substring(2, 4));
		int baiint = Integer.parseInt(prizeInfo.getWinbasecode().substring(0, 2));
		
		bai[baiint-1] = bai[baiint-1] + 1;
		shi[shiint-1] = shi[shiint-1] + 1;
		ge[geint-1] = ge[geint-1] + 1;
		
	}
	
	
	/**
	 * 指定期数刷新冷热号
	 * @param prizeInfoService
	 * @param countBatch 指定期数
	 * @return
	 */
	public JilinK3XHC refresh(List<PrizeInfo> prizeInfos,int countBatch) {
		for(PrizeInfo prizeInfo:prizeInfos) {
			onPrize(prizeInfo);
		}
		return this;
	}
	
	
	
	public String toJson() {
		return new JSONSerializer().exclude("*.class").deepSerialize(this);
	}
	
	public static JilinK3XHC fromJsonToJilinK3XHC(String json) {
		return new JSONDeserializer<JilinK3XHC>().use(null,
				JilinK3XHC.class).deserialize(json);

	}
	
}
