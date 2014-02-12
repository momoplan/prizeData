package com.ruyicai.prizedata.missvalue.chongqing11c5;

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

@Service("T01016MV")
public class Chongqing11c5 implements LotTypeMissValue {

	
	private static Logger logger = LoggerFactory.getLogger(Chongqing11c5.class);
	
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
		StatisticsData data_chongqing11c5rx = statisticsDataService.findLatestOrByBatchcode("T01016",Chongqing11c5RX.KEY,batchcode);
		StatisticsData data_chongqing11c5q1 = statisticsDataService.findLatestOrByBatchcode("T01016",Chongqing11c5Q1.KEY,batchcode);
		StatisticsData data_chongqing11c5q2 = statisticsDataService.findLatestOrByBatchcode("T01016",Chongqing11c5Q2.KEY,batchcode);
		StatisticsData data_chongqing11c5q3 = statisticsDataService.findLatestOrByBatchcode("T01016",Chongqing11c5Q3.KEY,batchcode);
		StatisticsData data_chongqing11c5q2z = statisticsDataService.findLatestOrByBatchcode("T01016",Chongqing11c5Q2Z.KEY,batchcode);
		StatisticsData data_chongqing11c5q3z = statisticsDataService.findLatestOrByBatchcode("T01016",Chongqing11c5Q3Z.KEY,batchcode);
		StatisticsData data_chongqing11c5q3zh = statisticsDataService.findLatestOrByBatchcode("T01016",Chongqing11c5Q3ZH.KEY,batchcode);
		StatisticsData data_chongqing11c5r5zh = statisticsDataService.findLatestOrByBatchcode("T01016",Chongqing11c5R5ZH.KEY,batchcode);
		StatisticsData data_chongqing11c5r7zh = statisticsDataService.findLatestOrByBatchcode("T01016",Chongqing11c5R7ZH.KEY,batchcode);
		StatisticsData data_chongqing11c5r8zh = statisticsDataService.findLatestOrByBatchcode("T01016",Chongqing11c5R8ZH.KEY,batchcode);
		
		Chongqing11c5RX chongqing11c5rx = Chongqing11c5RX.fromJsonToChongqing11c5RX(data_chongqing11c5rx.getValue());
		Chongqing11c5Q1 chongqing11c5q1 = Chongqing11c5Q1.fromJsonToChongqing11c5Q1(data_chongqing11c5q1.getValue());
		Chongqing11c5Q2 chongqing11c5q2 = Chongqing11c5Q2.fromJsonToChongqing11c5Q2(data_chongqing11c5q2.getValue());
		Chongqing11c5Q3 chongqing11c5q3 = Chongqing11c5Q3.fromJsonToChongqing11c5Q3(data_chongqing11c5q3.getValue());
		Chongqing11c5Q2Z chongqing11c5q2z = Chongqing11c5Q2Z.fromJsonToChongqing11c5Q2Z(data_chongqing11c5q2z.getValue());
		Chongqing11c5Q3Z chongqing11c5q3z = Chongqing11c5Q3Z.fromJsonToChongqing11c5Q3Z(data_chongqing11c5q3z.getValue());
		Chongqing11c5Q3ZH chongqing11c5q3zh = Chongqing11c5Q3ZH.fromJsonToChongqing11c5Q3ZH(data_chongqing11c5q3zh.getValue());
		Chongqing11c5R5ZH chongqing11c5r5zh = Chongqing11c5R5ZH.fromJsonToChongqing11c5R5ZH(data_chongqing11c5r5zh.getValue());
		Chongqing11c5R7ZH chongqing11c5r7zh = Chongqing11c5R7ZH.fromJsonToChongqing11c5R7ZH(data_chongqing11c5r7zh.getValue());
		Chongqing11c5R8ZH chongqing11c5r8zh = Chongqing11c5R8ZH.fromJsonToChongqing11c5R8ZH(data_chongqing11c5r8zh.getValue());
		
		chongqing11c5rx.onPrize(prizeInfo);
		chongqing11c5q1.onPrize(prizeInfo);
		chongqing11c5q2.onPrize(prizeInfo);
		chongqing11c5q3.onPrize(prizeInfo);
		chongqing11c5q2z.onPrize(prizeInfo);
		chongqing11c5q3z.onPrize(prizeInfo);
		chongqing11c5q3zh.onPrize(prizeInfo);
		chongqing11c5r5zh.onPrize(prizeInfo);
		chongqing11c5r7zh.onPrize(prizeInfo);
		chongqing11c5r8zh.onPrize(prizeInfo);
		
		StatisticsData data_chongqing11c5rx_1 = new StatisticsData(new StatisticsPK(Chongqing11c5RX.KEY,prizeInfo.getLotno(),prizeInfo.getBatchcode()));
		StatisticsData data_chongqing11c5q1_1 = new StatisticsData(new StatisticsPK(Chongqing11c5Q1.KEY,prizeInfo.getLotno(),prizeInfo.getBatchcode()));
		StatisticsData data_chongqing11c5q2_1 = new StatisticsData(new StatisticsPK(Chongqing11c5Q2.KEY,prizeInfo.getLotno(),prizeInfo.getBatchcode()));
		StatisticsData data_chongqing11c5q3_1 = new StatisticsData(new StatisticsPK(Chongqing11c5Q3.KEY,prizeInfo.getLotno(),prizeInfo.getBatchcode()));
		StatisticsData data_chongqing11c5q2z_1 = new StatisticsData(new StatisticsPK(Chongqing11c5Q2Z.KEY,prizeInfo.getLotno(),prizeInfo.getBatchcode()));
		StatisticsData data_chongqing11c5q3z_1 = new StatisticsData(new StatisticsPK(Chongqing11c5Q3Z.KEY,prizeInfo.getLotno(),prizeInfo.getBatchcode()));
		StatisticsData data_chongqing11c5q3zh_1 = new StatisticsData(new StatisticsPK(Chongqing11c5Q3ZH.KEY,prizeInfo.getLotno(),prizeInfo.getBatchcode()));
		StatisticsData data_chongqing11c5r5zh_1 = new StatisticsData(new StatisticsPK(Chongqing11c5R5ZH.KEY,prizeInfo.getLotno(),prizeInfo.getBatchcode()));
		StatisticsData data_chongqing11c5r7zh_1 = new StatisticsData(new StatisticsPK(Chongqing11c5R7ZH.KEY,prizeInfo.getLotno(),prizeInfo.getBatchcode()));
		StatisticsData data_chongqing11c5r8zh_1 = new StatisticsData(new StatisticsPK(Chongqing11c5R8ZH.KEY,prizeInfo.getLotno(),prizeInfo.getBatchcode()));
		
		data_chongqing11c5rx_1.setValue(chongqing11c5rx.toJson());
		data_chongqing11c5q1_1.setValue(chongqing11c5q1.toJson());
		data_chongqing11c5q2_1.setValue(chongqing11c5q2.toJson());
		data_chongqing11c5q3_1.setValue(chongqing11c5q3.toJson());
		data_chongqing11c5q2z_1.setValue(chongqing11c5q2z.toJson());
		data_chongqing11c5q3z_1.setValue(chongqing11c5q3z.toJson());
		data_chongqing11c5q3zh_1.setValue(chongqing11c5q3zh.toJson());
		data_chongqing11c5r5zh_1.setValue(chongqing11c5r5zh.toJson());
		data_chongqing11c5r7zh_1.setValue(chongqing11c5r7zh.toJson());
		data_chongqing11c5r8zh_1.setValue(chongqing11c5r8zh.toJson());
		
		
		data_chongqing11c5rx_1.setValueString(chongqing11c5rx.toString());
		data_chongqing11c5q1_1.setValueString(chongqing11c5q1.toString());
		data_chongqing11c5q2_1.setValueString(chongqing11c5q2.toString());
		data_chongqing11c5q3_1.setValueString(chongqing11c5q3.toString());
		data_chongqing11c5q2z_1.setValueString(chongqing11c5q2z.toString());
		data_chongqing11c5q3z_1.setValueString(chongqing11c5q3z.toString());
		data_chongqing11c5q3zh_1.setValueString(chongqing11c5q3zh.toString());
		data_chongqing11c5r5zh_1.setValueString(chongqing11c5r5zh.toString());
		data_chongqing11c5r7zh_1.setValueString(chongqing11c5r7zh.toString());
		data_chongqing11c5r8zh_1.setValueString(chongqing11c5r8zh.toString());
		
		
		statisticsDataService.merge(data_chongqing11c5rx_1);
		statisticsDataService.merge(data_chongqing11c5q1_1);
		statisticsDataService.merge(data_chongqing11c5q2_1);
		statisticsDataService.merge(data_chongqing11c5q3_1);
		statisticsDataService.merge(data_chongqing11c5q2z_1);
		statisticsDataService.merge(data_chongqing11c5q3z_1);
		statisticsDataService.merge(data_chongqing11c5q3zh_1);
		statisticsDataService.merge(data_chongqing11c5r5zh_1);
		statisticsDataService.merge(data_chongqing11c5r7zh_1);
		statisticsDataService.merge(data_chongqing11c5r8zh_1);
		
		logger.info("onPrize end :"+prizeInfo.getLotno()+ " " + prizeInfo.getBatchcode());
		
		try {
			logger.info("onPrize try to put to cache:"+prizeInfo.getLotno()+ " " + prizeInfo.getBatchcode());
			
			memcachedClient.set(Chongqing11c5RX.KEY+"_"+prizeInfo.getBatchcode(), 129600, data_chongqing11c5rx_1);
			memcachedClient.set(Chongqing11c5Q1.KEY+"_"+prizeInfo.getBatchcode(), 129600,data_chongqing11c5q1_1);
			memcachedClient.set(Chongqing11c5Q2.KEY+"_"+prizeInfo.getBatchcode(), 129600,data_chongqing11c5q2_1);
			memcachedClient.set(Chongqing11c5Q3.KEY+"_"+prizeInfo.getBatchcode(), 129600,data_chongqing11c5q3_1);
			memcachedClient.set(Chongqing11c5Q2Z.KEY+"_"+prizeInfo.getBatchcode(), 129600,data_chongqing11c5q2z_1);
			memcachedClient.set(Chongqing11c5Q3Z.KEY+"_"+prizeInfo.getBatchcode(), 129600,data_chongqing11c5q3z_1);
			memcachedClient.set(Chongqing11c5Q3ZH.KEY+"_"+prizeInfo.getBatchcode(), 129600,data_chongqing11c5q3zh_1);
			memcachedClient.set(Chongqing11c5R5ZH.KEY+"_"+prizeInfo.getBatchcode(), 129600,data_chongqing11c5r5zh_1);
			memcachedClient.set(Chongqing11c5R7ZH.KEY+"_"+prizeInfo.getBatchcode(), 129600,data_chongqing11c5r7zh_1);
			memcachedClient.set(Chongqing11c5R8ZH.KEY+"_"+prizeInfo.getBatchcode(), 129600,data_chongqing11c5r8zh_1);
			
			
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
		if(Chongqing11c5RX.KEY.equals(key)) {
			Chongqing11c5RX rx = new Chongqing11c5RX().refresh(prizeInfoService,index);
			sdata.setValue(rx.toJson());
			sdata.setValueString(rx.toString());
		}else if(Chongqing11c5Q1.KEY.equals(key)) {
			Chongqing11c5Q1 q1 = new Chongqing11c5Q1().refresh(prizeInfoService,index);
			sdata.setValue(q1.toJson());
			sdata.setValueString(q1.toString());
		}else if(Chongqing11c5Q2.KEY.equals(key)) {
			Chongqing11c5Q2 q2 = new Chongqing11c5Q2().refresh(prizeInfoService,index);
			sdata.setValue(q2.toJson());
			sdata.setValueString(q2.toString());
		}else if(Chongqing11c5Q3.KEY.equals(key)) {
			Chongqing11c5Q3 q3 = new Chongqing11c5Q3().refresh(prizeInfoService,index);
			sdata.setValue(q3.toJson());
			sdata.setValueString(q3.toString());
		}else if(Chongqing11c5Q2Z.KEY.equals(key)) {
			Chongqing11c5Q2Z q2z = new Chongqing11c5Q2Z().refresh(prizeInfoService,index);
			sdata.setValue(q2z.toJson());
			sdata.setValueString(q2z.toString());
		}else if(Chongqing11c5Q3Z.KEY.equals(key)) {
			Chongqing11c5Q3Z q3z = new Chongqing11c5Q3Z().refresh(prizeInfoService,index);
			sdata.setValue(q3z.toJson());
			sdata.setValueString(q3z.toString());
		}else if(Chongqing11c5Q3ZH.KEY.equals(key)) {
			Chongqing11c5Q3ZH q3zh = new Chongqing11c5Q3ZH().refresh(prizeInfoService,index);
			sdata.setValue(q3zh.toJson());
			sdata.setValueString(q3zh.toString());
		}else if(Chongqing11c5R5ZH.KEY.equals(key)) {
			Chongqing11c5R5ZH r5zh = new Chongqing11c5R5ZH().refresh(prizeInfoService,index);
			sdata.setValue(r5zh.toJson());
			sdata.setValueString(r5zh.toString());
		}else if(Chongqing11c5R7ZH.KEY.equals(key)) {
			Chongqing11c5R7ZH r7zh = new Chongqing11c5R7ZH().refresh(prizeInfoService,index);
			sdata.setValue(r7zh.toJson());
			sdata.setValueString(r7zh.toString());
		}else if(Chongqing11c5R8ZH.KEY.equals(key)) {
			Chongqing11c5R8ZH r8zh = new Chongqing11c5R8ZH().refresh(prizeInfoService,index);
			sdata.setValue(r8zh.toJson());
			sdata.setValueString(r8zh.toString());
		}
		statisticsDataService.merge(sdata);
		logger.info("refresh end:key="+key);
		
	}

	@Override
	public void refreshAll(String batchcode,String lotno) {
		refresh(Chongqing11c5RX.KEY,batchcode,lotno);
		refresh(Chongqing11c5Q1.KEY,batchcode,lotno);
		refresh(Chongqing11c5Q2.KEY,batchcode,lotno);
		refresh(Chongqing11c5Q3.KEY,batchcode,lotno);
		refresh(Chongqing11c5Q2Z.KEY,batchcode,lotno);
		refresh(Chongqing11c5Q3Z.KEY,batchcode,lotno);
		refresh(Chongqing11c5Q3ZH.KEY,batchcode,lotno);
		refresh(Chongqing11c5R5ZH.KEY,batchcode,lotno);
		refresh(Chongqing11c5R7ZH.KEY,batchcode,lotno);
		refresh(Chongqing11c5R8ZH.KEY,batchcode,lotno);
		
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
