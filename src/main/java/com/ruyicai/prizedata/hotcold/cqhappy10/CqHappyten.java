package com.ruyicai.prizedata.hotcold.cqhappy10;

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

@Service("F47109HC")
public class CqHappyten implements LotTypeHotCold{
	
	private static Logger logger = LoggerFactory.getLogger(CqHappyten.class);
	@Resource
	private PrizeInfoService prizeInfoService;

	@Resource
	private StatisticsDataService statisticsDataService;

	@Autowired
	MemcachedClient memcachedClient;

	@Override
	public void refresh(int countBatch) {
		logger.info("cqhappyten refresh hotcold start,allcountBatch=" + countBatch);
		List<PrizeInfo> prizeInfos = prizeInfoService.find("F47109", 0, countBatch);
		
		
		List<PrizeInfo> prizeInfos30 = prizeInfos.subList(0, 30);
		List<PrizeInfo> prizeInfos50 = prizeInfos.subList(0, 50);
		List<PrizeInfo> prizeInfos100 = prizeInfos.subList(0, 100);
		
		refreshCount(30, prizeInfos30);
		refreshCount(50, prizeInfos50);
		refreshCount(100, prizeInfos100);
		
		logger.info("cqhappyten refresh hotcold end,allcountBatch=" + countBatch);
		
	}

	
	private void refreshCount(int countBatch, List<PrizeInfo> prizeInfos) {
		logger.info("cqhappyten refresh hotcold start,countBatch=" + countBatch);
		CqHappytenH1HC h1 = new CqHappytenH1HC();
		h1.refresh(prizeInfos, countBatch);
		StatisticsData data_h1 = new StatisticsData(new StatisticsPK(CqHappytenH1HC.KEY, "F47109", countBatch + ""));
		data_h1.setValue(h1.toJson());
		statisticsDataService.merge(data_h1);
		
		
		CqHappytenQ3HC q3 = new CqHappytenQ3HC();
		q3.refresh(prizeInfos, countBatch);
		StatisticsData data_q3 = new StatisticsData(new StatisticsPK(CqHappytenQ3HC.KEY, "F47109", countBatch + ""));
		data_q3.setValue(q3.toJson());
		statisticsDataService.merge(data_q3);
		
		
		CqHappytenRXHC rx = new CqHappytenRXHC();
		rx.refresh(prizeInfos, countBatch);
		StatisticsData data_rx = new StatisticsData(new StatisticsPK(CqHappytenRXHC.KEY, "F47109", countBatch + ""));
		data_rx.setValue(rx.toJson());
		statisticsDataService.merge(data_rx);
		
		CqHappytenS1HC s1 = new CqHappytenS1HC();
		s1.refresh(prizeInfos, countBatch);
		StatisticsData data_s1 = new StatisticsData(new StatisticsPK(CqHappytenS1HC.KEY, "F47109", countBatch + ""));
		data_s1.setValue(s1.toJson());
		statisticsDataService.merge(data_s1);
		
		logger.info("cqhappyten refresh hotcold end,countBatch=" + countBatch);
		
		try {
			logger.info("cqhappyten hotcold try to put to cache start:F47109 countBatch=" + countBatch);
			memcachedClient.replace(CqHappytenH1HC.KEY+"_"+countBatch, 129600,data_h1);
			memcachedClient.replace(CqHappytenQ3HC.KEY+"_"+countBatch, 129600,data_q3);
			memcachedClient.replace(CqHappytenRXHC.KEY+"_"+countBatch, 129600,data_rx);
			memcachedClient.replace(CqHappytenS1HC.KEY+"_"+countBatch, 129600,data_s1);
			
			logger.info("cqhappyten hotcold try to put to cache end:F47109 countBatch=" + countBatch);
		}catch (Exception e) {
			logger.info("cqhappyten hotcold try to put to cache err:F47109 countBatch=" + countBatch);
		}
		
	}
}
