package com.ruyicai.prizedata.hotcold.shuangseqiu;

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

@Service("F47104HC")
public class ShuangSeQiu implements LotTypeHotCold {

	private static Logger logger = LoggerFactory.getLogger(ShuangSeQiu.class);
	@Resource
	private PrizeInfoService prizeInfoService;
	
	@Resource
	private StatisticsDataService statisticsDataService;
	
	@Autowired
	MemcachedClient memcachedClient;
	
	@Override
	public void refresh(int countBatch) {
		logger.info("ssq refresh hotcold start,countBatch="+countBatch);
		ShuangSeQiuXHC ssqXHC = new ShuangSeQiuXHC();
		ssqXHC.refresh(prizeInfoService, countBatch);
		
		StatisticsData data_shuangseqiux = new StatisticsData(new StatisticsPK(ShuangSeQiuXHC.KEY, "F47104", countBatch+""));
		data_shuangseqiux.setValue(ssqXHC.toJson());
		
		statisticsDataService.merge(data_shuangseqiux);
		
		logger.info("ssq refresh hotcold end,countBatch="+countBatch);
		
		try {
			logger.info("ssq hotcold try to put to cache start:F47104 countBatch=" + countBatch);
			memcachedClient.add(ShuangSeQiuXHC.KEY+"_"+countBatch, 864000,data_shuangseqiux);
			
			logger.info("ssq hotcold try to put to cache end:F47104 countBatch=" + countBatch);
		}catch (Exception e) {
			logger.info("ssq hotcold try to put to cache err:F47104 countBatch=" + countBatch);
		}

	}

}
