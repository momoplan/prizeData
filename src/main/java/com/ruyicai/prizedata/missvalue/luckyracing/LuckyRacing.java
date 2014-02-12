package com.ruyicai.prizedata.missvalue.luckyracing;

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

@Service("T01018MV")
public class LuckyRacing implements LotTypeMissValue{

	private static Logger logger = LoggerFactory.getLogger(LuckyRacing.class);
	
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
		StatisticsData data_dd = statisticsDataService.findLatestOrByBatchcode("T01018",LuckyRacingDD.KEY,batchcode);
		StatisticsData data_pc = statisticsDataService.findLatestOrByBatchcode("T01018",LuckyRacingPlace.KEY,batchcode);
		StatisticsData data_q1 = statisticsDataService.findLatestOrByBatchcode("T01018",LuckyRacingQ1.KEY,batchcode);
		StatisticsData data_q2 = statisticsDataService.findLatestOrByBatchcode("T01018",LuckyRacingQ2.KEY,batchcode);
		StatisticsData data_q3 = statisticsDataService.findLatestOrByBatchcode("T01018",LuckyRacingQ3.KEY,batchcode);
		StatisticsData data_z2 = statisticsDataService.findLatestOrByBatchcode("T01018",LuckyRacingZ2.KEY,batchcode);
		StatisticsData data_z3 = statisticsDataService.findLatestOrByBatchcode("T01018",LuckyRacingZ3.KEY,batchcode);
		
		LuckyRacingDD lrdd = LuckyRacingDD.fromJsonToLuckyRacingDD(data_dd.getValue());
		LuckyRacingPlace lrpc = LuckyRacingPlace.fromJsonToLuckyRacingPlace(data_pc.getValue());
		LuckyRacingQ1 lrq1 = LuckyRacingQ1.fromJsonToLuckyRacingQ1(data_q1.getValue());
		LuckyRacingQ2 lrq2 = LuckyRacingQ2.fromJsonToLuckyRacingQ2(data_q2.getValue());
		LuckyRacingQ3 lrq3 = LuckyRacingQ3.fromJsonToLuckyRacingQ3(data_q3.getValue());
		LuckyRacingZ2 lrz2 = LuckyRacingZ2.fromJsonToLuckyRacingZ2(data_z2.getValue());
		LuckyRacingZ3 lrz3 = LuckyRacingZ3.fromJsonToLuckyRacingZ3(data_z3.getValue());
		
		lrdd.onPrize(prizeInfo);
		lrpc.onPrize(prizeInfo);
		lrq1.onPrize(prizeInfo);
		lrq2.onPrize(prizeInfo);
		lrq3.onPrize(prizeInfo);
		lrz2.onPrize(prizeInfo);
		lrz3.onPrize(prizeInfo);
		
		StatisticsData data_dd_1 = new StatisticsData(new StatisticsPK(LuckyRacingDD.KEY, prizeInfo.getLotno(), prizeInfo.getBatchcode()));
		StatisticsData data_pc_1 = new StatisticsData(new StatisticsPK(LuckyRacingPlace.KEY, prizeInfo.getLotno(), prizeInfo.getBatchcode()));
		StatisticsData data_q1_1 = new StatisticsData(new StatisticsPK(LuckyRacingQ1.KEY, prizeInfo.getLotno(), prizeInfo.getBatchcode()));
		StatisticsData data_q2_1 = new StatisticsData(new StatisticsPK(LuckyRacingQ2.KEY, prizeInfo.getLotno(), prizeInfo.getBatchcode()));
		StatisticsData data_q3_1 = new StatisticsData(new StatisticsPK(LuckyRacingQ3.KEY, prizeInfo.getLotno(), prizeInfo.getBatchcode()));
		StatisticsData data_z2_1 = new StatisticsData(new StatisticsPK(LuckyRacingZ2.KEY, prizeInfo.getLotno(), prizeInfo.getBatchcode()));
		StatisticsData data_z3_1 = new StatisticsData(new StatisticsPK(LuckyRacingZ3.KEY, prizeInfo.getLotno(), prizeInfo.getBatchcode()));
		
		data_dd_1.setValue(lrdd.toJson());
		data_pc_1.setValue(lrpc.toJson());
		data_q1_1.setValue(lrq1.toJson());
		data_q2_1.setValue(lrq2.toJson());
		data_q3_1.setValue(lrq3.toJson());
		data_z2_1.setValue(lrz2.toJson());
		data_z3_1.setValue(lrz3.toJson());
		
		data_dd_1.setValueString(lrdd.toString());
		data_pc_1.setValueString(lrpc.toString());
		data_q1_1.setValueString(lrq1.toString());
		data_q2_1.setValueString(lrq2.toString());
		data_q3_1.setValueString(lrq3.toString());
		data_z2_1.setValueString(lrz2.toString());
		data_z3_1.setValueString(lrz3.toString());
		
		statisticsDataService.merge(data_dd_1);
		statisticsDataService.merge(data_pc_1);
		statisticsDataService.merge(data_q1_1);
		statisticsDataService.merge(data_q2_1);
		statisticsDataService.merge(data_q3_1);
		statisticsDataService.merge(data_z2_1);
		statisticsDataService.merge(data_z3_1);
		
		logger.info("onPrize end :"+prizeInfo.getLotno()+ " " + prizeInfo.getBatchcode());
		
		try {
			logger.info("onPrize try to put to cache:"+prizeInfo.getLotno()+ " " + prizeInfo.getBatchcode());
			
			memcachedClient.set(LuckyRacingDD.KEY+"_"+prizeInfo.getBatchcode(), 129600, data_dd_1);
			memcachedClient.set(LuckyRacingPlace.KEY+"_"+prizeInfo.getBatchcode(), 129600, data_pc_1);
			memcachedClient.set(LuckyRacingQ1.KEY+"_"+prizeInfo.getBatchcode(), 129600, data_q1_1);
			memcachedClient.set(LuckyRacingQ2.KEY+"_"+prizeInfo.getBatchcode(), 129600, data_q2_1);
			memcachedClient.set(LuckyRacingQ3.KEY+"_"+prizeInfo.getBatchcode(), 129600, data_q3_1);
			memcachedClient.set(LuckyRacingZ2.KEY+"_"+prizeInfo.getBatchcode(), 129600, data_z2_1);
			memcachedClient.set(LuckyRacingZ3.KEY+"_"+prizeInfo.getBatchcode(), 129600, data_z3_1);
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
		if(LuckyRacingDD.KEY.equals(key)) {
			LuckyRacingDD lrdd = new LuckyRacingDD().refresh(prizeInfoService,index);
			sdata.setValue(lrdd.toJson());
			sdata.setValueString(lrdd.toString());
		}else if(LuckyRacingPlace.KEY.equals(key)) {
			LuckyRacingPlace lrpc = new LuckyRacingPlace().refresh(prizeInfoService,index);
			sdata.setValue(lrpc.toJson());
			sdata.setValueString(lrpc.toString());
		}else if(LuckyRacingQ1.KEY.equals(key)) {
			LuckyRacingQ1 lrq1 = new LuckyRacingQ1().refresh(prizeInfoService,index);
			sdata.setValue(lrq1.toJson());
			sdata.setValueString(lrq1.toString());
		}else if(LuckyRacingQ2.KEY.equals(key)) {
			LuckyRacingQ2 lrq2 = new LuckyRacingQ2().refresh(prizeInfoService,index);
			sdata.setValue(lrq2.toJson());
			sdata.setValueString(lrq2.toString());
		}else if(LuckyRacingQ3.KEY.equals(key)) {
			LuckyRacingQ3 lrq3 = new LuckyRacingQ3().refresh(prizeInfoService,index);
			sdata.setValue(lrq3.toJson());
			sdata.setValueString(lrq3.toString());
		}else if(LuckyRacingZ2.KEY.equals(key)) {
			LuckyRacingZ2 lrz2 = new LuckyRacingZ2().refresh(prizeInfoService,index);
			sdata.setValue(lrz2.toJson());
			sdata.setValueString(lrz2.toString());
		}else if(LuckyRacingZ3.KEY.equals(key)) {
			LuckyRacingZ3 lrz3 = new LuckyRacingZ3().refresh(prizeInfoService,index);
			sdata.setValue(lrz3.toJson());
			sdata.setValueString(lrz3.toString());
		}
		statisticsDataService.merge(sdata);
		logger.info("refresh end:key="+key);
		
	}

	@Override
	public void refreshAll(String batchcode, String lotno) {
		refresh(LuckyRacingDD.KEY,batchcode,lotno);
		refresh(LuckyRacingPlace.KEY,batchcode,lotno);
		refresh(LuckyRacingQ1.KEY,batchcode,lotno);
		refresh(LuckyRacingQ2.KEY,batchcode,lotno);
		refresh(LuckyRacingQ3.KEY,batchcode,lotno);
		refresh(LuckyRacingZ2.KEY,batchcode,lotno);
		refresh(LuckyRacingZ3.KEY,batchcode,lotno);
		
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
