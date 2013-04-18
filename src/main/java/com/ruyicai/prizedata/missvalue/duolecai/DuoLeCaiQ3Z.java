package com.ruyicai.prizedata.missvalue.duolecai;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruyicai.prizedata.domain.PrizeInfo;
import com.ruyicai.prizedata.service.PrizeInfoService;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
/**
 * 前三组选(选号栏)
 * @author liuhongxing
 *
 */
public class DuoLeCaiQ3Z {

	public static final String KEY = "T01010MV_Q3Z";
	private static final String LOTNO = "T01010";
	
	private int[] miss = new int[11];

	
	
	
	public int[] getMiss() {
		return miss;
	}


	public void setMiss(int[] miss) {
		this.miss = miss;
	}


	public void onPrize(PrizeInfo prizeInfo) {
		int prizeBai = Integer.parseInt(prizeInfo.getWinbasecode().substring(0, 2));
		int prizeShi = Integer.parseInt(prizeInfo.getWinbasecode().substring(2, 4));
		int prizeGe = Integer.parseInt(prizeInfo.getWinbasecode().substring(4, 6));
		plusOne(miss);

		miss[prizeBai-1] = 0;
		miss[prizeShi-1] = 0;
		miss[prizeGe-1] = 0;
	}
	
	
	public DuoLeCaiQ3Z refresh(PrizeInfoService prizeInfoService,int index) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (int j = 1; j <= 11; j++) {
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
				if(this.refreshQ3Z(p.getWinbasecode(), map, i)){
					i++;
				}else{
					flag = false;
					break;
				}
			}
		}
		return this;
	}
	
	private boolean refreshQ3Z(String win,Map<String, Object> map,int i) {
		int prizeBai = Integer.parseInt(win.substring(0, 2));
		int prizeShi = Integer.parseInt(win.substring(2, 4));
		int prizeGe = Integer.parseInt(win.substring(4, 6));
		
		if(map.get(prizeGe+"")!=null) {
			miss[prizeGe-1] = i;
			map.remove(prizeGe+"");
		}
		
		if(map.get(prizeShi+"")!=null) {
			miss[prizeShi-1] = i;
			map.remove(prizeShi+"");
		}
		
		if(map.get(prizeBai+"")!=null) {
			miss[prizeBai-1] = i;
			map.remove(prizeBai+"");
		}
		
		if(map.isEmpty()) {
			return false;
		}else {
			return true;
		}
		
	}
	
	
	
	
	private void plusOne(int[] miss) {
		for(int i = 0;i < miss.length;i++) {
			miss[i] = miss[i] + 1;
		}
	}

	public String toJson() {
		return new JSONSerializer().exclude("*.class").deepSerialize(this);
	}
	
	public static DuoLeCaiQ3Z fromJsonToDuoLeCaiQ3Z(String json) {
		return new JSONDeserializer<DuoLeCaiQ3Z>().use(null,
				DuoLeCaiQ3Z.class).deserialize(json);
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
