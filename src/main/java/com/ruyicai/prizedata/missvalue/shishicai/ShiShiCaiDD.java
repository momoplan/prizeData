package com.ruyicai.prizedata.missvalue.shishicai;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruyicai.prizedata.domain.PrizeInfo;
import com.ruyicai.prizedata.service.PrizeInfoService;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
/**
 * 时时彩大小单双(选号栏)
 * @author liuhongxing
 *
 */
public class ShiShiCaiDD {

	public static final String KEY = "T01007MV_DD";
	private static final String LOTNO = "T01007";

	private int[] shiDX = new int[4];
	private int[] geDX = new int[4];

	public int[] getShiDX() {
		return shiDX;
	}

	public void setShiDX(int[] shiDX) {
		this.shiDX = shiDX;
	}

	public int[] getGeDX() {
		return geDX;
	}

	public void setGeDX(int[] geDX) {
		this.geDX = geDX;
	}

	public void onPrize(PrizeInfo prizeInfo) {
		int shi = Integer.parseInt(prizeInfo.getWinbasecode().substring(3, 4));
		int ge = Integer.parseInt(prizeInfo.getWinbasecode().substring(4));
		if (shi > 4) {
			shiDX[0] = 0;
			shiDX[1] = ++shiDX[1];
		} else {
			shiDX[1] = 0;
			shiDX[0] = ++shiDX[0];
		}
		if (shi % 2 != 0) {
			shiDX[2] = 0;
			shiDX[3] = ++shiDX[3];
		} else {
			shiDX[3] = 0;
			shiDX[2] = ++shiDX[2];
		}

		if (ge > 4) {
			geDX[0] = 0;
			geDX[1] = ++geDX[1];
		} else {
			geDX[1] = 0;
			geDX[0] = ++geDX[0];
		}
		if (ge % 2 != 0) {
			geDX[2] = 0;
			geDX[3] = ++geDX[3];
		} else {
			geDX[3] = 0;
			geDX[2] = ++geDX[2];
		}
	}

	public ShiShiCaiDD refresh(PrizeInfoService prizeInfoService,int index) {
		Map<String, Object> mark = new HashMap<String, Object>();
		mark = initMap(mark);
		int i = 0;
		int j = 0;
		boolean flag = true;
		while (flag) {
			List<PrizeInfo> prizeInfos = prizeInfoService.find(LOTNO, j+index, 50);
			j = j + 50;
			if (prizeInfos == null || prizeInfos.size() == 0) {
				break;
			}
			for (PrizeInfo p : prizeInfos) {
				if (this.refreshDD(p.getWinbasecode(), mark, i)) {
					i++;
				} else {
					flag = false;
					break;
				}
			}
		}
		return this;
	}

	private boolean refreshDD(String win, Map<String, Object> map, int i) {
		int[] winInt = { Integer.parseInt(win.charAt(3) + ""),
				Integer.parseInt(win.charAt(4) + "") };
		int s;
		int b;
		if (winInt[0] > 4) {
			s = 0;
		} else {
			s = 1;
		}
		if (winInt[0] % 2 == 0) {
			b = 3;
		} else {
			b = 2;
		}
		if (map.get("s" + s) != null) {
			shiDX[s] = i;
			map.remove("s" + s);
		}
		if (map.get("s" + b) != null) {
			shiDX[b] = i;
			map.remove("s" + b);
		}

		int ss;
		int bb;
		if (winInt[1] > 4) {
			ss = 0;
		} else {
			ss = 1;
		}
		if (winInt[1] % 2 == 0) {
			bb = 3;
		} else {
			bb = 2;
		}
		if (map.get("g" + ss) != null) {
			geDX[ss] = i;
			map.remove("g" + ss);
		}
		if (map.get("g" + bb) != null) {
			geDX[bb] = i;
			map.remove("g" + bb);
		}

		if (map.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	private Map<String, Object> initMap(Map<String, Object> map) {
		for (int j = 0; j < 4; j++) {
			map.put("s" + j, 0);
			map.put("g" + j, 0);
		}
		return map;
	}
	
	public String toJson() {
        return new JSONSerializer().exclude("*.class").deepSerialize(this);
    }
	
	public static ShiShiCaiDD fromJsonToShiShiCaiDD(String json) {
		return new JSONDeserializer<ShiShiCaiDD>().use(null, ShiShiCaiDD.class)
							.deserialize(json);
    }
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i:shiDX) {
			sb.append(i).append(",");
		}
		if(sb.toString().endsWith(",")) {
			sb.deleteCharAt(sb.length()-1);
		}
		sb.append(";");
		for(int i:geDX) {
			sb.append(i).append(",");
		}
		if(sb.toString().endsWith(",")) {
			sb.deleteCharAt(sb.length()-1);
		}
		return sb.toString();
	}
}
