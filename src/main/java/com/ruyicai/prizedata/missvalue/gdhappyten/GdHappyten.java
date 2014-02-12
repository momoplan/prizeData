package com.ruyicai.prizedata.missvalue.gdhappyten;

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

@Service("T01015MV")
public class GdHappyten implements LotTypeMissValue {

	
	private static Logger logger = LoggerFactory.getLogger(GdHappyten.class);
	
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
		StatisticsData data_gdhappytenh1 = statisticsDataService.findLatestOrByBatchcode("T01015",GdHappytenH1.KEY,batchcode);
		StatisticsData data_gdhappytens1 = statisticsDataService.findLatestOrByBatchcode("T01015",GdHappytenS1.KEY,batchcode);
		StatisticsData data_gdhappytenrx = statisticsDataService.findLatestOrByBatchcode("T01015",GdHappytenRX.KEY,batchcode);
		StatisticsData data_gdhappytenq3 = statisticsDataService.findLatestOrByBatchcode("T01015",GdHappytenQ3.KEY,batchcode);
		StatisticsData data_gdhappytenr2zh = statisticsDataService.findLatestOrByBatchcode("T01015",GdHappytenR2ZH.KEY,batchcode);
		StatisticsData data_gdhappytenr3zh = statisticsDataService.findLatestOrByBatchcode("T01015",GdHappytenR3ZH.KEY,batchcode);
		StatisticsData data_gdhappytenr4zh = statisticsDataService.findLatestOrByBatchcode("T01015",GdHappytenR4ZH.KEY,batchcode);
		StatisticsData data_gdhappytenr5zh = statisticsDataService.findLatestOrByBatchcode("T01015",GdHappytenR5ZH.KEY,batchcode);
		
		
		
		GdHappytenH1 gdhappytenh1 = GdHappytenH1.fromJsonToGdHappytenH1(data_gdhappytenh1.getValue());
		GdHappytenS1 gdhappytens1 = GdHappytenS1.fromJsonToGdHappytenS1(data_gdhappytens1.getValue());
		GdHappytenRX gdhappytenrx = GdHappytenRX.fromJsonToGdHappytenRX(data_gdhappytenrx.getValue());
		GdHappytenQ3 gdhappytenq3 = GdHappytenQ3.fromJsonToGdHappytenQ3(data_gdhappytenq3.getValue());
		GdHappytenR2ZH gdhappytenr2zh = GdHappytenR2ZH.fromJsonToGdHappytenR2ZH(data_gdhappytenr2zh.getValue());
		GdHappytenR3ZH gdhappytenr3zh = GdHappytenR3ZH.fromJsonToGdHappytenR3ZH(data_gdhappytenr3zh.getValue());
		GdHappytenR4ZH gdhappytenr4zh = GdHappytenR4ZH.fromJsonToGdHappytenR4ZH(data_gdhappytenr4zh.getValue());
		GdHappytenR5ZH gdhappytenr5zh = GdHappytenR5ZH.fromJsonToGdHappytenR5ZH(data_gdhappytenr5zh.getValue());
		
		
		
		gdhappytenh1.onPrize(prizeInfo);
		gdhappytens1.onPrize(prizeInfo);
		gdhappytenrx.onPrize(prizeInfo);
		gdhappytenq3.onPrize(prizeInfo);
		gdhappytenr2zh.onPrize(prizeInfo);
		gdhappytenr3zh.onPrize(prizeInfo);
		gdhappytenr4zh.onPrize(prizeInfo);
		gdhappytenr5zh.onPrize(prizeInfo);
		
		
		StatisticsData data_gdhappytenh1_1 = new StatisticsData(new StatisticsPK(GdHappytenH1.KEY,prizeInfo.getLotno(),prizeInfo.getBatchcode()));
		StatisticsData data_gdhappytens1_1 = new StatisticsData(new StatisticsPK(GdHappytenS1.KEY,prizeInfo.getLotno(),prizeInfo.getBatchcode()));
		StatisticsData data_gdhappytenrx_1 = new StatisticsData(new StatisticsPK(GdHappytenRX.KEY,prizeInfo.getLotno(),prizeInfo.getBatchcode()));
		StatisticsData data_gdhappytenq3_1 = new StatisticsData(new StatisticsPK(GdHappytenQ3.KEY,prizeInfo.getLotno(),prizeInfo.getBatchcode()));
		StatisticsData data_gdhappytenr2zh_1 = new StatisticsData(new StatisticsPK(GdHappytenR2ZH.KEY,prizeInfo.getLotno(),prizeInfo.getBatchcode()));
		StatisticsData data_gdhappytenr3zh_1 = new StatisticsData(new StatisticsPK(GdHappytenR3ZH.KEY,prizeInfo.getLotno(),prizeInfo.getBatchcode()));
		StatisticsData data_gdhappytenr4zh_1 = new StatisticsData(new StatisticsPK(GdHappytenR4ZH.KEY,prizeInfo.getLotno(),prizeInfo.getBatchcode()));
		StatisticsData data_gdhappytenr5zh_1 = new StatisticsData(new StatisticsPK(GdHappytenR5ZH.KEY,prizeInfo.getLotno(),prizeInfo.getBatchcode()));
		
		
		data_gdhappytenh1_1.setValue(gdhappytenh1.toJson());
		data_gdhappytens1_1.setValue(gdhappytens1.toJson());
		data_gdhappytenrx_1.setValue(gdhappytenrx.toJson());
		data_gdhappytenq3_1.setValue(gdhappytenq3.toJson());
		data_gdhappytenr2zh_1.setValue(gdhappytenr2zh.toJson());
		data_gdhappytenr3zh_1.setValue(gdhappytenr3zh.toJson());
		data_gdhappytenr4zh_1.setValue(gdhappytenr4zh.toJson());
		data_gdhappytenr5zh_1.setValue(gdhappytenr5zh.toJson());
		
		data_gdhappytenh1_1.setValueString(gdhappytenh1.toString());
		data_gdhappytens1_1.setValueString(gdhappytens1.toString());
		data_gdhappytenrx_1.setValueString(gdhappytenrx.toString());
		data_gdhappytenq3_1.setValueString(gdhappytenq3.toString());
		data_gdhappytenr2zh_1.setValueString(gdhappytenr2zh.toString());
		data_gdhappytenr3zh_1.setValueString(gdhappytenr3zh.toString());
		data_gdhappytenr4zh_1.setValueString(gdhappytenr4zh.toString());
		data_gdhappytenr5zh_1.setValueString(gdhappytenr5zh.toString());
		
		statisticsDataService.merge(data_gdhappytenh1_1);
		statisticsDataService.merge(data_gdhappytens1_1);
		statisticsDataService.merge(data_gdhappytenrx_1);
		statisticsDataService.merge(data_gdhappytenq3_1);
		statisticsDataService.merge(data_gdhappytenr2zh_1);
		statisticsDataService.merge(data_gdhappytenr3zh_1);
		statisticsDataService.merge(data_gdhappytenr4zh_1);
		statisticsDataService.merge(data_gdhappytenr5zh_1);
		
		logger.info("onPrize end :"+prizeInfo.getLotno()+ " " + prizeInfo.getBatchcode());
		
		try {
			logger.info("onPrize try to put to cache:"+prizeInfo.getLotno()+ " " + prizeInfo.getBatchcode());
			
			
			memcachedClient.set(GdHappytenH1.KEY+"_"+prizeInfo.getBatchcode(), 129600,data_gdhappytenh1_1);
			memcachedClient.set(GdHappytenS1.KEY+"_"+prizeInfo.getBatchcode(), 129600,data_gdhappytens1_1);
			memcachedClient.set(GdHappytenRX.KEY+"_"+prizeInfo.getBatchcode(), 129600,data_gdhappytenrx_1);
			memcachedClient.set(GdHappytenQ3.KEY+"_"+prizeInfo.getBatchcode(), 129600,data_gdhappytenq3_1);
			memcachedClient.set(GdHappytenR2ZH.KEY+"_"+prizeInfo.getBatchcode(), 129600,data_gdhappytenr2zh_1);
			memcachedClient.set(GdHappytenR3ZH.KEY+"_"+prizeInfo.getBatchcode(), 129600,data_gdhappytenr3zh_1);
			memcachedClient.set(GdHappytenR4ZH.KEY+"_"+prizeInfo.getBatchcode(), 129600,data_gdhappytenr4zh_1);
			memcachedClient.set(GdHappytenR5ZH.KEY+"_"+prizeInfo.getBatchcode(), 129600,data_gdhappytenr5zh_1);
			
			
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
		if(GdHappytenH1.KEY.equals(key)) {
			GdHappytenH1 h1 = new GdHappytenH1().refresh(prizeInfoService,index);
			sdata.setValue(h1.toJson());
			sdata.setValueString(h1.toString());
		}else if(GdHappytenS1.KEY.equals(key)) {
			GdHappytenS1 s1 = new GdHappytenS1().refresh(prizeInfoService,index);
			sdata.setValue(s1.toJson());
			sdata.setValueString(s1.toString());
		}else if(GdHappytenRX.KEY.equals(key)) {
			GdHappytenRX rx = new GdHappytenRX().refresh(prizeInfoService,index);
			sdata.setValue(rx.toJson());
			sdata.setValueString(rx.toString());
		}else if(GdHappytenQ3.KEY.equals(key)) {
			GdHappytenQ3 q3 = new GdHappytenQ3().refresh(prizeInfoService,index);
			sdata.setValue(q3.toJson());
			sdata.setValueString(q3.toString());
		}
		else if(GdHappytenR2ZH.KEY.equals(key)) {
			GdHappytenR2ZH r2zh = new GdHappytenR2ZH().refresh(prizeInfoService,index);
			sdata.setValue(r2zh.toJson());
			sdata.setValueString(r2zh.toString());
		}else if(GdHappytenR3ZH.KEY.equals(key)) {
			GdHappytenR3ZH r3zh = new GdHappytenR3ZH().refresh(prizeInfoService,index);
			sdata.setValue(r3zh.toJson());
			sdata.setValueString(r3zh.toString());
		}else if(GdHappytenR4ZH.KEY.equals(key)) {
			GdHappytenR4ZH r4zh = new GdHappytenR4ZH().refresh(prizeInfoService,index);
			sdata.setValue(r4zh.toJson());
			sdata.setValueString(r4zh.toString());
		}else if(GdHappytenR5ZH.KEY.equals(key)) {
			GdHappytenR5ZH r5zh = new GdHappytenR5ZH().refresh(prizeInfoService,index);
			sdata.setValue(r5zh.toJson());
			sdata.setValueString(r5zh.toString());
		}
		statisticsDataService.merge(sdata);
		logger.info("refresh end:key="+key);
		
	}

	@Override
	public void refreshAll(String batchcode,String lotno) {
		refresh(GdHappytenH1.KEY,batchcode,lotno);
		refresh(GdHappytenS1.KEY,batchcode,lotno);
		refresh(GdHappytenRX.KEY,batchcode,lotno);
		refresh(GdHappytenQ3.KEY,batchcode,lotno);
		refresh(GdHappytenR2ZH.KEY,batchcode,lotno);
		refresh(GdHappytenR3ZH.KEY,batchcode,lotno);
		refresh(GdHappytenR4ZH.KEY,batchcode,lotno);
		refresh(GdHappytenR5ZH.KEY,batchcode,lotno);
		
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
