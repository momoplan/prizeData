package com.ruyicai.prizedata.missvalue.nmkuai3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruyicai.prizedata.domain.PrizeInfo;
import com.ruyicai.prizedata.service.PrizeInfoService;
import com.ruyicai.prizedata.util.BaseUtil;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

/**
 * 
 * @author liuhongxing 二不同号组合遗漏
 */
public class NmK392 {

	public static final String KEY = "F47107MV_92";
	private static final String LOTNO = "F47107";

	private Map<String, Integer> result = new HashMap<String, Integer>();

	public Map<String, Integer> getResult() {
		return result;
	}

	public void setResult(Map<String, Integer> result) {
		this.result = result;
	}
	
	
	public void onPrize(PrizeInfo prizeInfo) {
		String win = prizeInfo.getWinbasecode();
		BaseUtil.plusOne(result);
		
		if(NmK3Util.isErBuTong(win)) {
			int[] wins = NmK3Util.convertWincodeInt(win);
			Arrays.sort(wins);
			if(wins[0]!=wins[1]) {
				result.put(wins[0]+""+wins[1],0);
			}
			if(wins[0]!=wins[2]) {
				result.put(wins[0]+""+wins[2],0);
			}
			if(wins[1]!=wins[2]) {
				result.put(wins[1]+""+wins[2],0);
			}
		}
		
	}
	
	public NmK392 refresh(PrizeInfoService prizeInfoService,int index) {
		Map<String, Object> map = new HashMap<String, Object>();
		map = initMap(map);

		int i = 0;
		int j = 0;
		boolean flag = true;
		while(flag){
			List<PrizeInfo> prizeInfos = prizeInfoService.find(LOTNO, j+index, 50);
			j = j + 50;
			if (prizeInfos == null || prizeInfos.size() == 0) {
				break;
			}
			for(PrizeInfo p:prizeInfos) {
				if(this.refresh92(p.getWinbasecode(), map, i)){
					i++;
				}else{
					flag = false;
					break;
				}
			}
		}
		return this;
	}
	
	
	
	private Map<String, Object> initMap(Map<String, Object> map) {
		for(int i=1;i<=6;i++) {
			for(int j=1;j<=6;j++) {
				if(i!=j&&i<j) {
					map.put(i+""+j, 0);
				}
			}
		}
		return map;
	}

	private boolean refresh92(String win,Map<String, Object> mark,int i) {
		if(NmK3Util.isErBuTong(win)) {
			int[] wins = NmK3Util.convertWincodeInt(win);
			Arrays.sort(wins);
			if(wins[0]!=wins[1]) {
				if(mark.get(wins[0]+""+wins[1])!=null) {
					result.put(wins[0]+""+wins[1], i);
					mark.remove(wins[0]+""+wins[1]);
				}
			}
			if(wins[0]!=wins[2]) {
				if(mark.get(wins[0]+""+wins[2])!=null) {
					result.put(wins[0]+""+wins[2], i);
					mark.remove(wins[0]+""+wins[2]);
				}
			}
			if(wins[1]!=wins[2]) {
				if(mark.get(wins[1]+""+wins[2])!=null) {
					result.put(wins[1]+""+wins[2], i);
					mark.remove(wins[1]+""+wins[2]);
				}
			}
		}
		if(mark.isEmpty()) {
			return false;
		}
		return true;
	}
	
	
	public String toJson() {
		return new JSONSerializer().exclude("*.class").deepSerialize(this);
	}
	
	public static NmK392 fromJsonToNmK392(String json) {
		return new JSONDeserializer<NmK392>().use(null,
				NmK392.class).deserialize(json);
	}
	
	
	@Override
	public String toString() {
		return BaseUtil.toStringFromMap(result);
	}
	
}
