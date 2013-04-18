package com.ruyicai.prizedata.missvalue.shuangseqiu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruyicai.prizedata.domain.PrizeInfo;
import com.ruyicai.prizedata.service.PrizeInfoService;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
/**
 * 双色球普通(选号区)
 * @author liuhongxing
 *
 */
public class ShuangSeQiuX {

	public static final String KEY = "F47104MV_X";
	private static final String LOTNO = "F47104";
	private int[] red = new int[33];
	private int[] blue = new int[16];

	public int[] getRed() {
		return red;
	}

	public void setRed(int[] red) {
		this.red = red;
	}

	public int[] getBlue() {
		return blue;
	}

	public void setBlue(int[] blue) {
		this.blue = blue;
	}

	public void onPrize(PrizeInfo prizeInfo) {
		String winbase = prizeInfo.getWinbasecode();
		int[] redPrize = { Integer.parseInt(winbase.substring(0, 2)),
				Integer.parseInt(winbase.substring(2, 4)),
				Integer.parseInt(winbase.substring(4, 6)),
				Integer.parseInt(winbase.substring(6, 8)),
				Integer.parseInt(winbase.substring(8, 10)),
				Integer.parseInt(winbase.substring(10, 12)) };
		int bluePrize = Integer.parseInt(prizeInfo.getWinspecialcode());
		
		plusOne(red);
		plusOne(blue);
		
		for(int i:redPrize) {
			red[i-1] = 0;
		}
		blue[bluePrize-1] = 0;
		
	}

	public ShuangSeQiuX refresh(PrizeInfoService prizeInfoService,int index) {
		Map<String, Object> map = new HashMap<String, Object>();
		for(int i = 1;i<=33;i++) {
			map.put("r"+i, 0);
		}
		for(int i = 1;i<=16;i++) {
			map.put("b"+i, 0);
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
	
	
	
	private boolean refreshX(String winbase,String winspecial,Map<String, Object> map,int i) {
		int[] redPrize = { Integer.parseInt(winbase.substring(0, 2)),
				Integer.parseInt(winbase.substring(2, 4)),
				Integer.parseInt(winbase.substring(4, 6)),
				Integer.parseInt(winbase.substring(6, 8)),
				Integer.parseInt(winbase.substring(8, 10)),
				Integer.parseInt(winbase.substring(10, 12)) };
		int bluePrize = Integer.parseInt(winspecial);
		
		for(int j:redPrize) {
			if(map.get("r"+j)!=null) {
				red[j-1] = i;
				map.remove("r"+j);
			}
		}
		if(map.get("b"+bluePrize)!=null) {
			blue[bluePrize-1] = i;
			map.remove("b"+bluePrize);
		}
		if(map.isEmpty()) {
			return false;
		}else {
			return true;
		}
	}
	
	
	private void plusOne(int[] miss) {
		for (int i = 0; i < miss.length; i++) {
			miss[i] = miss[i] + 1;
		}
	}

	public String toJson() {
		return new JSONSerializer().exclude("*.class").deepSerialize(this);
	}

	public static ShuangSeQiuX fromJsonToShuangSeQiuX(String json) {
		return new JSONDeserializer<ShuangSeQiuX>().use(null,
				ShuangSeQiuX.class).deserialize(json);

	}
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i:red) {
			sb.append(i).append(",");
		}
		if(sb.toString().endsWith(",")) {
			sb.deleteCharAt(sb.length()-1);
		}
		sb.append(";");
		for(int i:blue) {
			sb.append(i).append(",");
		}
		if(sb.toString().endsWith(",")) {
			sb.deleteCharAt(sb.length()-1);
		}
		return sb.toString();
	}

}
