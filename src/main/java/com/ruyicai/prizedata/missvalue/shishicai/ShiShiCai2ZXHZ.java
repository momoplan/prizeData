package com.ruyicai.prizedata.missvalue.shishicai;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruyicai.prizedata.domain.PrizeInfo;
import com.ruyicai.prizedata.service.PrizeInfoService;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

/**
 * 时时彩二星组选和值
 * 
 * @author liuhongxing
 * 
 */
public class ShiShiCai2ZXHZ {

	public static final String KEY = "T01007MV_2ZXHZ";
	private static final String LOTNO = "T01007";

	private int[] miss = new int[19];

	public int[] getMiss() {
		return miss;
	}

	public void setMiss(int[] miss) {
		this.miss = miss;
	}

	public void onPrize(PrizeInfo prizeInfo) {

		String win = prizeInfo.getWinbasecode();
		int winInt = Integer.parseInt(win.charAt(3) + "")+
				Integer.parseInt(win.charAt(4) + "");

		plusOne(miss);

		miss[winInt] = 0;

	}

	public ShiShiCai2ZXHZ refresh(PrizeInfoService prizeInfoService, int index) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (int j = 0; j <= 18; j++) {
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
				if (this.refresh2ZXHZ(p.getWinbasecode(), map, i)) {
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

	private boolean refresh2ZXHZ(String win, Map<String, Object> map, int i) {
		int winInt = Integer.parseInt(win.charAt(3) + "")+
				Integer.parseInt(win.charAt(4) + "");

		if (map.get("" + winInt) != null) {
			miss[winInt] = i;
			map.remove("" + winInt);
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

	public static ShiShiCai2ZXHZ fromJsonToShiShiCai2ZXHZ(String json) {
		return new JSONDeserializer<ShiShiCai2ZXHZ>().use(null,
				ShiShiCai2ZXHZ.class).deserialize(json);
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
