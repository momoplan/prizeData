package com.ruyicai.prizedata.hotcold.gdhappy10;

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

@Service("T01015HC")
public class GdHappyten implements LotTypeHotCold{
	
	private static Logger logger = LoggerFactory.getLogger(GdHappyten.class);
	@Resource
	private PrizeInfoService prizeInfoService;

	@Resource
	private StatisticsDataService statisticsDataService;

	@Autowired
	MemcachedClient memcachedClient;

	@Override
	public void refresh(int countBatch) {
		logger.info("gdhappyten refresh hotcold start,allcountBatch=" + countBatch);
		List<PrizeInfo> prizeInfos = prizeInfoService.find("T01015", 0, countBatch);
		
		
		List<PrizeInfo> prizeInfos30 = prizeInfos.subList(0, 30);
		List<PrizeInfo> prizeInfos50 = prizeInfos.subList(0, 50);
		List<PrizeInfo> prizeInfos100 = prizeInfos.subList(0, 100);
		
		refreshCount(30, prizeInfos30);
		refreshCount(50, prizeInfos50);
		refreshCount(100, prizeInfos100);
		
		logger.info("gdhappyten refresh hotcold end,allcountBatch=" + countBatch);
		
	}

	
	private void refreshCount(int countBatch, List<PrizeInfo> prizeInfos) {
		logger.info("gdhappyten refresh hotcold start,countBatch=" + countBatch);
		GdHappytenH1HC h1 = new GdHappytenH1HC();
		h1.refresh(prizeInfos, countBatch);
		StatisticsData data_h1 = new StatisticsData(new StatisticsPK(GdHappytenH1HC.KEY, "T01015", countBatch + ""));
		data_h1.setValue(h1.toJson());
		statisticsDataService.merge(data_h1);
		
		
		GdHappytenQ3HC q3 = new GdHappytenQ3HC();
		q3.refresh(prizeInfos, countBatch);
		StatisticsData data_q3 = new StatisticsData(new StatisticsPK(GdHappytenQ3HC.KEY, "T01015", countBatch + ""));
		data_q3.setValue(q3.toJson());
		statisticsDataService.merge(data_q3);
		
		
		GdHappytenRXHC rx = new GdHappytenRXHC();
		rx.refresh(prizeInfos, countBatch);
		StatisticsData data_rx = new StatisticsData(new StatisticsPK(GdHappytenRXHC.KEY, "T01015", countBatch + ""));
		data_rx.setValue(rx.toJson());
		statisticsDataService.merge(data_rx);
		
		GdHappytenS1HC s1 = new GdHappytenS1HC();
		s1.refresh(prizeInfos, countBatch);
		StatisticsData data_s1 = new StatisticsData(new StatisticsPK(GdHappytenS1HC.KEY, "T01015", countBatch + ""));
		data_s1.setValue(s1.toJson());
		statisticsDataService.merge(data_s1);
		
		logger.info("gdhappyten refresh hotcold end,countBatch=" + countBatch);
		
		try {
			logger.info("gdhappyten hotcold try to put to cache start:T01015 countBatch=" + countBatch);
			memcachedClient.replace(GdHappytenH1HC.KEY+"_"+countBatch, 129600,data_h1);
			memcachedClient.replace(GdHappytenQ3HC.KEY+"_"+countBatch, 129600,data_q3);
			memcachedClient.replace(GdHappytenRXHC.KEY+"_"+countBatch, 129600,data_rx);
			memcachedClient.replace(GdHappytenS1HC.KEY+"_"+countBatch, 129600,data_s1);
			
			logger.info("gdhappyten hotcold try to put to cache end:T01015 countBatch=" + countBatch);
		}catch (Exception e) {
			logger.info("gdhappyten hotcold try to put to cache err:T01015 countBatch=" + countBatch);
		}
		
	}
}
