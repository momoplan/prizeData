package com.ruyicai.prizedata.missvalue.jlkuai3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruyicai.prizedata.domain.PrizeInfo;
import com.ruyicai.prizedata.service.PrizeInfoService;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
/**
 * 快三
 * 二同号单式
 * 二同号单选组合
 * @author liuhongxing
 *
 */
public class JlK301 {

	public static final String KEY = "F47108MV_01";
	private static final String LOTNO = "F47108";

	private int[] dan = new int[6];
	private int[] shuang = new int[6];

	

	public int[] getDan() {
		return dan;
	}


	public void setDan(int[] dan) {
		this.dan = dan;
	}


	public int[] getShuang() {
		return shuang;
	}


	public void setShuang(int[] shuang) {
		this.shuang = shuang;
	}


	public void onPrize(PrizeInfo prizeInfo) {
		int ge = Integer.parseInt(prizeInfo.getWinbasecode().substring(4, 6));
		int shi = Integer.parseInt(prizeInfo.getWinbasecode().substring(2, 4));
		int bai = Integer.parseInt(prizeInfo.getWinbasecode().substring(0, 2));
		plusOne(dan);
		dan[ge-1] = 0;
		dan[shi-1] = 0;
		dan[bai-1] = 0;
		
		plusOne(shuang);
		if(ge==shi) {
			shuang[ge-1] = 0;
		}
		if(ge==bai) {
			shuang[ge-1] = 0;
		}
		if(shi==bai) {
			shuang[shi-1] = 0;
		}
	}
	
	
	public JlK301 refresh(PrizeInfoService prizeInfoService,int index) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (int j = 1; j <= 6; j++) {
			map.put(j+"", 0);
			map.put(j+""+j, 0);
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
				if(this.refresh01(p.getWinbasecode(), map, i)){
					i++;
				}else{
					flag = false;
					break;
				}
			}
		}
		return this;
	}
	
	
	
	private boolean refresh01(String win,Map<String, Object> map,int i) {
		int ge = Integer.parseInt(win.substring(4, 6));
		int shi = Integer.parseInt(win.substring(2, 4));
		int bai = Integer.parseInt(win.substring(0, 2));
		
		if(map.get(ge+"")!=null) {
			dan[ge-1] = i;
			map.remove(ge+"");
		}
		if(map.get(shi+"")!=null) {
			dan[shi-1] = i;
			map.remove(shi+"");
		}
		if(map.get(bai+"")!=null) {
			dan[bai-1] = i;
			map.remove(bai+"");
		}
		
		int same = -1;
		if(ge==shi) {
			same = ge;
		}else if(ge==bai) {
			same = ge;
		}else if(shi==bai) {
			same = shi;
		}
		
		if(same!=-1) {
			if(map.get(same+""+same)!=null) {
				shuang[same-1] = i;
				map.remove(same+""+same);
			}
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

	public static JlK301 fromJsonToJlK301(String json) {
		return new JSONDeserializer<JlK301>().use(null, JlK301.class)
				.deserialize(json);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i:dan) {
			sb.append(i).append(",");
		}
		if(sb.toString().endsWith(",")) {
			sb.deleteCharAt(sb.length()-1);
		}
		sb.append(";");
		for(int i:shuang) {
			sb.append(i).append(",");
		}
		if(sb.toString().endsWith(",")) {
			sb.deleteCharAt(sb.length()-1);
		}
		return sb.toString();
	}
}
