package com.ruyicai.prizedata.hotcold.qixingcai;

import javax.annotation.Resource;

import net.rubyeye.xmemcached.MemcachedClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		logger.info("qixingcai refresh hotcold start,countBatch=" + countBatch);
		QiXingCaiXHC qxcXHC = new QiXingCaiXHC();
		qxcXHC.refresh(prizeInfoService, countBatch);
		StatisticsData data_qixingcaix = new StatisticsData(new StatisticsPK(
				QiXingCaiXHC.KEY, "T01009", countBatch + ""));
		data_qixingcaix.setValue(qxcXHC.toJson());
		statisticsDataService.merge(data_qixingcaix);

		logger.info("qixingcai refresh hotcold end,countBatch=" + countBatch);
		
		try {
			logger.info("qixingcai hotcold try to put to cache start:T01009 countBatch=" + countBatch);
			memcachedClient.add(QiXingCaiXHC.KEY+"_"+countBatch, 864000,data_qixingcaix);
			
			logger.info("qixingcai hotcold try to put to cache end:T01009 countBatch=" + countBatch);
		}catch (Exception e) {
			logger.info("qixingcai hotcold try to put to cache err:T01009 countBatch=" + countBatch);
		}
		
	}

}
