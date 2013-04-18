package com.ruyicai.prizedata.hotcold.five22;

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
		logger.info("five22 refresh hotcold start,countBatch=" + countBatch);
		Five22XHC fiveXHC = new Five22XHC();
		fiveXHC.refresh(prizeInfoService, countBatch);
		StatisticsData data_five22x = new StatisticsData(new StatisticsPK(
				Five22XHC.KEY, "T01013", countBatch + ""));
		data_five22x.setValue(fiveXHC.toJson());
		statisticsDataService.merge(data_five22x);

		logger.info("five22 refresh hotcold end,countBatch=" + countBatch);
		
		try {
			logger.info("five22 hotcold try to put to cache start:T01013 countBatch=" + countBatch);
			memcachedClient.add(Five22XHC.KEY+"_"+countBatch, 864000,data_five22x);
			
			logger.info("five22 hotcold try to put to cache end:T01013 countBatch=" + countBatch);
		}catch (Exception e) {
			logger.info("five22 hotcold try to put to cache err:T01013 countBatch=" + countBatch);
		}
		
	}

}
