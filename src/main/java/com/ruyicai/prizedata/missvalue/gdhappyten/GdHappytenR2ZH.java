package com.ruyicai.prizedata.missvalue.gdhappyten;

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
 * 任选2组合遗漏
 * 
 * @author liuhongxing
 * 
 */
public class GdHappytenR2ZH {

	public static final String KEY = "T01015MV_R2ZH";
	private static final String LOTNO = "T01015";

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
		
		for(int i=0;i<wins.length-1;i++) {
			for(int j=i+1;j<wins.length;j++) {
				String key = getKey(wins[i], wins[j]);
				if(result.containsKey(key)) {
					result.put(key, 0);
				}
			}
		}

	}

	public GdHappytenR2ZH refresh(PrizeInfoService prizeInfoService, int index) {
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
				if (this.refreshR2ZH(p.getWinbasecode(), map, i)) {
					i++;
				} else {
					flag = false;
					break;
				}
			}
		}
		return this;
	}

	private boolean refreshR2ZH(String win, Map<String, Object> mark, int i) {
		String[] wins = { win.substring(0, 2), win.substring(2, 4),
				win.substring(4, 6), win.substring(6, 8), win.substring(8, 10),
				win.substring(10, 12), win.substring(12, 14),
				win.substring(14, 16) };
		
		for(int k=0;k<wins.length-1;k++) {
			for(int j=k+1;j<wins.length;j++) {
				String key = getKey(wins[k], wins[j]);
				if(mark.containsKey(key)) {
					result.put(key, i);
					mark.remove(key);
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

	public static GdHappytenR2ZH fromJsonToGdHappytenR2ZH(String json) {
		return new JSONDeserializer<GdHappytenR2ZH>().use(null,
				GdHappytenR2ZH.class).deserialize(json);
	}

	@Override
	public String toString() {
		return BaseUtil.toStringFromMap(result);
	}
	
	
	private String getKey(String i,String j) {
		return Integer.parseInt(i)<Integer.parseInt(j)?i+j:j+i;
	}
	
	
	private Map<String, Object> initMap(Map<String, Object> map) {
		for (int i = 1; i <= 20; i++) {
			for (int j = 1; j <= 20; j++) {
				if (i!=j) {
					map.put(getKey(i<10?"0"+i:""+i, j<10?"0"+j:""+j), 0);
				}
			}
		}

		return map;
	}
	
	

}
