package com.ruyicai.prizedata.missvalue.threed;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruyicai.prizedata.domain.PrizeInfo;
import com.ruyicai.prizedata.service.PrizeInfoService;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
/**
 * 3D组三组六普通(选号区)
 * @author liuhongxing
 *
 */
public class ThreeDZ36 {

	public static final String KEY = "F47103MV_Z36";
	private static final String LOTNO = "F47103";
	
	private int[] miss = new int[10];

	public int[] getMiss() {
		return miss;
	}

	public void setMiss(int[] miss) {
		this.miss = miss;
	}
	
	public void onPrize(PrizeInfo prizeInfo) {
		String win = prizeInfo.getWinbasecode();
		int[] winInt = { Integer.parseInt(win.substring(0, 2)),
				Integer.parseInt(win.substring(2, 4)),
				Integer.parseInt(win.substring(4, 6)) };
		
		plusOne();
		for(int i = 0;i<winInt.length;i++) {
			miss[winInt[i]] = 0;
		}
	}
	
	
	public ThreeDZ36 refresh(PrizeInfoService prizeInfoService,int index) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (int j = 0; j <= 9; j++) {
			map.put(j+"", 0);
		}
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
				if(this.refreshZ36(p.getWinbasecode(), map, i)){
					i++;
				}else{
					flag = false;
					break;
				}
			}
		}
		return this;
	}
	
	
	
	private boolean refreshZ36(String win,Map<String, Object> map,int i) {
		int[] winInt = { Integer.parseInt(win.substring(0, 2)),
				Integer.parseInt(win.substring(2, 4)),
				Integer.parseInt(win.substring(4, 6)) };
		
		for(int j:winInt) {
			if(map.get(j+"")!=null) {
				miss[j] = i;
				map.remove(j+"");
			}
		}
		
		if(map.isEmpty()) {
			return false;
		}else {
			return true;
		}
		
	}
	
	
	
	private void plusOne() {
		for(int i = 0;i < miss.length;i++) {
			miss[i] = miss[i] + 1;
		}
	}
	
	
	
	public String toJson() {
		return new JSONSerializer().exclude("*.class").deepSerialize(this);
	}

	public static ThreeDZ36 fromJsonToThreeDZ36(String json) {
		return new JSONDeserializer<ThreeDZ36>().use(null,
				ThreeDZ36.class).deserialize(json);
	}
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i:miss) {
			sb.append(i).append(",");
		}
		if(sb.toString().endsWith(",")) {
			sb.deleteCharAt(sb.length()-1);
		}
		return sb.toString();
	}
}
