package com.ruyicai.prizedata.hotcold.qixingcai;

import java.util.List;

import com.ruyicai.prizedata.domain.PrizeInfo;
import com.ruyicai.prizedata.service.PrizeInfoService;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

public class QiXingCaiXHC {

	public static final String KEY = "T01009HC_X";
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
	
	
	/**
	 * 指定期数刷新冷热号
	 * @param prizeInfoService
	 * @param countBatch 指定期数
	 * @return
	 */
	public QiXingCaiXHC refresh(PrizeInfoService prizeInfoService,int countBatch) {
		List<PrizeInfo> prizeInfos = prizeInfoService.find(LOTNO, 0, countBatch);
		for(PrizeInfo prizeInfo:prizeInfos) {
			onPrize(prizeInfo);
		}
		return this;
	}
	
	
	private void onPrize(PrizeInfo prizeInfo) {
		String win = prizeInfo.getWinbasecode();
		int[] winInt = { Integer.parseInt(win.charAt(0) + ""),
				Integer.parseInt(win.charAt(1) + ""),
				Integer.parseInt(win.charAt(2) + ""),
				Integer.parseInt(win.charAt(3) + ""),
				Integer.parseInt(win.charAt(4) + ""),
				Integer.parseInt(win.charAt(5) + ""),
				Integer.parseInt(win.charAt(6) + "")};
		
		
		baiwan[winInt[0]] = baiwan[winInt[0]] + 1;
		shiwan[winInt[1]] = shiwan[winInt[1]] + 1;
		wan[winInt[2]] = wan[winInt[2]] + 1;
		qian[winInt[3]] = qian[winInt[3]] + 1;
		bai[winInt[4]] = bai[winInt[4]] + 1;
		shi[winInt[5]] = shi[winInt[5]] + 1;
		ge[winInt[6]] = ge[winInt[6]] + 1;
		
	}
	public String toJson() {
		return new JSONSerializer().exclude("*.class").deepSerialize(this);
	}

	public static QiXingCaiXHC fromJsonToQiXingCaiXHC(String json) {
		return new JSONDeserializer<QiXingCaiXHC>().use(null,
				QiXingCaiXHC.class).deserialize(json);

	}
	
}
