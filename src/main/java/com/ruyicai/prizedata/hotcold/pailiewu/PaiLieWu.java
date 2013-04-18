package com.ruyicai.prizedata.hotcold.pailiewu;

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

@Service("T01011HC")
public class PaiLieWu implements LotTypeHotCold{

	private static Logger logger = LoggerFactory.getLogger(PaiLieWu.class);

	@Resource
	private PrizeInfoService prizeInfoService;

	@Resource
	private StatisticsDataService statisticsDataService;

	@Autowired
	MemcachedClient memcachedClient;
	
	
	
	@Override
	public void refresh(int countBatch) {
		logger.info("pailiewu refresh hotcold start,countBatch=" + countBatch);
		PaiLieWuXHC plwXHC = new PaiLieWuXHC();
		plwXHC.refresh(prizeInfoService, countBatch);
		StatisticsData data_pailiewux = new StatisticsData(new StatisticsPK(
				PaiLieWuXHC.KEY, "T01011", countBatch + ""));
		data_pailiewux.setValue(plwXHC.toJson());
		statisticsDataService.merge(data_pailiewux);

		logger.info("pailiewu refresh hotcold end,countBatch=" + countBatch);
		
		try {
			logger.info("pailiewu hotcold try to put to cache start:T01011 countBatch=" + countBatch);
			memcachedClient.add(PaiLieWuXHC.KEY+"_"+countBatch, 864000,data_pailiewux);
			
			logger.info("pailiewu hotcold try to put to cache end:T01011 countBatch=" + countBatch);
		}catch (Exception e) {
			logger.info("pailiewu hotcold try to put to cache err:T01011 countBatch=" + countBatch);
		}
		
	}
}
