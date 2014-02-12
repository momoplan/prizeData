package com.ruyicai.prizedata.hotcold.five22;

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

@Service("T01013HC")
public class Five22 implements LotTypeHotCold{

	
	private static Logger logger = LoggerFactory.getLogger(Five22.class);

	@Resource
	private PrizeInfoService prizeInfoService;

	@Resource
	private StatisticsDataService statisticsDataService;

	@Autowired
	MemcachedClient memcachedClient;
	
	
	
	@Override
	public void refresh(int countBatch) {
		logger.info("five22 refresh hotcold start,allcountBatch=" + countBatch);
		List<PrizeInfo> prizeInfos = prizeInfoService.find("T01013", 0, countBatch);
		
		List<PrizeInfo> prizeInfos10 = prizeInfos.subList(0, 10);
		List<PrizeInfo> prizeInfos30 = prizeInfos.subList(0, 30);
		List<PrizeInfo> prizeInfos50 = prizeInfos.subList(0, 50);
		
		refreshCount(10, prizeInfos10);
		refreshCount(30, prizeInfos30);
		refreshCount(50, prizeInfos50);
		
		logger.info("five22 refresh hotcold end,allcountBatch=" + countBatch);
		
	}



	private void refreshCount(int countBatch, List<PrizeInfo> prizeInfos) {
		logger.info("five22 refresh hotcold start,countBatch=" + countBatch);
		Five22XHC fiveXHC = new Five22XHC();
		fiveXHC.refresh(prizeInfos, countBatch);
		StatisticsData data_five22x = new StatisticsData(new StatisticsPK(
				Five22XHC.KEY, "T01013", countBatch + ""));
		data_five22x.setValue(fiveXHC.toJson());
		statisticsDataService.merge(data_five22x);

		logger.info("five22 refresh hotcold end,countBatch=" + countBatch);
		
		try {
			logger.info("five22 hotcold try to put to cache start:T01013 countBatch=" + countBatch);
			memcachedClient.replace(Five22XHC.KEY+"_"+countBatch, 864000,data_five22x);
			
			logger.info("five22 hotcold try to put to cache end:T01013 countBatch=" + countBatch);
		}catch (Exception e) {
			logger.info("five22 hotcold try to put to cache err:T01013 countBatch=" + countBatch);
		}
	}

}
