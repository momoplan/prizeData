package com.ruyicai.prizedata.hotcold.jilinkuai3;

import java.util.List;

import com.ruyicai.prizedata.domain.PrizeInfo;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

//三同号单式,三同号复式
public class JilinK302HC {

	public static final String KEY = "F47108HC_02";
	
	private int[] hc = new int[6];
	
	public int[] getHc() {
		return hc;
	}

	public void setHc(int[] hc) {
		this.hc = hc;
	}


	public void onPrize(PrizeInfo prizeInfo) {
		int geint = Integer.parseInt(prizeInfo.getWinbasecode().substring(4, 6));
		int shiint = Integer.parseInt(prizeInfo.getWinbasecode().substring(2, 4));
		int baiint = Integer.parseInt(prizeInfo.getWinbasecode().substring(0, 2));
		
		if(geint==shiint&&shiint==baiint) {
			hc[baiint-1] = hc[baiint-1] + 1;
		}

		
	}
	
	
	/**
	 * 指定期数刷新冷热号
	 * @param prizeInfoService
	 * @param countBatch 指定期数
	 * @return
	 */
	public JilinK302HC refresh(List<PrizeInfo> prizeInfos,int countBatch) {
		for(PrizeInfo prizeInfo:prizeInfos) {
			onPrize(prizeInfo);
		}
		return this;
	}
	
	
	
	public String toJson() {
		return new JSONSerializer().exclude("*.class").deepSerialize(this);
	}
	
	public static JilinK302HC fromJsonToJilinK302HC(String json) {
		return new JSONDeserializer<JilinK302HC>().use(null,
				JilinK302HC.class).deserialize(json);

	}
	
}
