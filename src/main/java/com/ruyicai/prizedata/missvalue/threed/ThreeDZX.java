package com.ruyicai.prizedata.missvalue.threed;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruyicai.prizedata.domain.PrizeInfo;
import com.ruyicai.prizedata.service.PrizeInfoService;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
/**
 * 3D直选(选号区)
 * @author liuhongxing
 *
 */
public class ThreeDZX {

	public static final String KEY = "F47103MV_ZX";
	private static final String LOTNO = "F47103";

	private int[] bai = new int[10];
	private int[] shi = new int[10];
	private int[] ge = new int[10];

	public int[] getBai() {
		return bai;
	}

	public void setBai(int[] bai) {
		this.bai = bai;
	}

	public int[] getShi() {
		return shi;
	}

	public void setShi(int[] shi) {
		this.shi = shi;
	}

	public int[] getGe() {
		return ge;
	}

	public void setGe(int[] ge) {
		this.ge = ge;
	}

	public void onPrize(PrizeInfo prizeInfo) {

		String win = prizeInfo.getWinbasecode();
		int[] winInt = { Integer.parseInt(win.substring(0, 2)),
				Integer.parseInt(win.substring(2, 4)),
				Integer.parseInt(win.substring(4, 6)) };

		plusOne(bai);
		plusOne(shi);
		plusOne(ge);

		bai[winInt[0]] = 0;
		shi[winInt[1]] = 0;
		ge[winInt[2]] = 0;

	}
	
	
	public ThreeDZX refresh(PrizeInfoService prizeInfoService,int index) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (int j = 0; j < 30; j++) {
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
				if(this.refreshZX(p.getWinbasecode(), map, i)){
					i++;
				}else{
					flag = false;
					break;
				}
			}
		}
		
		return this;
		
	}
	
	
	private boolean refreshZX(String win,Map<String, Object> map,int i) {
		int[] winInt = { Integer.parseInt(win.substring(0, 2)),
				Integer.parseInt(win.substring(2, 4)),
				Integer.parseInt(win.substring(4, 6)) };
		
		
		
		if(map.get("2"+winInt[0])!=null) {
			bai[winInt[0]]=i;
			map.remove("2"+winInt[0]);
		}
		
		if(map.get("1"+winInt[1])!=null) {
			shi[winInt[1]]=i;
			map.remove("1"+winInt[1]);
		}
		
		if(map.get(""+winInt[2])!=null) {
			ge[winInt[2]]=i;
			map.remove(""+winInt[2]);
		}
		
		if(map.isEmpty()) {
			return false;
		}else {
			return true;
		}
	}
	

	private void plusOne(int[] a) {
		for (int i = 0; i < a.length; i++) {
			a[i] = a[i] + 1;
		}
	}

	public String toJson() {
		return new JSONSerializer().exclude("*.class").deepSerialize(this);
	}

	public static ThreeDZX fromJsonToThreeDZX(String json) {
		return new JSONDeserializer<ThreeDZX>().use(null, ThreeDZX.class)
				.deserialize(json);
	}
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i:bai) {
			sb.append(i).append(",");
		}
		if(sb.toString().endsWith(",")) {
			sb.deleteCharAt(sb.length()-1);
		}
		sb.append(";");
		
		for(int i:shi) {
			sb.append(i).append(",");
		}
		if(sb.toString().endsWith(",")) {
			sb.deleteCharAt(sb.length()-1);
		}
		sb.append(";");
		
		for(int i:ge) {
			sb.append(i).append(",");
		}
		if(sb.toString().endsWith(",")) {
			sb.deleteCharAt(sb.length()-1);
		}
		return sb.toString();
	}

}
