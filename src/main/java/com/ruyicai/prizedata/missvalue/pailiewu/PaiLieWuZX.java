package com.ruyicai.prizedata.missvalue.pailiewu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruyicai.prizedata.domain.PrizeInfo;
import com.ruyicai.prizedata.service.PrizeInfoService;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
/**
 * 排列五普通(选号区)
 * @author liuhongxing
 *
 */
public class PaiLieWuZX {

	public static final String KEY = "T01011MV_ZX";
	private static final String LOTNO = "T01011";

	private int[] wan = new int[10];
	private int[] qian = new int[10];
	private int[] bai = new int[10];
	private int[] shi = new int[10];
	private int[] ge = new int[10];

	public int[] getWan() {
		return wan;
	}

	public void setWan(int[] wan) {
		this.wan = wan;
	}

	public int[] getQian() {
		return qian;
	}

	public void setQian(int[] qian) {
		this.qian = qian;
	}

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
		int[] winInt = { Integer.parseInt(win.charAt(0) + ""),
				Integer.parseInt(win.charAt(1) + ""),
				Integer.parseInt(win.charAt(2) + ""),
				Integer.parseInt(win.charAt(3) + ""),
				Integer.parseInt(win.charAt(4) + "") };

		plusOne(wan);
		plusOne(qian);
		plusOne(bai);
		plusOne(shi);
		plusOne(ge);

		wan[winInt[0]] = 0;
		qian[winInt[1]] = 0;
		bai[winInt[2]] = 0;
		shi[winInt[3]] = 0;
		ge[winInt[4]] = 0;

	}

	
	
	
	
	private boolean refreshZX(String win,Map<String, Object> map,int i) {
		int[] winInt = { Integer.parseInt(win.charAt(0) + ""),
				Integer.parseInt(win.charAt(1) + ""),
				Integer.parseInt(win.charAt(2) + ""),
				Integer.parseInt(win.charAt(3) + ""),
				Integer.parseInt(win.charAt(4) + "") };
		
		if(map.get("4"+winInt[0])!=null) {
			wan[winInt[0]]=i;
			map.remove("4"+winInt[0]);
		}
		
		if(map.get("3"+winInt[1])!=null) {
			qian[winInt[1]]=i;
			map.remove("3"+winInt[1]);
		}
		
		if(map.get("2"+winInt[2])!=null) {
			bai[winInt[2]]=i;
			map.remove("2"+winInt[2]);
		}
		
		if(map.get("1"+winInt[3])!=null) {
			shi[winInt[3]]=i;
			map.remove("1"+winInt[3]);
		}
		
		if(map.get(""+winInt[4])!=null) {
			ge[winInt[4]]=i;
			map.remove(""+winInt[4]);
		}
		
		if(map.isEmpty()) {
			return false;
		}else {
			return true;
		}
	}
	
	
	
	public PaiLieWuZX refresh(PrizeInfoService prizeInfoService,int index) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (int j = 0; j < 50; j++) {
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
	
	private void plusOne(int[] a) {
		for (int i = 0; i < a.length; i++) {
			a[i] = a[i] + 1;
		}
	}

	public String toJson() {
		return new JSONSerializer().exclude("*.class").deepSerialize(this);
	}

	public static PaiLieWuZX fromJsonToPaiLieWuZX(String json) {
		return new JSONDeserializer<PaiLieWuZX>().use(null, PaiLieWuZX.class)
				.deserialize(json);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i : wan) {
			sb.append(i).append(",");
		}
		if (sb.toString().endsWith(",")) {
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.append(";");

		for (int i : qian) {
			sb.append(i).append(",");
		}
		if (sb.toString().endsWith(",")) {
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.append(";");

		for (int i : bai) {
			sb.append(i).append(",");
		}
		if (sb.toString().endsWith(",")) {
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.append(";");

		for (int i : shi) {
			sb.append(i).append(",");
		}
		if (sb.toString().endsWith(",")) {
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.append(";");

		for (int i : ge) {
			sb.append(i).append(",");
		}
		if (sb.toString().endsWith(",")) {
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}
}
