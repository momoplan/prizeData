package com.ruyicai.prizedata.hotcold.shuangseqiu;

import java.util.List;

import com.ruyicai.prizedata.domain.PrizeInfo;
import com.ruyicai.prizedata.service.PrizeInfoService;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

public class ShuangSeQiuXHC {

	public static final String KEY = "F47104HC_X";
	private static final String LOTNO = "F47104";
	private int[] red = new int[33];
	private int[] blue = new int[16];
	public int[] getRed() {
		return red;
	}
	public void setRed(int[] red) {
		this.red = red;
	}
	public int[] getBlue() {
		return blue;
	}
	public void setBlue(int[] blue) {
		this.blue = blue;
	}
	
	
	
	public void onPrize(PrizeInfo prizeInfo) {
		String winbase = prizeInfo.getWinbasecode();
		int[] redPrize = { Integer.parseInt(winbase.substring(0, 2)),
				Integer.parseInt(winbase.substring(2, 4)),
				Integer.parseInt(winbase.substring(4, 6)),
				Integer.parseInt(winbase.substring(6, 8)),
				Integer.parseInt(winbase.substring(8, 10)),
				Integer.parseInt(winbase.substring(10, 12)) };
		int bluePrize = Integer.parseInt(prizeInfo.getWinspecialcode());
		
		for(int redBall:redPrize) {
			red[redBall-1] = red[redBall-1] + 1;
		}
		blue[bluePrize-1] = blue[bluePrize-1] + 1;
	}
	
	
	/**
	 * 指定期数刷新冷热号
	 * @param prizeInfoService
	 * @param countBatch 指定期数
	 * @return
	 */
	public ShuangSeQiuXHC refresh(PrizeInfoService prizeInfoService,int countBatch) {
		List<PrizeInfo> prizeInfos = prizeInfoService.find(LOTNO, 0, countBatch);
		for(PrizeInfo prizeInfo:prizeInfos) {
			onPrize(prizeInfo);
		}
		return this;
	}
	
	
	public String toJson() {
		return new JSONSerializer().exclude("*.class").deepSerialize(this);
	}

	public static ShuangSeQiuXHC fromJsonToShuangSeQiuXHC(String json) {
		return new JSONDeserializer<ShuangSeQiuXHC>().use(null,
				ShuangSeQiuXHC.class).deserialize(json);

	}
}
