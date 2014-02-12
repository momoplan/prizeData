package com.ruyicai.prizedata.hotcold.qilecai;

import java.util.List;

import javax.annotation.Resource;

import net.rubyeye.xmemcached.MemcachedClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruyicai.prizedata.domain.PrizeInfo;
import com.ruyicai.prizedata.domain.StatisticsData;
import com.ruyicai.prizedata.domain.StatisticsPK;
import com.ruyicai.prizedata.hotcold.LotTypeHotCold;
import com.ruyicai.prizedata.service.PrizeInfoService;
import com.ruyicai.prizedata.service.StatisticsDataService;

@Service("F47102HC")
public class QiLeCai implements LotTypeHotCold{

	private static Logger logger = LoggerFactory.getLogger(QiLeCai.class);

	@Resource
	private PrizeInfoService prizeInfoService;

	@Resource
	private StatisticsDataService statisticsDataService;

	@Autowired
	MemcachedClient memcachedClient;
	
	@Override
	public void refresh(int countBatch) {
		logger.info("qilecai refresh hotcold start,allcountBatch=" + countBatch);
		List<PrizeInfo> prizeInfos = prizeInfoService.find("F47102", 0, countBatch);
		
		List<PrizeInfo> prizeInfos10 = prizeInfos.subList(0, 10);
		List<PrizeInfo> prizeInfos30 = prizeInfos.subList(0, 30);
		List<PrizeInfo> prizeInfos50 = prizeInfos.subList(0, 50);
		
		refreshCount(10, prizeInfos10);
		refreshCount(30, prizeInfos30);
		refreshCount(50, prizeInfos50);
		
		logger.info("qilecai refresh hotcold end,allcountBatch=" + countBatch);
		
	}

	private void refreshCount(int countBatch, List<PrizeInfo> prizeInfos) {
		
		logger.info("daletou refresh hotcold start,countBatch=" + countBatch);
		QiLeCaiXHC qlcXHC = new QiLeCaiXHC();
		qlcXHC.refresh(prizeInfos, countBatch);
		StatisticsData data_qilecaix = new StatisticsData(new StatisticsPK(
				QiLeCaiXHC.KEY, "F47102", countBatch + ""));
		data_qilecaix.setValue(qlcXHC.toJson());
		statisticsDataService.merge(data_qilecaix);

		logger.info("daletou refresh hotcold end,countBatch=" + countBatch);
		
		try {
			logger.info("qilecai hotcold try to put to cache start:F47102 countBatch=" + countBatch);
			memcachedClient.replace(QiLeCaiXHC.KEY+"_"+countBatch, 864000,data_qilecaix);
			
			logger.info("qilecai hotcold try to put to cache end:F47102 countBatch=" + countBatch);
		}catch (Exception e) {
			logger.info("qilecai hotcold try to put to cache err:F47102 countBatch=" + countBatch);
		}
	}

}
