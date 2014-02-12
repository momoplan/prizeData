package com.ruyicai.prizedata.hotcold.nmkuai3;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ruyicai.prizedata.domain.PrizeInfo;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

//二同号单式，二同号单选组合
public class NmK301HC {

	public static final String KEY = "F47107HC_01";
	
	private int[] dan = new int[6];
	private int[] shuang = new int[6];

	public int[] getDan() {
		return dan;
	}

	public void setDan(int[] dan) {
		this.dan = dan;
	}

	public int[] getShuang() {
		return shuang;
	}

	public void setShuang(int[] shuang) {
		this.shuang = shuang;
	}


	public void onPrize(PrizeInfo prizeInfo) {
		int geint = Integer.parseInt(prizeInfo.getWinbasecode().substring(4, 6));
		int shiint = Integer.parseInt(prizeInfo.getWinbasecode().substring(2, 4));
		int baiint = Integer.parseInt(prizeInfo.getWinbasecode().substring(0, 2));

		Set<Integer> set = new HashSet<Integer>();
		set.add(geint);
		set.add(shiint);
		set.add(baiint);
		
		for(int num:set) {
			dan[num-1] = dan[num-1] + 1;
		}
		
		if(geint==shiint&&shiint==baiint) {
			shuang[geint-1] = shuang[geint-1] + 1;
		}else if(geint==shiint) {
			shuang[geint-1] = shuang[geint-1] + 1;
		}else if(geint==baiint) {
			shuang[geint-1] = shuang[geint-1] + 1;
		}else if(shiint==baiint) {
			shuang[shiint-1] = shuang[shiint-1] + 1;
		}
		
	}
	
	
	/**
	 * 指定期数刷新冷热号
	 * @param prizeInfoService
	 * @param countBatch 指定期数
	 * @return
	 */
	public NmK301HC refresh(List<PrizeInfo> prizeInfos,int countBatch) {
		for(PrizeInfo prizeInfo:prizeInfos) {
			onPrize(prizeInfo);
		}
		return this;
	}
	
	
	
	public String toJson() {
		return new JSONSerializer().exclude("*.class").deepSerialize(this);
	}
	
	public static NmK301HC fromJsonToNmK301HC(String json) {
		return new JSONDeserializer<NmK301HC>().use(null,
				NmK301HC.class).deserialize(json);

	}
	
}
