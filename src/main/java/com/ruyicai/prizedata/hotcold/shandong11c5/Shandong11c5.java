package com.ruyicai.prizedata.hotcold.shandong11c5;

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

@Service("T01012HC")
public class Shandong11c5 implements LotTypeHotCold{

	private static Logger logger = LoggerFactory.getLogger(Shandong11c5.class);
	@Resource
	private PrizeInfoService prizeInfoService;

	@Resource
	private StatisticsDataService statisticsDataService;

	@Autowired
	MemcachedClient memcachedClient;
	
	@Override
	public void refresh(int countBatch) {
		logger.info("shandong11c5 refresh hotcold start,allcountBatch=" + countBatch);
		List<PrizeInfo> prizeInfos = prizeInfoService.find("T01012", 0, countBatch);
		
		
		List<PrizeInfo> prizeInfos30 = prizeInfos.subList(0, 30);
		List<PrizeInfo> prizeInfos50 = prizeInfos.subList(0, 50);
		List<PrizeInfo> prizeInfos100 = prizeInfos.subList(0, 100);
		

		refreshCount(30, prizeInfos30);
		refreshCount(50, prizeInfos50);
		refreshCount(100, prizeInfos100);
		
		logger.info("shandong11c5 refresh hotcold end,allcountBatch=" + countBatch);
		
	}
	
	
	
	private void refreshCount(int countBatch, List<PrizeInfo> prizeInfos) {
		logger.info("shandong11c5 refresh hotcold start,countBatch=" + countBatch);
		Shandong11c5Q1HC q1hc = new Shandong11c5Q1HC();
		q1hc.refresh(prizeInfos, countBatch);
		StatisticsData data_q1hc = new StatisticsData(new StatisticsPK(Shandong11c5Q1HC.KEY, "T01012", countBatch + ""));
		data_q1hc.setValue(q1hc.toJson());
		statisticsDataService.merge(data_q1hc);
		
		Shandong11c5Q2HC q2hc = new Shandong11c5Q2HC();
		q2hc.refresh(prizeInfos, countBatch);
		StatisticsData data_q2hc = new StatisticsData(new StatisticsPK(Shandong11c5Q2HC.KEY, "T01012", countBatch + ""));
		data_q2hc.setValue(q2hc.toJson());
		statisticsDataService.merge(data_q2hc);
		
		Shandong11c5Q3HC q3hc = new Shandong11c5Q3HC();
		q3hc.refresh(prizeInfos, countBatch);
		StatisticsData data_q3hc = new StatisticsData(new StatisticsPK(Shandong11c5Q3HC.KEY, "T01012", countBatch + ""));
		data_q3hc.setValue(q3hc.toJson());
		statisticsDataService.merge(data_q3hc);
		
		Shandong11c5Q2ZHC q2zhc = new Shandong11c5Q2ZHC();
		q2zhc.refresh(prizeInfos, countBatch);
		StatisticsData data_q2zhc = new StatisticsData(new StatisticsPK(Shandong11c5Q2ZHC.KEY, "T01012", countBatch + ""));
		data_q2zhc.setValue(q2zhc.toJson());
		statisticsDataService.merge(data_q2zhc);
		
		Shandong11c5Q3ZHC q3zhc = new Shandong11c5Q3ZHC();
		q3zhc.refresh(prizeInfos, countBatch);
		StatisticsData data_q3zhc = new StatisticsData(new StatisticsPK(Shandong11c5Q3ZHC.KEY, "T01012", countBatch + ""));
		data_q3zhc.setValue(q3zhc.toJson());
		statisticsDataService.merge(data_q3zhc);
		
		Shandong11c5RXHC rxhc = new Shandong11c5RXHC();
		rxhc.refresh(prizeInfos, countBatch);
		StatisticsData data_rxhc = new StatisticsData(new StatisticsPK(Shandong11c5RXHC.KEY, "T01012", countBatch + ""));
		data_rxhc.setValue(rxhc.toJson());
		statisticsDataService.merge(data_rxhc);

		logger.info("shandong11c5 refresh hotcold end,countBatch=" + countBatch);
		
		try {
			logger.info("shandong11c5 hotcold try to put to cache start:T01012 countBatch=" + countBatch);
			memcachedClient.replace(Shandong11c5Q1HC.KEY+"_"+countBatch, 129600,data_q1hc);
			memcachedClient.replace(Shandong11c5Q2HC.KEY+"_"+countBatch, 129600,data_q2hc);
			memcachedClient.replace(Shandong11c5Q3HC.KEY+"_"+countBatch, 129600,data_q3hc);
			memcachedClient.replace(Shandong11c5Q2ZHC.KEY+"_"+countBatch, 129600,data_q2zhc);
			memcachedClient.replace(Shandong11c5Q3ZHC.KEY+"_"+countBatch, 129600,data_q3zhc);
			memcachedClient.replace(Shandong11c5RXHC.KEY+"_"+countBatch, 129600,data_rxhc);
			
			logger.info("shandong11c5 hotcold try to put to cache end:T01012 countBatch=" + countBatch);
		}catch (Exception e) {
			logger.info("shandong11c5 hotcold try to put to cache err:T01012 countBatch=" + countBatch);
		}
	}

}
