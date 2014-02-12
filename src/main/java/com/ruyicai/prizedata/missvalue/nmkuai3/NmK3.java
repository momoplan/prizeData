package com.ruyicai.prizedata.missvalue.nmkuai3;

import javax.annotation.Resource;

import net.rubyeye.xmemcached.MemcachedClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruyicai.prizedata.domain.PrizeInfo;
import com.ruyicai.prizedata.domain.StatisticsData;
import com.ruyicai.prizedata.domain.StatisticsPK;
import com.ruyicai.prizedata.missvalue.LotTypeMissValue;
import com.ruyicai.prizedata.service.PrizeInfoService;
import com.ruyicai.prizedata.service.StatisticsDataService;

@Service("F47107MV")
public class NmK3 implements LotTypeMissValue{

private static Logger logger = LoggerFactory.getLogger(NmK3.class);
	
	@Resource
	private PrizeInfoService prizeInfoService;
	
	@Resource
	private StatisticsDataService statisticsDataService;
	
	@Autowired
	MemcachedClient memcachedClient;
	
	@Override
	public PrizeInfo transformPrizeInfo(PrizeInfo prizeInfo) {
		prizeInfo.setWinspecialcode("");
		return prizeInfo;
	}

	@Override
	public void onPrize(PrizeInfo prizeInfo, String batchcode) {
		logger.info("onPrize start :"+prizeInfo.getLotno()+ " " + prizeInfo.getBatchcode());
		StatisticsData data_k300 = statisticsDataService.findLatestOrByBatchcode("F47107",NmK300.KEY,batchcode);
		StatisticsData data_k301 = statisticsDataService.findLatestOrByBatchcode("F47107",NmK301.KEY,batchcode);
		StatisticsData data_k302 = statisticsDataService.findLatestOrByBatchcode("F47107",NmK302.KEY,batchcode);
		StatisticsData data_k310 = statisticsDataService.findLatestOrByBatchcode("F47107",NmK310.KEY,batchcode);
		StatisticsData data_k330 = statisticsDataService.findLatestOrByBatchcode("F47107",NmK330.KEY,batchcode);
		StatisticsData data_k340 = statisticsDataService.findLatestOrByBatchcode("F47107",NmK340.KEY,batchcode);
		StatisticsData data_k350 = statisticsDataService.findLatestOrByBatchcode("F47107",NmK350.KEY,batchcode);
		StatisticsData data_k391 = statisticsDataService.findLatestOrByBatchcode("F47107",NmK391.KEY,batchcode);
		StatisticsData data_k392 = statisticsDataService.findLatestOrByBatchcode("F47107",NmK392.KEY,batchcode);
		StatisticsData data_k393 = statisticsDataService.findLatestOrByBatchcode("F47107",NmK393.KEY,batchcode);
		
		NmK300 k300 = NmK300.fromJsonToNmK300(data_k300.getValue());
		NmK301 k301 = NmK301.fromJsonToNmK301(data_k301.getValue());
		NmK302 k302 = NmK302.fromJsonToNmK302(data_k302.getValue());
		NmK310 k310 = NmK310.fromJsonToNmK310(data_k310.getValue());
		NmK330 k330 = NmK330.fromJsonToNmK330(data_k330.getValue());
		NmK340 k340 = NmK340.fromJsonToNmK340(data_k340.getValue());
		NmK350 k350 = NmK350.fromJsonToNmK350(data_k350.getValue());
		NmK391 k391 = NmK391.fromJsonToNmK391(data_k391.getValue());
		NmK392 k392 = NmK392.fromJsonToNmK392(data_k392.getValue());
		NmK393 k393 = NmK393.fromJsonToNmK393(data_k393.getValue());
		
		k300.onPrize(prizeInfo);
		k301.onPrize(prizeInfo);
		k302.onPrize(prizeInfo);
		k310.onPrize(prizeInfo);
		k330.onPrize(prizeInfo);
		k340.onPrize(prizeInfo);
		k350.onPrize(prizeInfo);
		k391.onPrize(prizeInfo);
		k392.onPrize(prizeInfo);
		k393.onPrize(prizeInfo);
		
		StatisticsData data_k300_1 = new StatisticsData(new StatisticsPK(NmK300.KEY, prizeInfo.getLotno(), prizeInfo.getBatchcode()));
		StatisticsData data_k301_1 = new StatisticsData(new StatisticsPK(NmK301.KEY, prizeInfo.getLotno(), prizeInfo.getBatchcode()));
		StatisticsData data_k302_1 = new StatisticsData(new StatisticsPK(NmK302.KEY, prizeInfo.getLotno(), prizeInfo.getBatchcode()));
		StatisticsData data_k310_1 = new StatisticsData(new StatisticsPK(NmK310.KEY, prizeInfo.getLotno(), prizeInfo.getBatchcode()));
		StatisticsData data_k330_1 = new StatisticsData(new StatisticsPK(NmK330.KEY, prizeInfo.getLotno(), prizeInfo.getBatchcode()));
		StatisticsData data_k340_1 = new StatisticsData(new StatisticsPK(NmK340.KEY, prizeInfo.getLotno(), prizeInfo.getBatchcode()));
		StatisticsData data_k350_1 = new StatisticsData(new StatisticsPK(NmK350.KEY, prizeInfo.getLotno(), prizeInfo.getBatchcode()));
		StatisticsData data_k391_1 = new StatisticsData(new StatisticsPK(NmK391.KEY, prizeInfo.getLotno(), prizeInfo.getBatchcode()));
		StatisticsData data_k392_1 = new StatisticsData(new StatisticsPK(NmK392.KEY, prizeInfo.getLotno(), prizeInfo.getBatchcode()));
		StatisticsData data_k393_1 = new StatisticsData(new StatisticsPK(NmK393.KEY, prizeInfo.getLotno(), prizeInfo.getBatchcode()));
		
		data_k300_1.setValue(k300.toJson());
		data_k301_1.setValue(k301.toJson());
		data_k302_1.setValue(k302.toJson());
		data_k310_1.setValue(k310.toJson());
		data_k330_1.setValue(k330.toJson());
		data_k340_1.setValue(k340.toJson());
		data_k350_1.setValue(k350.toJson());
		data_k391_1.setValue(k391.toJson());
		data_k392_1.setValue(k392.toJson());
		data_k393_1.setValue(k393.toJson());
		
		data_k300_1.setValueString(k300.toString());
		data_k301_1.setValueString(k301.toString());
		data_k302_1.setValueString(k302.toString());
		data_k310_1.setValueString(k310.toString());
		data_k330_1.setValueString(k330.toString());
		data_k340_1.setValueString(k340.toString());
		data_k350_1.setValueString(k350.toString());
		data_k391_1.setValueString(k391.toString());
		data_k392_1.setValueString(k392.toString());
		data_k393_1.setValueString(k393.toString());
		
		statisticsDataService.merge(data_k300_1);
		statisticsDataService.merge(data_k301_1);
		statisticsDataService.merge(data_k302_1);
		statisticsDataService.merge(data_k310_1);
		statisticsDataService.merge(data_k330_1);
		statisticsDataService.merge(data_k340_1);
		statisticsDataService.merge(data_k350_1);
		statisticsDataService.merge(data_k391_1);
		statisticsDataService.merge(data_k392_1);
		statisticsDataService.merge(data_k393_1);
		
		logger.info("onPrize end :"+prizeInfo.getLotno()+ " " + prizeInfo.getBatchcode());
		try {
			logger.info("onPrize try to put to cache:"+prizeInfo.getLotno()+ " " + prizeInfo.getBatchcode());
			
			memcachedClient.set(NmK300.KEY+"_"+prizeInfo.getBatchcode(), 129600, data_k300_1);
			memcachedClient.set(NmK301.KEY+"_"+prizeInfo.getBatchcode(), 129600, data_k301_1);
			memcachedClient.set(NmK302.KEY+"_"+prizeInfo.getBatchcode(), 129600, data_k302_1);
			memcachedClient.set(NmK310.KEY+"_"+prizeInfo.getBatchcode(), 129600, data_k310_1);
			memcachedClient.set(NmK330.KEY+"_"+prizeInfo.getBatchcode(), 129600, data_k330_1);
			memcachedClient.set(NmK340.KEY+"_"+prizeInfo.getBatchcode(), 129600, data_k340_1);
			memcachedClient.set(NmK350.KEY+"_"+prizeInfo.getBatchcode(), 129600, data_k350_1);
			memcachedClient.set(NmK391.KEY+"_"+prizeInfo.getBatchcode(), 129600, data_k391_1);
			memcachedClient.set(NmK392.KEY+"_"+prizeInfo.getBatchcode(), 129600, data_k392_1);
			memcachedClient.set(NmK393.KEY+"_"+prizeInfo.getBatchcode(), 129600, data_k393_1);
			logger.info("onPrize put to cache success:"+prizeInfo.getLotno()+ " " + prizeInfo.getBatchcode());
		}catch (Exception e) {
			logger.debug("onPrize put cache exception:"+prizeInfo.getLotno()+ " " + prizeInfo.getBatchcode(), e);
		}
	}

	@Override
	public void refresh(String key, String batchcode, String lotno) {
		logger.info("refresh start:key="+key);
		int index = prizeInfoService.getIndex(lotno, batchcode);
		StatisticsData sdata = new StatisticsData(new StatisticsPK(key,lotno,batchcode));
		if(NmK300.KEY.equals(key)) {
			NmK300 k300 = new NmK300().refresh(prizeInfoService,index);
			sdata.setValue(k300.toJson());
			sdata.setValueString(k300.toString());
		}else if(NmK301.KEY.equals(key)) {
			NmK301 k301 = new NmK301().refresh(prizeInfoService,index);
			sdata.setValue(k301.toJson());
			sdata.setValueString(k301.toString());
		}else if(NmK302.KEY.equals(key)) {
			NmK302 k302 = new NmK302().refresh(prizeInfoService,index);
			sdata.setValue(k302.toJson());
			sdata.setValueString(k302.toString());
		}else if(NmK310.KEY.equals(key)) {
			NmK310 k310 = new NmK310().refresh(prizeInfoService,index);
			sdata.setValue(k310.toJson());
			sdata.setValueString(k310.toString());
		}else if(NmK330.KEY.equals(key)) {
			NmK330 k330 = new NmK330().refresh(prizeInfoService,index);
			sdata.setValue(k330.toJson());
			sdata.setValueString(k330.toString());
		}else if(NmK340.KEY.equals(key)) {
			NmK340 k340 = new NmK340().refresh(prizeInfoService,index);
			sdata.setValue(k340.toJson());
			sdata.setValueString(k340.toString());
		}else if(NmK350.KEY.equals(key)) {
			NmK350 k350 = new NmK350().refresh(prizeInfoService,index);
			sdata.setValue(k350.toJson());
			sdata.setValueString(k350.toString());
		}else if(NmK391.KEY.equals(key)) {
			NmK391 k391 = new NmK391().refresh(prizeInfoService,index);
			sdata.setValue(k391.toJson());
			sdata.setValueString(k391.toString());
		}else if(NmK392.KEY.equals(key)) {
			NmK392 k392 = new NmK392().refresh(prizeInfoService,index);
			sdata.setValue(k392.toJson());
			sdata.setValueString(k392.toString());
		}else if(NmK393.KEY.equals(key)) {
			NmK393 k393 = new NmK393().refresh(prizeInfoService,index);
			sdata.setValue(k393.toJson());
			sdata.setValueString(k393.toString());
		}
		statisticsDataService.merge(sdata);
		logger.info("refresh end:key="+key);
		
	}

	@Override
	public void refreshAll(String batchcode, String lotno) {
		refresh(NmK300.KEY,batchcode,lotno);
		refresh(NmK301.KEY,batchcode,lotno);
		refresh(NmK302.KEY,batchcode,lotno);
		refresh(NmK310.KEY,batchcode,lotno);
		refresh(NmK330.KEY,batchcode,lotno);
		refresh(NmK340.KEY,batchcode,lotno);
		refresh(NmK350.KEY,batchcode,lotno);
		refresh(NmK391.KEY,batchcode,lotno);
		refresh(NmK392.KEY,batchcode,lotno);
		refresh(NmK393.KEY,batchcode,lotno);
		
	}

	@Override
	public void refreshAllByOnPrize(String lotno, String batchcode,
			String preBatchcode) {
		logger.info("refreshAllByOnPrize start:lotno="+lotno+" batchcode="+batchcode+" prebatchcode="+preBatchcode);
		PrizeInfo prizeInfo = prizeInfoService.findByLotnoAndBatchcode(lotno, preBatchcode);
		onPrize(prizeInfo, batchcode);
		logger.info("refreshAllByOnPrize end="+lotno+" batchcode="+batchcode+" prebatchcode="+preBatchcode);
		
	}

}
