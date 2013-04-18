package com.ruyicai.prizedata.missvalue.elevenduojin;

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

@Service("T01012MV")
public class ElevenDuoJin implements LotTypeMissValue {

	
	private static Logger logger = LoggerFactory.getLogger(ElevenDuoJin.class);
	
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
		StatisticsData data_elevenduojinrx = statisticsDataService.findLatestOrByBatchcode("T01012",ElevenDuoJinRX.KEY,batchcode);
		StatisticsData data_elevenduojinq1 = statisticsDataService.findLatestOrByBatchcode("T01012",ElevenDuoJinQ1.KEY,batchcode);
		StatisticsData data_elevenduojinq2 = statisticsDataService.findLatestOrByBatchcode("T01012",ElevenDuoJinQ2.KEY,batchcode);
		StatisticsData data_elevenduojinq3 = statisticsDataService.findLatestOrByBatchcode("T01012",ElevenDuoJinQ3.KEY,batchcode);
		StatisticsData data_elevenduojinq2z = statisticsDataService.findLatestOrByBatchcode("T01012",ElevenDuoJinQ2Z.KEY,batchcode);
		StatisticsData data_elevenduojinq3z = statisticsDataService.findLatestOrByBatchcode("T01012",ElevenDuoJinQ3Z.KEY,batchcode);
		StatisticsData data_elevenduojinq3zh = statisticsDataService.findLatestOrByBatchcode("T01012",ElevenDuoJinQ3ZH.KEY,batchcode);
		StatisticsData data_elevenduojinqr5zh = statisticsDataService.findLatestOrByBatchcode("T01012",ElevenDuoJinR5ZH.KEY,batchcode);
		StatisticsData data_elevenduojinqr7zh = statisticsDataService.findLatestOrByBatchcode("T01012",ElevenDuoJinR7ZH.KEY,batchcode);
		StatisticsData data_elevenduojinqr8zh = statisticsDataService.findLatestOrByBatchcode("T01012",ElevenDuoJinR8ZH.KEY,batchcode);
		
		ElevenDuoJinRX elevenduojinrx = ElevenDuoJinRX.fromJsonToElevenDuoJinRX(data_elevenduojinrx.getValue());
		ElevenDuoJinQ1 elevenduojinq1 = ElevenDuoJinQ1.fromJsonToElevenDuoJinQ1(data_elevenduojinq1.getValue());
		ElevenDuoJinQ2 elevenduojinq2 = ElevenDuoJinQ2.fromJsonToElevenDuoJinQ2(data_elevenduojinq2.getValue());
		ElevenDuoJinQ3 elevenduojinq3 = ElevenDuoJinQ3.fromJsonToElevenDuoJinQ3(data_elevenduojinq3.getValue());
		ElevenDuoJinQ2Z elevenduojinq2z = ElevenDuoJinQ2Z.fromJsonToElevenDuoJinQ2Z(data_elevenduojinq2z.getValue());
		ElevenDuoJinQ3Z elevenduojinq3z = ElevenDuoJinQ3Z.fromJsonToElevenDuoJinQ3Z(data_elevenduojinq3z.getValue());
		ElevenDuoJinQ3ZH elevenduojinq3zh = ElevenDuoJinQ3ZH.fromJsonToElevenDuoJinQ3ZH(data_elevenduojinq3zh.getValue());
		ElevenDuoJinR5ZH elevenduojinqr5zh = ElevenDuoJinR5ZH.fromJsonToElevenDuoJinR5ZH(data_elevenduojinqr5zh.getValue());
		ElevenDuoJinR7ZH elevenduojinqr7zh = ElevenDuoJinR7ZH.fromJsonToElevenDuoJinR7ZH(data_elevenduojinqr7zh.getValue());
		ElevenDuoJinR8ZH elevenduojinqr8zh = ElevenDuoJinR8ZH.fromJsonToElevenDuoJinR8ZH(data_elevenduojinqr8zh.getValue());
		
		elevenduojinrx.onPrize(prizeInfo);
		elevenduojinq1.onPrize(prizeInfo);
		elevenduojinq2.onPrize(prizeInfo);
		elevenduojinq3.onPrize(prizeInfo);
		elevenduojinq2z.onPrize(prizeInfo);
		elevenduojinq3z.onPrize(prizeInfo);
		elevenduojinq3zh.onPrize(prizeInfo);
		elevenduojinqr5zh.onPrize(prizeInfo);
		elevenduojinqr7zh.onPrize(prizeInfo);
		elevenduojinqr8zh.onPrize(prizeInfo);
		
		StatisticsData data_elevenduojinrx_1 = new StatisticsData(new StatisticsPK(ElevenDuoJinRX.KEY,prizeInfo.getLotno(),prizeInfo.getBatchcode()));
		StatisticsData data_elevenduojinq1_1 = new StatisticsData(new StatisticsPK(ElevenDuoJinQ1.KEY,prizeInfo.getLotno(),prizeInfo.getBatchcode()));
		StatisticsData data_elevenduojinq2_1 = new StatisticsData(new StatisticsPK(ElevenDuoJinQ2.KEY,prizeInfo.getLotno(),prizeInfo.getBatchcode()));
		StatisticsData data_elevenduojinq3_1 = new StatisticsData(new StatisticsPK(ElevenDuoJinQ3.KEY,prizeInfo.getLotno(),prizeInfo.getBatchcode()));
		StatisticsData data_elevenduojinq2z_1 = new StatisticsData(new StatisticsPK(ElevenDuoJinQ2Z.KEY,prizeInfo.getLotno(),prizeInfo.getBatchcode()));
		StatisticsData data_elevenduojinq3z_1 = new StatisticsData(new StatisticsPK(ElevenDuoJinQ3Z.KEY,prizeInfo.getLotno(),prizeInfo.getBatchcode()));
		StatisticsData data_elevenduojinq3zh_1 = new StatisticsData(new StatisticsPK(ElevenDuoJinQ3ZH.KEY,prizeInfo.getLotno(),prizeInfo.getBatchcode()));
		StatisticsData data_elevenduojinqr5zh_1 = new StatisticsData(new StatisticsPK(ElevenDuoJinR5ZH.KEY,prizeInfo.getLotno(),prizeInfo.getBatchcode()));
		StatisticsData data_elevenduojinqr7zh_1 = new StatisticsData(new StatisticsPK(ElevenDuoJinR7ZH.KEY,prizeInfo.getLotno(),prizeInfo.getBatchcode()));
		StatisticsData data_elevenduojinqr8zh_1 = new StatisticsData(new StatisticsPK(ElevenDuoJinR8ZH.KEY,prizeInfo.getLotno(),prizeInfo.getBatchcode()));
		
		data_elevenduojinrx_1.setValue(elevenduojinrx.toJson());
		data_elevenduojinq1_1.setValue(elevenduojinq1.toJson());
		data_elevenduojinq2_1.setValue(elevenduojinq2.toJson());
		data_elevenduojinq3_1.setValue(elevenduojinq3.toJson());
		data_elevenduojinq2z_1.setValue(elevenduojinq2z.toJson());
		data_elevenduojinq3z_1.setValue(elevenduojinq3z.toJson());
		data_elevenduojinq3zh_1.setValue(elevenduojinq3zh.toJson());
		data_elevenduojinqr5zh_1.setValue(elevenduojinqr5zh.toJson());
		data_elevenduojinqr7zh_1.setValue(elevenduojinqr7zh.toJson());
		data_elevenduojinqr8zh_1.setValue(elevenduojinqr8zh.toJson());
		
		
		data_elevenduojinrx_1.setValueString(elevenduojinrx.toString());
		data_elevenduojinq1_1.setValueString(elevenduojinq1.toString());
		data_elevenduojinq2_1.setValueString(elevenduojinq2.toString());
		data_elevenduojinq3_1.setValueString(elevenduojinq3.toString());
		data_elevenduojinq2z_1.setValueString(elevenduojinq2z.toString());
		data_elevenduojinq3z_1.setValueString(elevenduojinq3z.toString());
		data_elevenduojinq3zh_1.setValueString(elevenduojinq3zh.toString());
		data_elevenduojinqr5zh_1.setValueString(elevenduojinqr5zh.toString());
		data_elevenduojinqr7zh_1.setValueString(elevenduojinqr7zh.toString());
		data_elevenduojinqr8zh_1.setValueString(elevenduojinqr8zh.toString());
		
		
		statisticsDataService.merge(data_elevenduojinrx_1);
		statisticsDataService.merge(data_elevenduojinq1_1);
		statisticsDataService.merge(data_elevenduojinq2_1);
		statisticsDataService.merge(data_elevenduojinq3_1);
		statisticsDataService.merge(data_elevenduojinq2z_1);
		statisticsDataService.merge(data_elevenduojinq3z_1);
		statisticsDataService.merge(data_elevenduojinq3zh_1);
		statisticsDataService.merge(data_elevenduojinqr5zh_1);
		statisticsDataService.merge(data_elevenduojinqr7zh_1);
		statisticsDataService.merge(data_elevenduojinqr8zh_1);
		
		logger.info("onPrize end :"+prizeInfo.getLotno()+ " " + prizeInfo.getBatchcode());
		
		try {
			logger.info("onPrize try to put to cache:"+prizeInfo.getLotno()+ " " + prizeInfo.getBatchcode());
			
			memcachedClient.set(ElevenDuoJinRX.KEY+"_"+prizeInfo.getBatchcode(), 129600, data_elevenduojinrx_1);
			memcachedClient.set(ElevenDuoJinQ1.KEY+"_"+prizeInfo.getBatchcode(), 129600,data_elevenduojinq1_1);
			memcachedClient.set(ElevenDuoJinQ2.KEY+"_"+prizeInfo.getBatchcode(), 129600,data_elevenduojinq2_1);
			memcachedClient.set(ElevenDuoJinQ3.KEY+"_"+prizeInfo.getBatchcode(), 129600,data_elevenduojinq3_1);
			memcachedClient.set(ElevenDuoJinQ2Z.KEY+"_"+prizeInfo.getBatchcode(), 129600,data_elevenduojinq2z_1);
			memcachedClient.set(ElevenDuoJinQ3Z.KEY+"_"+prizeInfo.getBatchcode(), 129600,data_elevenduojinq3z_1);
			memcachedClient.set(ElevenDuoJinQ3ZH.KEY+"_"+prizeInfo.getBatchcode(), 129600,data_elevenduojinq3zh_1);
			memcachedClient.set(ElevenDuoJinR5ZH.KEY+"_"+prizeInfo.getBatchcode(), 129600,data_elevenduojinqr5zh_1);
			memcachedClient.set(ElevenDuoJinR7ZH.KEY+"_"+prizeInfo.getBatchcode(), 129600,data_elevenduojinqr7zh_1);
			memcachedClient.set(ElevenDuoJinR8ZH.KEY+"_"+prizeInfo.getBatchcode(), 129600,data_elevenduojinqr8zh_1);
			
			
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
		if(ElevenDuoJinRX.KEY.equals(key)) {
			ElevenDuoJinRX rx = new ElevenDuoJinRX().refresh(prizeInfoService,index);
			sdata.setValue(rx.toJson());
			sdata.setValueString(rx.toString());
		}else if(ElevenDuoJinQ1.KEY.equals(key)) {
			ElevenDuoJinQ1 q1 = new ElevenDuoJinQ1().refresh(prizeInfoService,index);
			sdata.setValue(q1.toJson());
			sdata.setValueString(q1.toString());
		}else if(ElevenDuoJinQ2.KEY.equals(key)) {
			ElevenDuoJinQ2 q2 = new ElevenDuoJinQ2().refresh(prizeInfoService,index);
			sdata.setValue(q2.toJson());
			sdata.setValueString(q2.toString());
		}else if(ElevenDuoJinQ3.KEY.equals(key)) {
			ElevenDuoJinQ3 q3 = new ElevenDuoJinQ3().refresh(prizeInfoService,index);
			sdata.setValue(q3.toJson());
			sdata.setValueString(q3.toString());
		}else if(ElevenDuoJinQ2Z.KEY.equals(key)) {
			ElevenDuoJinQ2Z q2z = new ElevenDuoJinQ2Z().refresh(prizeInfoService,index);
			sdata.setValue(q2z.toJson());
			sdata.setValueString(q2z.toString());
		}else if(ElevenDuoJinQ3Z.KEY.equals(key)) {
			ElevenDuoJinQ3Z q3z = new ElevenDuoJinQ3Z().refresh(prizeInfoService,index);
			sdata.setValue(q3z.toJson());
			sdata.setValueString(q3z.toString());
		}else if(ElevenDuoJinQ3ZH.KEY.equals(key)) {
			ElevenDuoJinQ3ZH q3zh = new ElevenDuoJinQ3ZH().refresh(prizeInfoService,index);
			sdata.setValue(q3zh.toJson());
			sdata.setValueString(q3zh.toString());
		}else if(ElevenDuoJinR5ZH.KEY.equals(key)) {
			ElevenDuoJinR5ZH r5zh = new ElevenDuoJinR5ZH().refresh(prizeInfoService,index);
			sdata.setValue(r5zh.toJson());
			sdata.setValueString(r5zh.toString());
		}else if(ElevenDuoJinR7ZH.KEY.equals(key)) {
			ElevenDuoJinR7ZH r7zh = new ElevenDuoJinR7ZH().refresh(prizeInfoService,index);
			sdata.setValue(r7zh.toJson());
			sdata.setValueString(r7zh.toString());
		}else if(ElevenDuoJinR8ZH.KEY.equals(key)) {
			ElevenDuoJinR8ZH r8zh = new ElevenDuoJinR8ZH().refresh(prizeInfoService,index);
			sdata.setValue(r8zh.toJson());
			sdata.setValueString(r8zh.toString());
		}
		statisticsDataService.merge(sdata);
		logger.info("refresh end:key="+key);
		
	}

	@Override
	public void refreshAll(String batchcode,String lotno) {
		refresh(ElevenDuoJinRX.KEY,batchcode,lotno);
		refresh(ElevenDuoJinQ1.KEY,batchcode,lotno);
		refresh(ElevenDuoJinQ2.KEY,batchcode,lotno);
		refresh(ElevenDuoJinQ3.KEY,batchcode,lotno);
		refresh(ElevenDuoJinQ2Z.KEY,batchcode,lotno);
		refresh(ElevenDuoJinQ3Z.KEY,batchcode,lotno);
		refresh(ElevenDuoJinQ3ZH.KEY,batchcode,lotno);
		refresh(ElevenDuoJinR5ZH.KEY,batchcode,lotno);
		refresh(ElevenDuoJinR7ZH.KEY,batchcode,lotno);
		refresh(ElevenDuoJinR8ZH.KEY,batchcode,lotno);
		
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
