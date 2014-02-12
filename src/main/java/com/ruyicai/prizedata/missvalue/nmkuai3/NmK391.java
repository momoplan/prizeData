package com.ruyicai.prizedata.missvalue.nmkuai3;

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
 * @author liuhongxing 二同号单选组合遗漏
 */
public class NmK391 {

	public static final String KEY = "F47107MV_91";
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
		if(NmK3Util.isErTong(win)) {
			result.put(NmK3Util.getErTongCode(win),0);
		}
	}
	
	
	public NmK391 refresh(PrizeInfoService prizeInfoService,int index) {
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
				if(this.refresh91(p.getWinbasecode(), map, i)){
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
				if(i!=j) {
					map.put(i+""+i+""+j, 0);
				}
			}
		}
		return map;
	}

	private boolean refresh91(String win,Map<String, Object> mark,int i) {
		if(NmK3Util.isErTong(win)) {
			String ertongcode = NmK3Util.getErTongCode(win);
			if(mark.get(ertongcode)!=null) {
				result.put(ertongcode, i);
				mark.remove(ertongcode);
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
	
	public static NmK391 fromJsonToNmK391(String json) {
		return new JSONDeserializer<NmK391>().use(null,
				NmK391.class).deserialize(json);
	}
	
	@Override
	public String toString() {
		return BaseUtil.toStringFromMap(result);
	}

}
