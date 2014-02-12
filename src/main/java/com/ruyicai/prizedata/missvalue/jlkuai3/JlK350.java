package com.ruyicai.prizedata.missvalue.jlkuai3;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.ruyicai.prizedata.domain.PrizeInfo;
import com.ruyicai.prizedata.service.PrizeInfoService;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

/**
 * 三连号通选
 * 
 * @author liuhongxing
 * 
 */
public class JlK350 {

	public static final String KEY = "F47108MV_50";
	private static final String LOTNO = "F47108";

	private int[] miss = new int[1];

	public int[] getMiss() {
		return miss;
	}

	public void setMiss(int[] miss) {
		this.miss = miss;
	}

	public void onPrize(PrizeInfo prizeInfo) {
		int[] wins = new int[] {
				Integer.parseInt(prizeInfo.getWinbasecode().substring(0, 2)),
				Integer.parseInt(prizeInfo.getWinbasecode().substring(2, 4)),
				Integer.parseInt(prizeInfo.getWinbasecode().substring(4, 6)) };
		
		plusOne(miss);
		Arrays.sort(wins);
		if (wins[2]-wins[1]==1&&wins[1]-wins[0]==1) {
			miss[0] = 0;
		}
	}

	public JlK350 refresh(PrizeInfoService prizeInfoService, int index) {
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
				if (this.refresh40(p.getWinbasecode(), null, i)) {
					i++;
				} else {
					flag = false;
					break;
				}
			}
		}
		return this;
	}

	private boolean refresh40(String win, Map<String, Object> map, int i) {
		int[] wins = new int[] {
				Integer.parseInt(win.substring(0, 2)),
				Integer.parseInt(win.substring(2, 4)),
				Integer.parseInt(win.substring(4, 6)) };

		if (wins[2]-wins[1]==1&&wins[1]-wins[0]==1) {
			miss[0] = i;
			return false;
		}
		return true;
	}

	private void plusOne(int[] miss) {
		for (int i = 0; i < miss.length; i++) {
			miss[i] = miss[i] + 1;
		}
	}

	public String toJson() {
		return new JSONSerializer().exclude("*.class").deepSerialize(this);
	}

	public static JlK350 fromJsonToJlK350(String json) {
		return new JSONDeserializer<JlK350>().use(null, JlK350.class)
				.deserialize(json);
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
