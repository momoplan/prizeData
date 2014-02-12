package com.ruyicai.prizedata.missvalue.cqhappyten;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruyicai.prizedata.domain.PrizeInfo;
import com.ruyicai.prizedata.service.PrizeInfoService;
import com.ruyicai.prizedata.util.BaseMath;
import com.ruyicai.prizedata.util.BaseUtil;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

/**
 * 任选3组合遗漏
 * 
 * @author liuhongxing
 * 
 */
public class CqHappytenR3ZH {

	public static final String KEY = "F47109MV_R3ZH";
	private static final String LOTNO = "F47109";

	private Map<String, Integer> result = new HashMap<String, Integer>();

	public Map<String, Integer> getResult() {
		return result;
	}

	public void setResult(Map<String, Integer> result) {
		this.result = result;
	}

	public void onPrize(PrizeInfo prizeInfo) {
		String win = prizeInfo.getWinbasecode();
		String[] wins = { win.substring(0, 2), win.substring(2, 4),
				win.substring(4, 6), win.substring(6, 8), win.substring(8, 10),
				win.substring(10, 12), win.substring(12, 14),
				win.substring(14, 16) };
		BaseMath.plusOne(result);
		
		for(int i=0;i<wins.length-2;i++) {
			for(int j=i+1;j<wins.length-1;j++) {
				for(int k=j+1;k<wins.length;k++) {
					String key = BaseUtil.getKey(wins[i],wins[j],wins[k]);
					if(result.containsKey(key)) {
						result.put(key, 0);
					}
				}
			}
		}

	}

	public CqHappytenR3ZH refresh(PrizeInfoService prizeInfoService, int index) {
		Map<String, Object> map = new HashMap<String, Object>();
		initMap(map);
		int i = 0;
		int j = 0;
		boolean flag = true;
		while (flag) {
			List<PrizeInfo> prizeInfos = prizeInfoService.find(LOTNO,
					j + index, 50);
			j = j + 50;
			if (prizeInfos == null || prizeInfos.size() == 0) {
				break;
			}
			for (PrizeInfo p : prizeInfos) {
				if (this.refreshR3ZH(p.getWinbasecode(), map, i)) {
					i++;
				} else {
					flag = false;
					break;
				}
			}
		}
		return this;
	}

	private boolean refreshR3ZH(String win, Map<String, Object> mark, int i) {
		String[] wins = { win.substring(0, 2), win.substring(2, 4),
				win.substring(4, 6), win.substring(6, 8), win.substring(8, 10),
				win.substring(10, 12), win.substring(12, 14),
				win.substring(14, 16) };
		for(int m=0;m<wins.length-2;m++) {
			for(int j=m+1;j<wins.length-1;j++) {
				for(int k=j+1;k<wins.length;k++) {
					String key = BaseUtil.getKey(wins[m],wins[j],wins[k]);
					if(mark.containsKey(key)) {
						result.put(key, i);
						mark.remove(key);
					}
				}
			}
		}

		if (mark.isEmpty()) {
			return false;
		} else {
			return true;
		}

	}

	public String toJson() {
		return new JSONSerializer().exclude("*.class").deepSerialize(this);
	}

	public static CqHappytenR3ZH fromJsonToCqHappytenR3ZH(String json) {
		return new JSONDeserializer<CqHappytenR3ZH>().use(null,
				CqHappytenR3ZH.class).deserialize(json);
	}

	@Override
	public String toString() {
		return BaseUtil.toStringFromMap(result);
	}

	
	
	private Map<String, Object> initMap(Map<String, Object> map) {
		for (int i = 1; i <= 20; i++) {
			for (int j = 1; j <= 20; j++) {
				for(int k=1;k<=20;k++) {
					if (i!=j&&i!=k&&j!=k) {
						map.put(BaseUtil.getKey(new int[]{i,j,k}), 0);
					}
				}
				
			}
		}

		return map;
	}
	
	

}
