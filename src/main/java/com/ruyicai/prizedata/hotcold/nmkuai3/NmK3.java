package com.ruyicai.prizedata.hotcold.nmkuai3;

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

@Service("F47107HC")
public class NmK3 implements LotTypeHotCold{

	private static Logger logger = LoggerFactory.getLogger(NmK3.class);
	@Resource
	private PrizeInfoService prizeInfoService;

	@Resource
	private StatisticsDataService statisticsDataService;

	@Autowired
	MemcachedClient memcachedClient;
	
	@Override
	public void refresh(int countBatch) {
		logger.info("nmk3 refresh hotcold start,allcountBatch=" + countBatch);
		List<PrizeInfo> prizeInfos = prizeInfoService.find("F47107", 0, countBatch);
		
		
		List<PrizeInfo> prizeInfos30 = prizeInfos.subList(0, 30);
		List<PrizeInfo> prizeInfos50 = prizeInfos.subList(0, 50);
		List<PrizeInfo> prizeInfos100 = prizeInfos.subList(0, 100);
		

		refreshCount(30, prizeInfos30);
		refreshCount(50, prizeInfos50);
		refreshCount(100, prizeInfos100);
		
		logger.info("nmk3 refresh hotcold end,allcountBatch=" + countBatch);
		
	}

	private void refreshCount(int countBatch, List<PrizeInfo> prizeInfos) {
		logger.info("nmk3 refresh hotcold start,countBatch=" + countBatch);
		NmK3XHC k3hc = new NmK3XHC();
		k3hc.refresh(prizeInfos, countBatch);
		StatisticsData data_nmk3x = new StatisticsData(new StatisticsPK(NmK3XHC.KEY, "F47107", countBatch + ""));
		data_nmk3x.setValue(k3hc.toJson());
		statisticsDataService.merge(data_nmk3x);
		
		NmK301HC k301hc = new NmK301HC();
		k301hc.refresh(prizeInfos, countBatch);
		StatisticsData data_k301hc = new StatisticsData(new StatisticsPK(NmK301HC.KEY, "F47107", countBatch + ""));
		data_k301hc.setValue(k301hc.toJson());
		statisticsDataService.merge(data_k301hc);
		
		NmK302HC k302hc = new NmK302HC();
		k302hc.refresh(prizeInfos, countBatch);
		StatisticsData data_k302hc = new StatisticsData(new StatisticsPK(NmK302HC.KEY, "F47107", countBatch + ""));
		data_k302hc.setValue(k302hc.toJson());
		statisticsDataService.merge(data_k302hc);
		
		NmK310HC k310hc = new NmK310HC();
		k310hc.refresh(prizeInfos, countBatch);
		StatisticsData data_k310hc = new StatisticsData(new StatisticsPK(NmK310HC.KEY, "F47107", countBatch + ""));
		data_k310hc.setValue(k310hc.toJson());
		statisticsDataService.merge(data_k310hc);
		
		NmK330HC k330hc = new NmK330HC();
		k330hc.refresh(prizeInfos, countBatch);
		StatisticsData data_k330hc = new StatisticsData(new StatisticsPK(NmK330HC.KEY, "F47107", countBatch + ""));
		data_k330hc.setValue(k330hc.toJson());
		statisticsDataService.merge(data_k330hc);
		
		NmK340HC k340hc = new NmK340HC();
		k340hc.refresh(prizeInfos, countBatch);
		StatisticsData data_k340hc = new StatisticsData(new StatisticsPK(NmK340HC.KEY, "F47107", countBatch + ""));
		data_k340hc.setValue(k340hc.toJson());
		statisticsDataService.merge(data_k340hc);
		
		NmK350HC k350hc = new NmK350HC();
		k350hc.refresh(prizeInfos, countBatch);
		StatisticsData data_k350hc = new StatisticsData(new StatisticsPK(NmK350HC.KEY, "F47107", countBatch + ""));
		data_k350hc.setValue(k350hc.toJson());
		statisticsDataService.merge(data_k350hc);
		
		NmK3BaseHC k3basehc = new NmK3BaseHC();
		k3basehc.refresh(prizeInfos, countBatch);
		StatisticsData data_k3basehc = new StatisticsData(new StatisticsPK(NmK3BaseHC.KEY, "F47107", countBatch + ""));
		data_k3basehc.setValue(k3basehc.toJson());
		statisticsDataService.merge(data_k3basehc);
		
		

		logger.info("nmk3 refresh hotcold end,countBatch=" + countBatch);
		
		try {
			logger.info("nmk3 hotcold try to put to cache start:F47107 countBatch=" + countBatch);
			memcachedClient.replace(NmK3XHC.KEY+"_"+countBatch, 129600,data_nmk3x);
			memcachedClient.replace(NmK301HC.KEY+"_"+countBatch, 129600,data_k301hc);
			memcachedClient.replace(NmK302HC.KEY+"_"+countBatch, 129600,data_k302hc);
			memcachedClient.replace(NmK310HC.KEY+"_"+countBatch, 129600,data_k310hc);
			memcachedClient.replace(NmK330HC.KEY+"_"+countBatch, 129600,data_k330hc);
			memcachedClient.replace(NmK340HC.KEY+"_"+countBatch, 129600,data_k340hc);
			memcachedClient.replace(NmK350HC.KEY+"_"+countBatch, 129600,data_k350hc);
			memcachedClient.replace(NmK3BaseHC.KEY+"_"+countBatch, 129600,data_k3basehc);
			
			logger.info("nmk3 hotcold try to put to cache end:F47107 countBatch=" + countBatch);
		}catch (Exception e) {
			logger.info("nmk3 hotcold try to put to cache err:F47107 countBatch=" + countBatch);
		}
	}

}
