package com.ruyicai.prizedata.hotcold.daletou;

import java.util.List;

import com.ruyicai.prizedata.domain.PrizeInfo;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

public class DaLeTouXHC {

	public static final String KEY = "T01001HC_X";
	
	
	private int[] qian = new int[35];
	private int[] hou = new int[12];
	public int[] getQian() {
		return qian;
	}
	public void setQian(int[] qian) {
		this.qian = qian;
	}
	public int[] getHou() {
		return hou;
	}
	public void setHou(int[] hou) {
		this.hou = hou;
	}
	
	
	
	
	public void onPrize(PrizeInfo prizeInfo) {
		String winbase = prizeInfo.getWinbasecode();
		String winspecial = prizeInfo.getWinspecialcode();
		int[] qianPrize = { Integer.parseInt(winbase.substring(0, 2)),
				Integer.parseInt(winbase.substring(2, 4)),
				Integer.parseInt(winbase.substring(4, 6)),
				Integer.parseInt(winbase.substring(6, 8)),
				Integer.parseInt(winbase.substring(8, 10))};
		int[] houPrize = {Integer.parseInt(winspecial.substring(0, 2)),
				Integer.parseInt(winspecial.substring(2, 4))};
		
		for(int qianBall:qianPrize) {
			qian[qianBall-1] = qian[qianBall-1] + 1;
		}
		
		for(int houBall:houPrize) {
			hou[houBall-1] = hou[houBall-1] + 1;
		}
	}
	
	
	/**
	 * 指定期数刷新冷热号
	 * @param prizeInfoService
	 * @param countBatch 指定期数
	 * @return
	 */
	public DaLeTouXHC refresh(List<PrizeInfo> prizeInfos,int countBatch) {
		for(PrizeInfo prizeInfo:prizeInfos) {
			onPrize(prizeInfo);
		}
		return this;
	}
	
	
	
	public String toJson() {
		return new JSONSerializer().exclude("*.class").deepSerialize(this);
	}

	public static DaLeTouXHC fromJsonToDaLeTouXHC(String json) {
		return new JSONDeserializer<DaLeTouXHC>().use(null,
				DaLeTouXHC.class).deserialize(json);

	}
	
	
}
