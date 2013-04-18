package com.ruyicai.prizedata.missvalue.qixingcai;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruyicai.prizedata.domain.PrizeInfo;
import com.ruyicai.prizedata.service.PrizeInfoService;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
/**
 * 七星彩普通(选号区)
 * @author liuhongxing
 *
 */
public class QiXingCaiZX {

	public static final String KEY = "T01009MV_ZX";
	private static final String LOTNO = "T01009";
	private int[] baiwan = new int[10];
	private int[] shiwan = new int[10];
	private int[] wan = new int[10];
	private int[] qian = new int[10];
	private int[] bai = new int[10];
	private int[] shi = new int[10];
	private int[] ge = new int[10];
	
	public int[] getBaiwan() {
		return baiwan;
	}

	public void setBaiwan(int[] baiwan) {
		this.baiwan = baiwan;
	}

	public int[] getShiwan() {
		return shiwan;
	}

	public void setShiwan(int[] shiwan) {
		this.shiwan = shiwan;
	}

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
				Integer.parseInt(win.charAt(4) + ""),
				Integer.parseInt(win.charAt(5) + ""),
				Integer.parseInt(win.charAt(6) + "")};

		plusOne(baiwan);
		plusOne(shiwan);
		plusOne(wan);
		plusOne(qian);
		plusOne(bai);
		plusOne(shi);
		plusOne(ge);

		
		baiwan[winInt[0]] = 0;
		shiwan[winInt[1]] = 0;
		wan[winInt[2]] = 0;
		qian[winInt[3]] = 0;
		bai[winInt[4]] = 0;
		shi[winInt[5]] = 0;
		ge[winInt[6]] = 0;

	}
	
	
	
	private boolean refreshZX(String win,Map<String, Object> map,int i) {
		int[] winInt = { Integer.parseInt(win.charAt(0) + ""),
				Integer.parseInt(win.charAt(1) + ""),
				Integer.parseInt(win.charAt(2) + ""),
				Integer.parseInt(win.charAt(3) + ""),
				Integer.parseInt(win.charAt(4) + ""),
				Integer.parseInt(win.charAt(5) + ""),
				Integer.parseInt(win.charAt(6) + "")};
		
		if(map.get("6"+winInt[0])!=null) {
			baiwan[winInt[0]]=i;
			map.remove("6"+winInt[0]);
		}
		
		if(map.get("5"+winInt[1])!=null) {
			shiwan[winInt[1]]=i;
			map.remove("5"+winInt[1]);
		}
		
		if(map.get("4"+winInt[2])!=null) {
			wan[winInt[2]]=i;
			map.remove("4"+winInt[2]);
		}
		
		if(map.get("3"+winInt[3])!=null) {
			qian[winInt[3]]=i;
			map.remove("3"+winInt[3]);
		}
		
		if(map.get("2"+winInt[4])!=null) {
			bai[winInt[4]]=i;
			map.remove("2"+winInt[4]);
		}
		
		if(map.get("1"+winInt[5])!=null) {
			shi[winInt[5]]=i;
			map.remove("1"+winInt[5]);
		}
		
		if(map.get(""+winInt[6])!=null) {
			ge[winInt[6]]=i;
			map.remove(""+winInt[6]);
		}
		
		if(map.isEmpty()) {
			return false;
		}else {
			return true;
		}
	}
	
	public QiXingCaiZX refresh(PrizeInfoService prizeInfoService,int index) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (int j = 0; j < 70; j++) {
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

	public static QiXingCaiZX fromJsonToQiXingCaiZX(String json) {
		return new JSONDeserializer<QiXingCaiZX>().use(null, QiXingCaiZX.class)
				.deserialize(json);
	}
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		
		for (int i : baiwan) {
			sb.append(i).append(",");
		}
		if (sb.toString().endsWith(",")) {
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.append(";");
		
		for (int i : shiwan) {
			sb.append(i).append(",");
		}
		if (sb.toString().endsWith(",")) {
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.append(";");
		
		
		
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
