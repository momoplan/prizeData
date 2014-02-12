package com.ruyicai.prizedata.hotcold.shuangseqiu;

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
		logger.info("ssq refresh hotcold start,allcountBatch="+countBatch);
		List<PrizeInfo> prizeInfos = prizeInfoService.find("F47104", 0, countBatch);
		
		List<PrizeInfo> prizeInfos10 = prizeInfos.subList(0, 10);
		List<PrizeInfo> prizeInfos30 = prizeInfos.subList(0, 30);
		List<PrizeInfo> prizeInfos50 = prizeInfos.subList(0, 50);
		
		refreshCount(10, prizeInfos10);
		refreshCount(30, prizeInfos30);
		refreshCount(50, prizeInfos50);
		
		logger.info("ssq refresh hotcold end,allcountBatch="+countBatch);

	}

	private void refreshCount(int countBatch, List<PrizeInfo> prizeInfos) {
		
		logger.info("ssq refresh hotcold start,countBatch="+countBatch);
		ShuangSeQiuXHC ssqXHC = new ShuangSeQiuXHC();
		ssqXHC.refresh(prizeInfos, countBatch);
		
		StatisticsData data_shuangseqiux = new StatisticsData(new StatisticsPK(ShuangSeQiuXHC.KEY, "F47104", countBatch+""));
		data_shuangseqiux.setValue(ssqXHC.toJson());
		
		statisticsDataService.merge(data_shuangseqiux);
		
		logger.info("ssq refresh hotcold end,countBatch="+countBatch);
		
		try {
			logger.info("ssq hotcold try to put to cache start:F47104 countBatch=" + countBatch);
			memcachedClient.replace(ShuangSeQiuXHC.KEY+"_"+countBatch, 864000,data_shuangseqiux);
			
			logger.info("ssq hotcold try to put to cache end:F47104 countBatch=" + countBatch);
		}catch (Exception e) {
			logger.info("ssq hotcold try to put to cache err:F47104 countBatch=" + countBatch);
		}
	}

}
