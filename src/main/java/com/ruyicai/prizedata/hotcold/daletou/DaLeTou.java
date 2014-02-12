package com.ruyicai.prizedata.hotcold.daletou;

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

@Service("T01001HC")
public class DaLeTou implements LotTypeHotCold {

	private static Logger logger = LoggerFactory.getLogger(DaLeTou.class);

	@Resource
	private PrizeInfoService prizeInfoService;

	@Resource
	private StatisticsDataService statisticsDataService;

	@Autowired
	MemcachedClient memcachedClient;

	@Override
	public void refresh(int countBatch) {
		logger.info("daletou refresh hotcold start,allcountBatch=" + countBatch);
		List<PrizeInfo> prizeInfos = prizeInfoService.find("T01001", 0, countBatch);
		
		List<PrizeInfo> prizeInfos10 = prizeInfos.subList(0, 10);
		List<PrizeInfo> prizeInfos30 = prizeInfos.subList(0, 30);
		List<PrizeInfo> prizeInfos50 = prizeInfos.subList(0, 50);
		
		refreshCount(10, prizeInfos10);
		refreshCount(30, prizeInfos30);
		refreshCount(50, prizeInfos50);
		
		logger.info("daletou refresh hotcold end,allcountBatch=" + countBatch);
	}

	private void refreshCount(int countBatch, List<PrizeInfo> prizeInfos) {
		logger.info("daletou refresh hotcold start,countBatch=" + countBatch);
		DaLeTouXHC dltXHC = new DaLeTouXHC();
		dltXHC.refresh(prizeInfos, countBatch);
		StatisticsData data_daletoux = new StatisticsData(new StatisticsPK(
				DaLeTouXHC.KEY, "T01001", countBatch + ""));
		data_daletoux.setValue(dltXHC.toJson());
		statisticsDataService.merge(data_daletoux);

		logger.info("daletou refresh hotcold end,countBatch=" + countBatch);
		
		try {
			logger.info("daletou hotcold try to put to cache start:T01001 countBatch=" + countBatch);
			memcachedClient.replace(DaLeTouXHC.KEY+"_"+countBatch, 864000,data_daletoux);
			
			logger.info("daletou hotcold try to put to cache end:T01001 countBatch=" + countBatch);
		}catch (Exception e) {
			logger.info("daletou hotcold try to put to cache err:T01001 countBatch=" + countBatch);
		}
	}

}
