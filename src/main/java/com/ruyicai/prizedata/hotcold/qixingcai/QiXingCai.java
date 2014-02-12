package com.ruyicai.prizedata.hotcold.qixingcai;

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

@Service("T01009HC")
public class QiXingCai implements LotTypeHotCold{

	private static Logger logger = LoggerFactory.getLogger(QiXingCai.class);

	@Resource
	private PrizeInfoService prizeInfoService;

	@Resource
	private StatisticsDataService statisticsDataService;

	@Autowired
	MemcachedClient memcachedClient;
	
	
	@Override
	public void refresh(int countBatch) {
		logger.info("qixingcai refresh hotcold start,allcountBatch=" + countBatch);
		List<PrizeInfo> prizeInfos = prizeInfoService.find("T01009", 0, countBatch);
		
		List<PrizeInfo> prizeInfos10 = prizeInfos.subList(0, 10);
		List<PrizeInfo> prizeInfos30 = prizeInfos.subList(0, 30);
		List<PrizeInfo> prizeInfos50 = prizeInfos.subList(0, 50);
		
		refreshCount(10, prizeInfos10);
		refreshCount(30, prizeInfos30);
		refreshCount(50, prizeInfos50);
		
		logger.info("qixingcai refresh hotcold end,allcountBatch=" + countBatch);
		
	}


	private void refreshCount(int countBatch, List<PrizeInfo> prizeInfos) {
		
		logger.info("qixingcai refresh hotcold start,countBatch=" + countBatch);
		QiXingCaiXHC qxcXHC = new QiXingCaiXHC();
		qxcXHC.refresh(prizeInfos, countBatch);
		StatisticsData data_qixingcaix = new StatisticsData(new StatisticsPK(
				QiXingCaiXHC.KEY, "T01009", countBatch + ""));
		data_qixingcaix.setValue(qxcXHC.toJson());
		statisticsDataService.merge(data_qixingcaix);

		logger.info("qixingcai refresh hotcold end,countBatch=" + countBatch);
		
		try {
			logger.info("qixingcai hotcold try to put to cache start:T01009 countBatch=" + countBatch);
			memcachedClient.replace(QiXingCaiXHC.KEY+"_"+countBatch, 864000,data_qixingcaix);
			
			logger.info("qixingcai hotcold try to put to cache end:T01009 countBatch=" + countBatch);
		}catch (Exception e) {
			logger.info("qixingcai hotcold try to put to cache err:T01009 countBatch=" + countBatch);
		}
	}

}
