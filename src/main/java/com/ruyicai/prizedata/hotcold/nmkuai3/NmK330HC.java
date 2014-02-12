package com.ruyicai.prizedata.hotcold.nmkuai3;

import java.util.List;

import com.ruyicai.prizedata.domain.PrizeInfo;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

//二同号复选
public class NmK330HC {

	public static final String KEY = "F47107HC_30";
	
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
			hc[geint-1] = hc[geint-1] + 1;
		}else if(geint==shiint) {
			hc[geint-1] = hc[geint-1] + 1;
		}else if(geint==baiint) {
			hc[geint-1] = hc[geint-1] + 1;
		}else if(shiint==baiint) {
			hc[shiint-1] = hc[shiint-1] + 1;
		}

		
	}
	
	
	/**
	 * 指定期数刷新冷热号
	 * @param prizeInfoService
	 * @param countBatch 指定期数
	 * @return
	 */
	public NmK330HC refresh(List<PrizeInfo> prizeInfos,int countBatch) {
		for(PrizeInfo prizeInfo:prizeInfos) {
			onPrize(prizeInfo);
		}
		return this;
	}
	
	
	
	public String toJson() {
		return new JSONSerializer().exclude("*.class").deepSerialize(this);
	}
	
	public static NmK330HC fromJsonToNmK330HC(String json) {
		return new JSONDeserializer<NmK330HC>().use(null,
				NmK330HC.class).deserialize(json);

	}
	
}
