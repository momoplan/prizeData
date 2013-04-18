package com.ruyicai.prizedata.missvalue.guangdong11c5;

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

@Service("T01014MV")
public class Guangdong11c5 implements LotTypeMissValue {

	
	private static Logger logger = LoggerFactory.getLogger(Guangdong11c5.class);
	
	@Resource
	private PrizeInfoService prizeInfoService;
	
	@Resource
	private StatisticsDataService statisticsDataService;
	
	@Autowired
	MemcachedClient memcachedClient;
	
	@Override
	public PrizeInfo transformPrizeInfo(PrizeInfo prizeInfo) {
		prizeInfo.setWinbasecode(prizeInfo.getWinbasecode().replace(" ", ""));
		prizeInfo.setWinspecialcode("");
		return prizeInfo;
	}

	@Override
	public void onPrize(PrizeInfo prizeInfo,String batchcode) {
		
		logger.info("onPrize start :"+prizeInfo.getLotno()+ " " + prizeInfo.getBatchcode());
		StatisticsData data_guangdong11c5rx = statisticsDataService.findLatestOrByBatchcode("T01014",Guangdong11c5RX.KEY,batchcode);
		StatisticsData data_guangdong11c5q1 = statisticsDataService.findLatestOrByBatchcode("T01014",Guangdong11c5Q1.KEY,batchcode);
		StatisticsData data_guangdong11c5q2 = statisticsDataService.findLatestOrByBatchcode("T01014",Guangdong11c5Q2.KEY,batchcode);
		StatisticsData data_guangdong11c5q3 = statisticsDataService.findLatestOrByBatchcode("T01014",Guangdong11c5Q3.KEY,batchcode);
		StatisticsData data_guangdong11c5q2z = statisticsDataService.findLatestOrByBatchcode("T01014",Guangdong11c5Q2Z.KEY,batchcode);
		StatisticsData data_guangdong11c5q3z = statisticsDataService.findLatestOrByBatchcode("T01014",Guangdong11c5Q3Z.KEY,batchcode);
		StatisticsData data_guangdong11c5q3zh = statisticsDataService.findLatestOrByBatchcode("T01014",Guangdong11c5Q3ZH.KEY,batchcode);
		StatisticsData data_guangdong11c5qr5zh = statisticsDataService.findLatestOrByBatchcode("T01014",Guangdong11c5R5ZH.KEY,batchcode);
		StatisticsData data_guangdong11c5qr7zh = statisticsDataService.findLatestOrByBatchcode("T01014",Guangdong11c5R7ZH.KEY,batchcode);
		StatisticsData data_guangdong11c5qr8zh = statisticsDataService.findLatestOrByBatchcode("T01014",Guangdong11c5R8ZH.KEY,batchcode);
		
		Guangdong11c5RX guangdong11c5rx = Guangdong11c5RX.fromJsonToGuangdong11c5RX(data_guangdong11c5rx.getValue());
		Guangdong11c5Q1 guangdong11c5q1 = Guangdong11c5Q1.fromJsonToGuangdong11c5Q1(data_guangdong11c5q1.getValue());
		Guangdong11c5Q2 guangdong11c5q2 = Guangdong11c5Q2.fromJsonToGuangdong11c5Q2(data_guangdong11c5q2.getValue());
		Guangdong11c5Q3 guangdong11c5q3 = Guangdong11c5Q3.fromJsonToGuangdong11c5Q3(data_guangdong11c5q3.getValue());
		Guangdong11c5Q2Z guangdong11c5q2z = Guangdong11c5Q2Z.fromJsonToGuangdong11c5Q2Z(data_guangdong11c5q2z.getValue());
		Guangdong11c5Q3Z guangdong11c5q3z = Guangdong11c5Q3Z.fromJsonToGuangdong11c5Q3Z(data_guangdong11c5q3z.getValue());
		Guangdong11c5Q3ZH guangdong11c5q3zh = Guangdong11c5Q3ZH.fromJsonToGuangdong11c5Q3ZH(data_guangdong11c5q3zh.getValue());
		Guangdong11c5R5ZH guangdong11c5qr5zh = Guangdong11c5R5ZH.fromJsonToGuangdong11c5R5ZH(data_guangdong11c5qr5zh.getValue());
		Guangdong11c5R7ZH guangdong11c5qr7zh = Guangdong11c5R7ZH.fromJsonToGuangdong11c5R7ZH(data_guangdong11c5qr7zh.getValue());
		Guangdong11c5R8ZH guangdong11c5qr8zh = Guangdong11c5R8ZH.fromJsonToGuangdong11c5R8ZH(data_guangdong11c5qr8zh.getValue());
		
		guangdong11c5rx.onPrize(prizeInfo);
		guangdong11c5q1.onPrize(prizeInfo);
		guangdong11c5q2.onPrize(prizeInfo);
		guangdong11c5q3.onPrize(prizeInfo);
		guangdong11c5q2z.onPrize(prizeInfo);
		guangdong11c5q3z.onPrize(prizeInfo);
		guangdong11c5q3zh.onPrize(prizeInfo);
		guangdong11c5qr5zh.onPrize(prizeInfo);
		guangdong11c5qr7zh.onPrize(prizeInfo);
		guangdong11c5qr8zh.onPrize(prizeInfo);
		
		StatisticsData data_guangdong11c5rx_1 = new StatisticsData(new StatisticsPK(Guangdong11c5RX.KEY,prizeInfo.getLotno(),prizeInfo.getBatchcode()));
		StatisticsData data_guangdong11c5q1_1 = new StatisticsData(new StatisticsPK(Guangdong11c5Q1.KEY,prizeInfo.getLotno(),prizeInfo.getBatchcode()));
		StatisticsData data_guangdong11c5q2_1 = new StatisticsData(new StatisticsPK(Guangdong11c5Q2.KEY,prizeInfo.getLotno(),prizeInfo.getBatchcode()));
		StatisticsData data_guangdong11c5q3_1 = new StatisticsData(new StatisticsPK(Guangdong11c5Q3.KEY,prizeInfo.getLotno(),prizeInfo.getBatchcode()));
		StatisticsData data_guangdong11c5q2z_1 = new StatisticsData(new StatisticsPK(Guangdong11c5Q2Z.KEY,prizeInfo.getLotno(),prizeInfo.getBatchcode()));
		StatisticsData data_guangdong11c5q3z_1 = new StatisticsData(new StatisticsPK(Guangdong11c5Q3Z.KEY,prizeInfo.getLotno(),prizeInfo.getBatchcode()));
		StatisticsData data_guangdong11c5q3zh_1 = new StatisticsData(new StatisticsPK(Guangdong11c5Q3ZH.KEY,prizeInfo.getLotno(),prizeInfo.getBatchcode()));
		StatisticsData data_guangdong11c5qr5zh_1 = new StatisticsData(new StatisticsPK(Guangdong11c5R5ZH.KEY,prizeInfo.getLotno(),prizeInfo.getBatchcode()));
		StatisticsData data_guangdong11c5qr7zh_1 = new StatisticsData(new StatisticsPK(Guangdong11c5R7ZH.KEY,prizeInfo.getLotno(),prizeInfo.getBatchcode()));
		StatisticsData data_guangdong11c5qr8zh_1 = new StatisticsData(new StatisticsPK(Guangdong11c5R8ZH.KEY,prizeInfo.getLotno(),prizeInfo.getBatchcode()));
		
		data_guangdong11c5rx_1.setValue(guangdong11c5rx.toJson());
		data_guangdong11c5q1_1.setValue(guangdong11c5q1.toJson());
		data_guangdong11c5q2_1.setValue(guangdong11c5q2.toJson());
		data_guangdong11c5q3_1.setValue(guangdong11c5q3.toJson());
		data_guangdong11c5q2z_1.setValue(guangdong11c5q2z.toJson());
		data_guangdong11c5q3z_1.setValue(guangdong11c5q3z.toJson());
		data_guangdong11c5q3zh_1.setValue(guangdong11c5q3zh.toJson());
		data_guangdong11c5qr5zh_1.setValue(guangdong11c5qr5zh.toJson());
		data_guangdong11c5qr7zh_1.setValue(guangdong11c5qr7zh.toJson());
		data_guangdong11c5qr8zh_1.setValue(guangdong11c5qr8zh.toJson());
		
		
		data_guangdong11c5rx_1.setValueString(guangdong11c5rx.toString());
		data_guangdong11c5q1_1.setValueString(guangdong11c5q1.toString());
		data_guangdong11c5q2_1.setValueString(guangdong11c5q2.toString());
		data_guangdong11c5q3_1.setValueString(guangdong11c5q3.toString());
		data_guangdong11c5q2z_1.setValueString(guangdong11c5q2z.toString());
		data_guangdong11c5q3z_1.setValueString(guangdong11c5q3z.toString());
		data_guangdong11c5q3zh_1.setValueString(guangdong11c5q3zh.toString());
		data_guangdong11c5qr5zh_1.setValueString(guangdong11c5qr5zh.toString());
		data_guangdong11c5qr7zh_1.setValueString(guangdong11c5qr7zh.toString());
		data_guangdong11c5qr8zh_1.setValueString(guangdong11c5qr8zh.toString());
		
		
		statisticsDataService.merge(data_guangdong11c5rx_1);
		statisticsDataService.merge(data_guangdong11c5q1_1);
		statisticsDataService.merge(data_guangdong11c5q2_1);
		statisticsDataService.merge(data_guangdong11c5q3_1);
		statisticsDataService.merge(data_guangdong11c5q2z_1);
		statisticsDataService.merge(data_guangdong11c5q3z_1);
		statisticsDataService.merge(data_guangdong11c5q3zh_1);
		statisticsDataService.merge(data_guangdong11c5qr5zh_1);
		statisticsDataService.merge(data_guangdong11c5qr7zh_1);
		statisticsDataService.merge(data_guangdong11c5qr8zh_1);
		
		logger.info("onPrize end :"+prizeInfo.getLotno()+ " " + prizeInfo.getBatchcode());
		
		try {
			logger.info("onPrize try to put to cache:"+prizeInfo.getLotno()+ " " + prizeInfo.getBatchcode());
			
			
			memcachedClient.set(Guangdong11c5RX.KEY+"_"+prizeInfo.getBatchcode(), 129600, data_guangdong11c5rx_1);
			memcachedClient.set(Guangdong11c5Q1.KEY+"_"+prizeInfo.getBatchcode(), 129600,data_guangdong11c5q1_1);
			memcachedClient.set(Guangdong11c5Q2.KEY+"_"+prizeInfo.getBatchcode(), 129600,data_guangdong11c5q2_1);
			memcachedClient.set(Guangdong11c5Q3.KEY+"_"+prizeInfo.getBatchcode(), 129600,data_guangdong11c5q3_1);
			memcachedClient.set(Guangdong11c5Q2Z.KEY+"_"+prizeInfo.getBatchcode(), 129600,data_guangdong11c5q2z_1);
			memcachedClient.set(Guangdong11c5Q3Z.KEY+"_"+prizeInfo.getBatchcode(), 129600,data_guangdong11c5q3z_1);
			memcachedClient.set(Guangdong11c5Q3ZH.KEY+"_"+prizeInfo.getBatchcode(), 129600,data_guangdong11c5q3zh_1);
			memcachedClient.set(Guangdong11c5R5ZH.KEY+"_"+prizeInfo.getBatchcode(), 129600,data_guangdong11c5qr5zh_1);
			memcachedClient.set(Guangdong11c5R7ZH.KEY+"_"+prizeInfo.getBatchcode(), 129600,data_guangdong11c5qr7zh_1);
			memcachedClient.set(Guangdong11c5R8ZH.KEY+"_"+prizeInfo.getBatchcode(), 129600,data_guangdong11c5qr8zh_1);
			
			
			logger.info("onPrize put to cache success:"+prizeInfo.getLotno()+ " " + prizeInfo.getBatchcode());
		}catch (Exception e) {
			logger.debug("onPrize put cache exception:"+prizeInfo.getLotno()+ " " + prizeInfo.getBatchcode(), e);
		}
		
	}


	@Override
	public void refresh(String key,String batchcode,String lotno) {
		logger.info("refresh start:key="+key);
		int index = prizeInfoService.getIndex(lotno, batchcode);
		StatisticsData sdata = new StatisticsData(new StatisticsPK(key,lotno,batchcode));
		if(Guangdong11c5RX.KEY.equals(key)) {
			Guangdong11c5RX rx = new Guangdong11c5RX().refresh(prizeInfoService,index);
			sdata.setValue(rx.toJson());
			sdata.setValueString(rx.toString());
		}else if(Guangdong11c5Q1.KEY.equals(key)) {
			Guangdong11c5Q1 q1 = new Guangdong11c5Q1().refresh(prizeInfoService,index);
			sdata.setValue(q1.toJson());
			sdata.setValueString(q1.toString());
		}else if(Guangdong11c5Q2.KEY.equals(key)) {
			Guangdong11c5Q2 q2 = new Guangdong11c5Q2().refresh(prizeInfoService,index);
			sdata.setValue(q2.toJson());
			sdata.setValueString(q2.toString());
		}else if(Guangdong11c5Q3.KEY.equals(key)) {
			Guangdong11c5Q3 q3 = new Guangdong11c5Q3().refresh(prizeInfoService,index);
			sdata.setValue(q3.toJson());
			sdata.setValueString(q3.toString());
		}else if(Guangdong11c5Q2Z.KEY.equals(key)) {
			Guangdong11c5Q2Z q2z = new Guangdong11c5Q2Z().refresh(prizeInfoService,index);
			sdata.setValue(q2z.toJson());
			sdata.setValueString(q2z.toString());
		}else if(Guangdong11c5Q3Z.KEY.equals(key)) {
			Guangdong11c5Q3Z q3z = new Guangdong11c5Q3Z().refresh(prizeInfoService,index);
			sdata.setValue(q3z.toJson());
			sdata.setValueString(q3z.toString());
		}else if(Guangdong11c5Q3ZH.KEY.equals(key)) {
			Guangdong11c5Q3ZH q3zh = new Guangdong11c5Q3ZH().refresh(prizeInfoService,index);
			sdata.setValue(q3zh.toJson());
			sdata.setValueString(q3zh.toString());
		}else if(Guangdong11c5R5ZH.KEY.equals(key)) {
			Guangdong11c5R5ZH r5zh = new Guangdong11c5R5ZH().refresh(prizeInfoService,index);
			sdata.setValue(r5zh.toJson());
			sdata.setValueString(r5zh.toString());
		}else if(Guangdong11c5R7ZH.KEY.equals(key)) {
			Guangdong11c5R7ZH r7zh = new Guangdong11c5R7ZH().refresh(prizeInfoService,index);
			sdata.setValue(r7zh.toJson());
			sdata.setValueString(r7zh.toString());
		}else if(Guangdong11c5R8ZH.KEY.equals(key)) {
			Guangdong11c5R8ZH r8zh = new Guangdong11c5R8ZH().refresh(prizeInfoService,index);
			sdata.setValue(r8zh.toJson());
			sdata.setValueString(r8zh.toString());
		}
		statisticsDataService.merge(sdata);
		logger.info("refresh end:key="+key);
		
	}

	@Override
	public void refreshAll(String batchcode,String lotno) {
		refresh(Guangdong11c5RX.KEY,batchcode,lotno);
		refresh(Guangdong11c5Q1.KEY,batchcode,lotno);
		refresh(Guangdong11c5Q2.KEY,batchcode,lotno);
		refresh(Guangdong11c5Q3.KEY,batchcode,lotno);
		refresh(Guangdong11c5Q2Z.KEY,batchcode,lotno);
		refresh(Guangdong11c5Q3Z.KEY,batchcode,lotno);
		refresh(Guangdong11c5Q3ZH.KEY,batchcode,lotno);
		refresh(Guangdong11c5R5ZH.KEY,batchcode,lotno);
		refresh(Guangdong11c5R7ZH.KEY,batchcode,lotno);
		refresh(Guangdong11c5R8ZH.KEY,batchcode,lotno);
		
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
