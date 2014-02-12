package com.ruyicai.prizedata.missvalue.cqhappyten;

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

@Service("F47109MV")
public class CqHappyten implements LotTypeMissValue {

	
	private static Logger logger = LoggerFactory.getLogger(CqHappyten.class);
	
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
		StatisticsData data_cqhappytenh1 = statisticsDataService.findLatestOrByBatchcode("F47109",CqHappytenH1.KEY,batchcode);
		StatisticsData data_cqhappytens1 = statisticsDataService.findLatestOrByBatchcode("F47109",CqHappytenS1.KEY,batchcode);
		StatisticsData data_cqhappytenrx = statisticsDataService.findLatestOrByBatchcode("F47109",CqHappytenRX.KEY,batchcode);
		StatisticsData data_cqhappytenq3 = statisticsDataService.findLatestOrByBatchcode("F47109",CqHappytenQ3.KEY,batchcode);
		StatisticsData data_cqhappytenr2zh = statisticsDataService.findLatestOrByBatchcode("F47109",CqHappytenR2ZH.KEY,batchcode);
		StatisticsData data_cqhappytenr3zh = statisticsDataService.findLatestOrByBatchcode("F47109",CqHappytenR3ZH.KEY,batchcode);
		StatisticsData data_cqhappytenr4zh = statisticsDataService.findLatestOrByBatchcode("F47109",CqHappytenR4ZH.KEY,batchcode);
		StatisticsData data_cqhappytenr5zh = statisticsDataService.findLatestOrByBatchcode("F47109",CqHappytenR5ZH.KEY,batchcode);
		
		CqHappytenH1 cqhappytenh1 = CqHappytenH1.fromJsonToCqHappytenH1(data_cqhappytenh1.getValue());
		CqHappytenS1 cqhappytens1 = CqHappytenS1.fromJsonToCqHappytenS1(data_cqhappytens1.getValue());
		CqHappytenRX cqhappytenrx = CqHappytenRX.fromJsonToCqHappytenRX(data_cqhappytenrx.getValue());
		CqHappytenQ3 cqhappytenq3 = CqHappytenQ3.fromJsonToCqHappytenQ3(data_cqhappytenq3.getValue());
		CqHappytenR2ZH cqhappytenr2zh = CqHappytenR2ZH.fromJsonToCqHappytenR2ZH(data_cqhappytenr2zh.getValue());
		CqHappytenR3ZH cqhappytenr3zh = CqHappytenR3ZH.fromJsonToCqHappytenR3ZH(data_cqhappytenr3zh.getValue());
		CqHappytenR4ZH cqhappytenr4zh = CqHappytenR4ZH.fromJsonToCqHappytenR4ZH(data_cqhappytenr4zh.getValue());
		CqHappytenR5ZH cqhappytenr5zh = CqHappytenR5ZH.fromJsonToCqHappytenR5ZH(data_cqhappytenr5zh.getValue());
		
		cqhappytenh1.onPrize(prizeInfo);
		cqhappytens1.onPrize(prizeInfo);
		cqhappytenrx.onPrize(prizeInfo);
		cqhappytenq3.onPrize(prizeInfo);
		cqhappytenr2zh.onPrize(prizeInfo);
		cqhappytenr3zh.onPrize(prizeInfo);
		cqhappytenr4zh.onPrize(prizeInfo);
		cqhappytenr5zh.onPrize(prizeInfo);
		
		
		StatisticsData data_cqhappytenh1_1 = new StatisticsData(new StatisticsPK(CqHappytenH1.KEY,prizeInfo.getLotno(),prizeInfo.getBatchcode()));
		StatisticsData data_cqhappytens1_1 = new StatisticsData(new StatisticsPK(CqHappytenS1.KEY,prizeInfo.getLotno(),prizeInfo.getBatchcode()));
		StatisticsData data_cqhappytenrx_1 = new StatisticsData(new StatisticsPK(CqHappytenRX.KEY,prizeInfo.getLotno(),prizeInfo.getBatchcode()));
		StatisticsData data_cqhappytenq3_1 = new StatisticsData(new StatisticsPK(CqHappytenQ3.KEY,prizeInfo.getLotno(),prizeInfo.getBatchcode()));
		StatisticsData data_cqhappytenr2zh_1 = new StatisticsData(new StatisticsPK(CqHappytenR2ZH.KEY,prizeInfo.getLotno(),prizeInfo.getBatchcode()));
		StatisticsData data_cqhappytenr3zh_1 = new StatisticsData(new StatisticsPK(CqHappytenR3ZH.KEY,prizeInfo.getLotno(),prizeInfo.getBatchcode()));
		StatisticsData data_cqhappytenr4zh_1 = new StatisticsData(new StatisticsPK(CqHappytenR4ZH.KEY,prizeInfo.getLotno(),prizeInfo.getBatchcode()));
		StatisticsData data_cqhappytenr5zh_1 = new StatisticsData(new StatisticsPK(CqHappytenR5ZH.KEY,prizeInfo.getLotno(),prizeInfo.getBatchcode()));
		
		
		data_cqhappytenh1_1.setValue(cqhappytenh1.toJson());
		data_cqhappytens1_1.setValue(cqhappytens1.toJson());
		data_cqhappytenrx_1.setValue(cqhappytenrx.toJson());
		data_cqhappytenq3_1.setValue(cqhappytenq3.toJson());
		data_cqhappytenr2zh_1.setValue(cqhappytenr2zh.toJson());
		data_cqhappytenr3zh_1.setValue(cqhappytenr3zh.toJson());
		data_cqhappytenr4zh_1.setValue(cqhappytenr4zh.toJson());
		data_cqhappytenr5zh_1.setValue(cqhappytenr5zh.toJson());
		
		
		data_cqhappytenh1_1.setValueString(cqhappytenh1.toString());
		data_cqhappytens1_1.setValueString(cqhappytens1.toString());
		data_cqhappytenrx_1.setValueString(cqhappytenrx.toString());
		data_cqhappytenq3_1.setValueString(cqhappytenq3.toString());
		data_cqhappytenr2zh_1.setValueString(cqhappytenr2zh.toString());
		data_cqhappytenr3zh_1.setValueString(cqhappytenr3zh.toString());
		data_cqhappytenr4zh_1.setValueString(cqhappytenr4zh.toString());
		data_cqhappytenr5zh_1.setValueString(cqhappytenr5zh.toString());
		
		
		statisticsDataService.merge(data_cqhappytenh1_1);
		statisticsDataService.merge(data_cqhappytens1_1);
		statisticsDataService.merge(data_cqhappytenrx_1);
		statisticsDataService.merge(data_cqhappytenq3_1);
		statisticsDataService.merge(data_cqhappytenr2zh_1);
		statisticsDataService.merge(data_cqhappytenr3zh_1);
		statisticsDataService.merge(data_cqhappytenr4zh_1);
		statisticsDataService.merge(data_cqhappytenr5zh_1);
		
		
		logger.info("onPrize end :"+prizeInfo.getLotno()+ " " + prizeInfo.getBatchcode());
		
		try {
			logger.info("onPrize try to put to cache:"+prizeInfo.getLotno()+ " " + prizeInfo.getBatchcode());
			
			
			memcachedClient.set(CqHappytenH1.KEY+"_"+prizeInfo.getBatchcode(), 129600,data_cqhappytenh1_1);
			memcachedClient.set(CqHappytenS1.KEY+"_"+prizeInfo.getBatchcode(), 129600,data_cqhappytens1_1);
			memcachedClient.set(CqHappytenRX.KEY+"_"+prizeInfo.getBatchcode(), 129600,data_cqhappytenrx_1);
			memcachedClient.set(CqHappytenQ3.KEY+"_"+prizeInfo.getBatchcode(), 129600,data_cqhappytenq3_1);
			memcachedClient.set(CqHappytenR2ZH.KEY+"_"+prizeInfo.getBatchcode(), 129600,data_cqhappytenr2zh_1);
			memcachedClient.set(CqHappytenR3ZH.KEY+"_"+prizeInfo.getBatchcode(), 129600,data_cqhappytenr3zh_1);
			memcachedClient.set(CqHappytenR4ZH.KEY+"_"+prizeInfo.getBatchcode(), 129600,data_cqhappytenr4zh_1);
			memcachedClient.set(CqHappytenR5ZH.KEY+"_"+prizeInfo.getBatchcode(), 129600,data_cqhappytenr5zh_1);
			

			
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
		if(CqHappytenH1.KEY.equals(key)) {
			CqHappytenH1 h1 = new CqHappytenH1().refresh(prizeInfoService,index);
			sdata.setValue(h1.toJson());
			sdata.setValueString(h1.toString());
		}else if(CqHappytenS1.KEY.equals(key)) {
			CqHappytenS1 s1 = new CqHappytenS1().refresh(prizeInfoService,index);
			sdata.setValue(s1.toJson());
			sdata.setValueString(s1.toString());
		}else if(CqHappytenRX.KEY.equals(key)) {
			CqHappytenRX rx = new CqHappytenRX().refresh(prizeInfoService,index);
			sdata.setValue(rx.toJson());
			sdata.setValueString(rx.toString());
		}else if(CqHappytenQ3.KEY.equals(key)) {
			CqHappytenQ3 q3 = new CqHappytenQ3().refresh(prizeInfoService,index);
			sdata.setValue(q3.toJson());
			sdata.setValueString(q3.toString());
		}
		else if(CqHappytenR2ZH.KEY.equals(key)) {
			CqHappytenR2ZH r2zh = new CqHappytenR2ZH().refresh(prizeInfoService,index);
			sdata.setValue(r2zh.toJson());
			sdata.setValueString(r2zh.toString());
		}else if(CqHappytenR3ZH.KEY.equals(key)) {
			CqHappytenR3ZH r3zh = new CqHappytenR3ZH().refresh(prizeInfoService,index);
			sdata.setValue(r3zh.toJson());
			sdata.setValueString(r3zh.toString());
		}else if(CqHappytenR4ZH.KEY.equals(key)) {
			CqHappytenR4ZH r4zh = new CqHappytenR4ZH().refresh(prizeInfoService,index);
			sdata.setValue(r4zh.toJson());
			sdata.setValueString(r4zh.toString());
		}else if(CqHappytenR5ZH.KEY.equals(key)) {
			CqHappytenR5ZH r5zh = new CqHappytenR5ZH().refresh(prizeInfoService,index);
			sdata.setValue(r5zh.toJson());
			sdata.setValueString(r5zh.toString());
		}
		statisticsDataService.merge(sdata);
		logger.info("refresh end:key="+key);
		
	}

	@Override
	public void refreshAll(String batchcode,String lotno) {
		refresh(CqHappytenH1.KEY,batchcode,lotno);
		refresh(CqHappytenS1.KEY,batchcode,lotno);
		refresh(CqHappytenRX.KEY,batchcode,lotno);
		refresh(CqHappytenQ3.KEY,batchcode,lotno);
		refresh(CqHappytenR2ZH.KEY,batchcode,lotno);
		refresh(CqHappytenR3ZH.KEY,batchcode,lotno);
		refresh(CqHappytenR4ZH.KEY,batchcode,lotno);
		refresh(CqHappytenR5ZH.KEY,batchcode,lotno);
		
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
