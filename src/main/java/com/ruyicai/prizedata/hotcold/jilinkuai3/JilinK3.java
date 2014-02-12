package com.ruyicai.prizedata.hotcold.jilinkuai3;

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

@Service("F47108HC")
public class JilinK3 implements LotTypeHotCold{

	private static Logger logger = LoggerFactory.getLogger(JilinK3.class);
	@Resource
	private PrizeInfoService prizeInfoService;

	@Resource
	private StatisticsDataService statisticsDataService;

	@Autowired
	MemcachedClient memcachedClient;
	
	@Override
	public void refresh(int countBatch) {
		logger.info("jilink3 refresh hotcold start,allcountBatch=" + countBatch);
		List<PrizeInfo> prizeInfos = prizeInfoService.find("F47108", 0, countBatch);
		
		
		List<PrizeInfo> prizeInfos30 = prizeInfos.subList(0, 30);
		List<PrizeInfo> prizeInfos50 = prizeInfos.subList(0, 50);
		List<PrizeInfo> prizeInfos100 = prizeInfos.subList(0, 100);
		

		refreshCount(30, prizeInfos30);
		refreshCount(50, prizeInfos50);
		refreshCount(100, prizeInfos100);
		
		logger.info("jilink3 refresh hotcold end,allcountBatch=" + countBatch);
		
	}

	private void refreshCount(int countBatch, List<PrizeInfo> prizeInfos) {
		logger.info("jilink3 refresh hotcold start,countBatch=" + countBatch);
		JilinK3XHC k3hc = new JilinK3XHC();
		k3hc.refresh(prizeInfos, countBatch);
		StatisticsData data_k3x = new StatisticsData(new StatisticsPK(JilinK3XHC.KEY, "F47108", countBatch + ""));
		data_k3x.setValue(k3hc.toJson());
		statisticsDataService.merge(data_k3x);
		
		JilinK301HC k301hc = new JilinK301HC();
		k301hc.refresh(prizeInfos, countBatch);
		StatisticsData data_k301hc = new StatisticsData(new StatisticsPK(JilinK301HC.KEY, "F47108", countBatch + ""));
		data_k301hc.setValue(k301hc.toJson());
		statisticsDataService.merge(data_k301hc);
		
		JilinK302HC k302hc = new JilinK302HC();
		k302hc.refresh(prizeInfos, countBatch);
		StatisticsData data_k302hc = new StatisticsData(new StatisticsPK(JilinK302HC.KEY, "F47108", countBatch + ""));
		data_k302hc.setValue(k302hc.toJson());
		statisticsDataService.merge(data_k302hc);
		
		JilinK310HC k310hc = new JilinK310HC();
		k310hc.refresh(prizeInfos, countBatch);
		StatisticsData data_k310hc = new StatisticsData(new StatisticsPK(JilinK310HC.KEY, "F47108", countBatch + ""));
		data_k310hc.setValue(k310hc.toJson());
		statisticsDataService.merge(data_k310hc);
		
		JilinK330HC k330hc = new JilinK330HC();
		k330hc.refresh(prizeInfos, countBatch);
		StatisticsData data_k330hc = new StatisticsData(new StatisticsPK(JilinK330HC.KEY, "F47108", countBatch + ""));
		data_k330hc.setValue(k330hc.toJson());
		statisticsDataService.merge(data_k330hc);
		
		JilinK340HC k340hc = new JilinK340HC();
		k340hc.refresh(prizeInfos, countBatch);
		StatisticsData data_k340hc = new StatisticsData(new StatisticsPK(JilinK340HC.KEY, "F47108", countBatch + ""));
		data_k340hc.setValue(k340hc.toJson());
		statisticsDataService.merge(data_k340hc);
		
		JilinK350HC k350hc = new JilinK350HC();
		k350hc.refresh(prizeInfos, countBatch);
		StatisticsData data_k350hc = new StatisticsData(new StatisticsPK(JilinK350HC.KEY, "F47108", countBatch + ""));
		data_k350hc.setValue(k350hc.toJson());
		statisticsDataService.merge(data_k350hc);
		
		JilinK3BaseHC k3basehc = new JilinK3BaseHC();
		k3basehc.refresh(prizeInfos, countBatch);
		StatisticsData data_k3basehc = new StatisticsData(new StatisticsPK(JilinK3BaseHC.KEY, "F47108", countBatch + ""));
		data_k3basehc.setValue(k3basehc.toJson());
		statisticsDataService.merge(data_k3basehc);
		
		

		logger.info("jilink3 refresh hotcold end,countBatch=" + countBatch);
		
		try {
			logger.info("jilink3 hotcold try to put to cache start:F47108 countBatch=" + countBatch);
			memcachedClient.replace(JilinK3XHC.KEY+"_"+countBatch, 129600,data_k3x);
			memcachedClient.replace(JilinK301HC.KEY+"_"+countBatch, 129600,data_k301hc);
			memcachedClient.replace(JilinK302HC.KEY+"_"+countBatch, 129600,data_k302hc);
			memcachedClient.replace(JilinK310HC.KEY+"_"+countBatch, 129600,data_k310hc);
			memcachedClient.replace(JilinK330HC.KEY+"_"+countBatch, 129600,data_k330hc);
			memcachedClient.replace(JilinK340HC.KEY+"_"+countBatch, 129600,data_k340hc);
			memcachedClient.replace(JilinK350HC.KEY+"_"+countBatch, 129600,data_k350hc);
			memcachedClient.replace(JilinK3BaseHC.KEY+"_"+countBatch, 129600,data_k3basehc);
			
			logger.info("jilink3 hotcold try to put to cache end:F47108 countBatch=" + countBatch);
		}catch (Exception e) {
			logger.info("jilink3 hotcold try to put to cache err:F47108 countBatch=" + countBatch);
		}
	}

}
