package com.ruyicai.prizedata.hotcold.jilinkuai3;

import java.util.List;

import com.ruyicai.prizedata.domain.PrizeInfo;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

//三不同单式，三不同组合，三不同胆拖，二不同胆拖，二不同组合，二不同复选
public class JilinK3BaseHC {

	public static final String KEY = "F47108HC_BASE";
	
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
		
		hc[baiint-1] = hc[baiint-1] + 1;
		hc[shiint-1] = hc[shiint-1] + 1;
		hc[geint-1] = hc[geint-1] + 1;
		
	}
	
	
	/**
	 * 指定期数刷新冷热号
	 * @param prizeInfoService
	 * @param countBatch 指定期数
	 * @return
	 */
	public JilinK3BaseHC refresh(List<PrizeInfo> prizeInfos,int countBatch) {
		for(PrizeInfo prizeInfo:prizeInfos) {
			onPrize(prizeInfo);
		}
		return this;
	}
	
	
	
	public String toJson() {
		return new JSONSerializer().exclude("*.class").deepSerialize(this);
	}
	
	public static JilinK3BaseHC fromJsonToJilinK3BaseHC(String json) {
		return new JSONDeserializer<JilinK3BaseHC>().use(null,
				JilinK3BaseHC.class).deserialize(json);

	}
	
}
