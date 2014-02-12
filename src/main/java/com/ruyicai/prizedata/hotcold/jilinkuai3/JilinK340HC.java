package com.ruyicai.prizedata.hotcold.jilinkuai3;

import java.util.List;

import com.ruyicai.prizedata.domain.PrizeInfo;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

//三同号通选
public class JilinK340HC {

	public static final String KEY = "F47108HC_40";
	
	private int[] hc = new int[1];
	
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
			hc[0] = hc[0] + 1;
		}

		
	}
	
	
	/**
	 * 指定期数刷新冷热号
	 * @param prizeInfoService
	 * @param countBatch 指定期数
	 * @return
	 */
	public JilinK340HC refresh(List<PrizeInfo> prizeInfos,int countBatch) {
		for(PrizeInfo prizeInfo:prizeInfos) {
			onPrize(prizeInfo);
		}
		return this;
	}
	
	
	
	public String toJson() {
		return new JSONSerializer().exclude("*.class").deepSerialize(this);
	}
	
	public static JilinK340HC fromJsonToJilinK340HC(String json) {
		return new JSONDeserializer<JilinK340HC>().use(null,
				JilinK340HC.class).deserialize(json);

	}
	
}
