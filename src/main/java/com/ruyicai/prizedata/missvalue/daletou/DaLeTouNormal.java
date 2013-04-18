package com.ruyicai.prizedata.missvalue.daletou;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruyicai.prizedata.domain.PrizeInfo;
import com.ruyicai.prizedata.service.PrizeInfoService;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
/**
 * 大乐透普通遗漏(选号区)
 * @author liuhongxing
 *
 */
public class DaLeTouNormal {

	public static final String KEY = "T01001MV_X";
	private static final String LOTNO = "T01001";
	
	private int[] qian = new int[35];
	private int[] hou = new int[12];
	
	public int[] getQian() {
		return qian;
	}

	public void setQian(int[] qian) {
		this.qian = qian;
	}

	public int[] getHou() {
		return hou;
	}

	public void setHou(int[] hou) {
		this.hou = hou;
	}
	
	
	public void onPrize(PrizeInfo prizeInfo) {
		String winbase = prizeInfo.getWinbasecode();
		String winspecial = prizeInfo.getWinspecialcode();
		int[] winBasePrize = { Integer.parseInt(winbase.substring(0, 2)),
				Integer.parseInt(winbase.substring(2, 4)),
				Integer.parseInt(winbase.substring(4, 6)),
				Integer.parseInt(winbase.substring(6, 8)),
				Integer.parseInt(winbase.substring(8, 10))};
		int[] winSpecialPrize = {Integer.parseInt(winspecial.substring(0, 2)),
				Integer.parseInt(winspecial.substring(2, 4))};
		
		plusOne(qian);
		plusOne(hou);
		
		for(int i:winBasePrize) {
			qian[i-1]=0;
		}
		
		for(int i:winSpecialPrize) {
			hou[i-1]=0;
		}
		
	}
	
	private boolean refreshX(String winbase,String winspecial,Map<String, Object> map,int i) {
		int[] winBasePrize = { Integer.parseInt(winbase.substring(0, 2)),
				Integer.parseInt(winbase.substring(2, 4)),
				Integer.parseInt(winbase.substring(4, 6)),
				Integer.parseInt(winbase.substring(6, 8)),
				Integer.parseInt(winbase.substring(8, 10))};
		int[] winSpecialPrize = {Integer.parseInt(winspecial.substring(0, 2)),
				Integer.parseInt(winspecial.substring(2, 4))};
		
		for(int j:winBasePrize) {
			if(map.get(""+j)!=null) {
				qian[j-1] = i;
				map.remove(""+j);
			}
		}
		
		for(int j:winSpecialPrize) {
			if(map.get((40+j)+"")!=null) {
				hou[j-1] = i;
				map.remove((40+j)+"");
			}
		}
		if(map.isEmpty()) {
			return false;
		}else {
			return true;
		}
	}
	
	
	
	
	public DaLeTouNormal refresh(PrizeInfoService prizeInfoService,int index) {
		Map<String, Object> map = new HashMap<String, Object>();
		for(int i = 1;i<=35;i++) {
			map.put(""+i, 0);
		}
		for(int i = 41;i<=52;i++) {
			map.put(""+i, 0);
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
				if(this.refreshX(p.getWinbasecode(),p.getWinspecialcode(), map, i)){
					i++;
				}else{
					flag = false;
					break;
				}
			}
		}
		return this;
	}
	
	
	private void plusOne(int[] miss) {
		for (int i = 0; i < miss.length; i++) {
			miss[i] = miss[i] + 1;
		}
	}

	public String toJson() {
		return new JSONSerializer().exclude("*.class").deepSerialize(this);
	}

	public static DaLeTouNormal fromJsonToDaLeTouNormal(String json) {
		return new JSONDeserializer<DaLeTouNormal>().use(null,
				DaLeTouNormal.class).deserialize(json);

	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i:qian) {
			sb.append(i).append(",");
		}
		if(sb.toString().endsWith(",")) {
			sb.deleteCharAt(sb.length()-1);
		}
		sb.append(";");
		
		
		for(int i:hou) {
			sb.append(i).append(",");
		}
		if(sb.toString().endsWith(",")) {
			sb.deleteCharAt(sb.length()-1);
		}
		return sb.toString();
	}
}
