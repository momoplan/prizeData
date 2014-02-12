package com.ruyicai.prizedata.missvalue.jlkuai3;

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

@Service("F47108MV")
public class JlK3 implements LotTypeMissValue{

private static Logger logger = LoggerFactory.getLogger(JlK3.class);
	
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
		StatisticsData data_k300 = statisticsDataService.findLatestOrByBatchcode("F47108",JlK300.KEY,batchcode);
		StatisticsData data_k301 = statisticsDataService.findLatestOrByBatchcode("F47108",JlK301.KEY,batchcode);
		StatisticsData data_k302 = statisticsDataService.findLatestOrByBatchcode("F47108",JlK302.KEY,batchcode);
		StatisticsData data_k310 = statisticsDataService.findLatestOrByBatchcode("F47108",JlK310.KEY,batchcode);
		StatisticsData data_k330 = statisticsDataService.findLatestOrByBatchcode("F47108",JlK330.KEY,batchcode);
		StatisticsData data_k340 = statisticsDataService.findLatestOrByBatchcode("F47108",JlK340.KEY,batchcode);
		StatisticsData data_k350 = statisticsDataService.findLatestOrByBatchcode("F47108",JlK350.KEY,batchcode);
		StatisticsData data_k391 = statisticsDataService.findLatestOrByBatchcode("F47108",JlK391.KEY,batchcode);
		StatisticsData data_k392 = statisticsDataService.findLatestOrByBatchcode("F47108",JlK392.KEY,batchcode);
		StatisticsData data_k393 = statisticsDataService.findLatestOrByBatchcode("F47108",JlK393.KEY,batchcode);
		
		JlK300 k300 = JlK300.fromJsonToJlK300(data_k300.getValue());
		JlK301 k301 = JlK301.fromJsonToJlK301(data_k301.getValue());
		JlK302 k302 = JlK302.fromJsonToJlK302(data_k302.getValue());
		JlK310 k310 = JlK310.fromJsonToJlK310(data_k310.getValue());
		JlK330 k330 = JlK330.fromJsonToJlK330(data_k330.getValue());
		JlK340 k340 = JlK340.fromJsonToJlK340(data_k340.getValue());
		JlK350 k350 = JlK350.fromJsonToJlK350(data_k350.getValue());
		JlK391 k391 = JlK391.fromJsonToJlK391(data_k391.getValue());
		JlK392 k392 = JlK392.fromJsonToJlK392(data_k392.getValue());
		JlK393 k393 = JlK393.fromJsonToJlK393(data_k393.getValue());
		
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
		
		StatisticsData data_k300_1 = new StatisticsData(new StatisticsPK(JlK300.KEY, prizeInfo.getLotno(), prizeInfo.getBatchcode()));
		StatisticsData data_k301_1 = new StatisticsData(new StatisticsPK(JlK301.KEY, prizeInfo.getLotno(), prizeInfo.getBatchcode()));
		StatisticsData data_k302_1 = new StatisticsData(new StatisticsPK(JlK302.KEY, prizeInfo.getLotno(), prizeInfo.getBatchcode()));
		StatisticsData data_k310_1 = new StatisticsData(new StatisticsPK(JlK310.KEY, prizeInfo.getLotno(), prizeInfo.getBatchcode()));
		StatisticsData data_k330_1 = new StatisticsData(new StatisticsPK(JlK330.KEY, prizeInfo.getLotno(), prizeInfo.getBatchcode()));
		StatisticsData data_k340_1 = new StatisticsData(new StatisticsPK(JlK340.KEY, prizeInfo.getLotno(), prizeInfo.getBatchcode()));
		StatisticsData data_k350_1 = new StatisticsData(new StatisticsPK(JlK350.KEY, prizeInfo.getLotno(), prizeInfo.getBatchcode()));
		StatisticsData data_k391_1 = new StatisticsData(new StatisticsPK(JlK391.KEY, prizeInfo.getLotno(), prizeInfo.getBatchcode()));
		StatisticsData data_k392_1 = new StatisticsData(new StatisticsPK(JlK392.KEY, prizeInfo.getLotno(), prizeInfo.getBatchcode()));
		StatisticsData data_k393_1 = new StatisticsData(new StatisticsPK(JlK393.KEY, prizeInfo.getLotno(), prizeInfo.getBatchcode()));
		
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
			
			memcachedClient.set(JlK300.KEY+"_"+prizeInfo.getBatchcode(), 129600, data_k300_1);
			memcachedClient.set(JlK301.KEY+"_"+prizeInfo.getBatchcode(), 129600, data_k301_1);
			memcachedClient.set(JlK302.KEY+"_"+prizeInfo.getBatchcode(), 129600, data_k302_1);
			memcachedClient.set(JlK310.KEY+"_"+prizeInfo.getBatchcode(), 129600, data_k310_1);
			memcachedClient.set(JlK330.KEY+"_"+prizeInfo.getBatchcode(), 129600, data_k330_1);
			memcachedClient.set(JlK340.KEY+"_"+prizeInfo.getBatchcode(), 129600, data_k340_1);
			memcachedClient.set(JlK350.KEY+"_"+prizeInfo.getBatchcode(), 129600, data_k350_1);
			memcachedClient.set(JlK391.KEY+"_"+prizeInfo.getBatchcode(), 129600, data_k391_1);
			memcachedClient.set(JlK392.KEY+"_"+prizeInfo.getBatchcode(), 129600, data_k392_1);
			memcachedClient.set(JlK393.KEY+"_"+prizeInfo.getBatchcode(), 129600, data_k393_1);
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
		if(JlK300.KEY.equals(key)) {
			JlK300 k300 = new JlK300().refresh(prizeInfoService,index);
			sdata.setValue(k300.toJson());
			sdata.setValueString(k300.toString());
		}else if(JlK301.KEY.equals(key)) {
			JlK301 k301 = new JlK301().refresh(prizeInfoService,index);
			sdata.setValue(k301.toJson());
			sdata.setValueString(k301.toString());
		}else if(JlK302.KEY.equals(key)) {
			JlK302 k302 = new JlK302().refresh(prizeInfoService,index);
			sdata.setValue(k302.toJson());
			sdata.setValueString(k302.toString());
		}else if(JlK310.KEY.equals(key)) {
			JlK310 k310 = new JlK310().refresh(prizeInfoService,index);
			sdata.setValue(k310.toJson());
			sdata.setValueString(k310.toString());
		}else if(JlK330.KEY.equals(key)) {
			JlK330 k330 = new JlK330().refresh(prizeInfoService,index);
			sdata.setValue(k330.toJson());
			sdata.setValueString(k330.toString());
		}else if(JlK340.KEY.equals(key)) {
			JlK340 k340 = new JlK340().refresh(prizeInfoService,index);
			sdata.setValue(k340.toJson());
			sdata.setValueString(k340.toString());
		}else if(JlK350.KEY.equals(key)) {
			JlK350 k350 = new JlK350().refresh(prizeInfoService,index);
			sdata.setValue(k350.toJson());
			sdata.setValueString(k350.toString());
		}else if(JlK391.KEY.equals(key)) {
			JlK391 k391 = new JlK391().refresh(prizeInfoService,index);
			sdata.setValue(k391.toJson());
			sdata.setValueString(k391.toString());
		}else if(JlK392.KEY.equals(key)) {
			JlK392 k392 = new JlK392().refresh(prizeInfoService,index);
			sdata.setValue(k392.toJson());
			sdata.setValueString(k392.toString());
		}else if(JlK393.KEY.equals(key)) {
			JlK393 k393 = new JlK393().refresh(prizeInfoService,index);
			sdata.setValue(k393.toJson());
			sdata.setValueString(k393.toString());
		}
		statisticsDataService.merge(sdata);
		logger.info("refresh end:key="+key);
		
	}

	@Override
	public void refreshAll(String batchcode, String lotno) {
		refresh(JlK300.KEY,batchcode,lotno);
		refresh(JlK301.KEY,batchcode,lotno);
		refresh(JlK302.KEY,batchcode,lotno);
		refresh(JlK310.KEY,batchcode,lotno);
		refresh(JlK330.KEY,batchcode,lotno);
		refresh(JlK340.KEY,batchcode,lotno);
		refresh(JlK350.KEY,batchcode,lotno);
		refresh(JlK391.KEY,batchcode,lotno);
		refresh(JlK392.KEY,batchcode,lotno);
		refresh(JlK393.KEY,batchcode,lotno);
		
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
