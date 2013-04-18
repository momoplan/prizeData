package com.ruyicai.prizedata.missvalue.shishicai;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruyicai.prizedata.domain.PrizeInfo;
import com.ruyicai.prizedata.service.PrizeInfoService;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

/**
 * 时时彩二星组选
 * 
 * @author liuhongxing
 * 
 */
public class ShiShiCai2ZX {

	public static final String KEY = "T01007MV_2ZX";
	private static final String LOTNO = "T01007";

	private int[] miss = new int[10];

	public int[] getMiss() {
		return miss;
	}

	public void setMiss(int[] miss) {
		this.miss = miss;
	}

	public void onPrize(PrizeInfo prizeInfo) {

		String win = prizeInfo.getWinbasecode();
		int[] winInt = { Integer.parseInt(win.charAt(3) + ""),
				Integer.parseInt(win.charAt(4) + "") };

		plusOne(miss);

		miss[winInt[0]] = 0;
		miss[winInt[1]] = 0;

	}

	public ShiShiCai2ZX refresh(PrizeInfoService prizeInfoService, int index) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (int j = 0; j < 9; j++) {
			map.put(j + "", 0);
		}
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
				if (this.refresh2ZX(p.getWinbasecode(), map, i)) {
					i++;
				} else {
					flag = false;
					break;
				}
			}
		}

		return this;

	}

	private void plusOne(int[] a) {
		for (int i = 0; i < a.length; i++) {
			a[i] = a[i] + 1;
		}
	}

	private boolean refresh2ZX(String win, Map<String, Object> map, int i) {
		int[] winInt = { Integer.parseInt(win.charAt(3) + ""),
				Integer.parseInt(win.charAt(4) + "") };

		if (map.get("" + winInt[0]) != null) {
			miss[winInt[0]] = i;
			map.remove("" + winInt[0]);
		}

		if (map.get("" + winInt[1]) != null) {
			miss[winInt[1]] = i;
			map.remove("" + winInt[1]);
		}

		if (map.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	public String toJson() {
		return new JSONSerializer().exclude("*.class").deepSerialize(this);
	}

	public static ShiShiCai2ZX fromJsonToShiShiCai2ZX(String json) {
		return new JSONDeserializer<ShiShiCai2ZX>().use(null,
				ShiShiCai2ZX.class).deserialize(json);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i : miss) {
			sb.append(i).append(",");
		}
		if (sb.toString().endsWith(",")) {
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}

}
