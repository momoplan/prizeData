package com.ruyicai.prizedata.hotcold.nmkuai3;

import java.util.List;

import com.ruyicai.prizedata.domain.PrizeInfo;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

//和值
public class NmK310HC {

	public static final String KEY = "F47107HC_10";
	
	private int[] hc = new int[14];
	
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
		if(geint+shiint+baiint>=4&&geint+shiint+baiint<=17) {
			hc[geint+shiint+baiint-4] = hc[geint+shiint+baiint-4] + 1;
		}
	}
	
	
	/**
	 * 指定期数刷新冷热号
	 * @param prizeInfoService
	 * @param countBatch 指定期数
	 * @return
	 */
	public NmK310HC refresh(List<PrizeInfo> prizeInfos,int countBatch) {
		for(PrizeInfo prizeInfo:prizeInfos) {
			onPrize(prizeInfo);
		}
		return this;
	}
	
	
	
	public String toJson() {
		return new JSONSerializer().exclude("*.class").deepSerialize(this);
	}
	
	public static NmK310HC fromJsonToNmK310HC(String json) {
		return new JSONDeserializer<NmK310HC>().use(null,
				NmK310HC.class).deserialize(json);

	}
	
}
