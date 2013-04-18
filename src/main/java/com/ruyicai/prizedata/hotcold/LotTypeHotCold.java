package com.ruyicai.prizedata.hotcold;



public interface LotTypeHotCold {

	/**
	 * 按照期数刷新冷热号
	 * @param countBatch 期数
	 * @param key 冷热类型
	 */
	public void refresh(int countBatch);
}
