package com.ruyicai.prizedata.missvalue;

import com.ruyicai.prizedata.domain.PrizeInfo;

public interface LotTypeMissValue {

	/**
	 * 
	 * @param prizeInfo
	 * @return
	 */
	public PrizeInfo transformPrizeInfo(PrizeInfo prizeInfo);
	
	/**
	 * 
	 * @param prizeInfo
	 */
	public void onPrize(PrizeInfo prizeInfo,String batchcode);
	
	/**
	 * 
	 * @param key
	 */
	public void refresh(String key,String batchcode,String lotno);
	
	
	/**
	 * 
	 */
	public void refreshAll(String batchcode,String lotno);
	
	/**
	 * 用preBatchcode的开奖刷新preBatchcode遗漏，比如刷第二期，要用第一期的遗漏数据和第二期的开奖数据
	 * @param lotno 彩种编号
	 * @param batchcode 要使用的遗漏数据
	 * @param preBatchcode 要使用的开奖数据
	 */
	public void refreshAllByOnPrize(String lotno,String batchcode,String preBatchcode);
	
	
	
}
