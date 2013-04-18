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
		
		GdHappytenH1 gdhappytenh1 = GdHappytenH1.fromJsonToGdHappytenH1(data_gdhappytenh1.getValue());
		GdHappytenS1 gdhappytens1 = GdHappytenS1.fromJsonToGdHappytenS1(data_gdhappytens1.getValue());
		GdHappytenRX gdhappytenrx = GdHappytenRX.fromJsonToGdHappytenRX(data_gdhappytenrx.getValue());
		GdHappytenQ3 gdhappytenq3 = GdHappytenQ3.fromJsonToGdHappytenQ3(data_gdhappytenq3.getValue());
		
		gdhappytenh1.onPrize(prizeInfo);
		gdhappytens1.onPrize(prizeInfo);
		gdhappytenrx.onPrize(prizeInfo);
		gdhappytenq3.onPrize(prizeInfo);
		
		StatisticsData data_gdhappytenh1_1 = new StatisticsData(new StatisticsPK(GdHappytenH1.KEY,prizeInfo.getLotno(),prizeInfo.getBatchcode()));
		StatisticsData data_gdhappytens1_1 = new StatisticsData(new StatisticsPK(GdHappytenS1.KEY,prizeInfo.getLotno(),prizeInfo.getBatchcode()));
		StatisticsData data_gdhappytenrx_1 = new StatisticsData(new StatisticsPK(GdHappytenRX.KEY,prizeInfo.getLotno(),prizeInfo.getBatchcode()));
		StatisticsData data_gdhappytenq3_1 = new StatisticsData(new StatisticsPK(GdHappytenQ3.KEY,prizeInfo.getLotno(),prizeInfo.getBatchcode()));
		
		data_gdhappytenh1_1.setValue(gdhappytenh1.toJson());
		data_gdhappytens1_1.setValue(gdhappytens1.toJson());
		data_gdhappytenrx_1.setValue(gdhappytenrx.toJson());
		data_gdhappytenq3_1.setValue(gdhappytenq3.toJson());
		
		
		data_gdhappytenh1_1.setValueString(gdhappytenh1.toString());
		data_gdhappytens1_1.setValueString(gdhappytens1.toString());
		data_gdhappytenrx_1.setValueString(gdhappytenrx.toString());
		data_gdhappytenq3_1.setValueString(gdhappytenq3.toString());
		
		
		statisticsDataService.merge(data_gdhappytenh1_1);
		statisticsDataService.merge(data_gdhappytens1_1);
		statisticsDataService.merge(data_gdhappytenrx_1);
		statisticsDataService.merge(data_gdhappytenq3_1);
		
		logger.info("onPrize end :"+prizeInfo.getLotno()+ " " + prizeInfo.getBatchcode());
		
		try {
			logger.info("onPrize try to put to cache:"+prizeInfo.getLotno()+ " " + prizeInfo.getBatchcode());
			
			
			memcachedClient.set(GdHappytenH1.KEY+"_"+prizeInfo.getBatchcode(), 129600,data_gdhappytenh1_1);
			memcachedClient.set(GdHappytenS1.KEY+"_"+prizeInfo.getBatchcode(), 129600,data_gdhappytens1_1);
			memcachedClient.set(GdHappytenRX.KEY+"_"+prizeInfo.getBatchcode(), 129600,data_gdhappytenrx_1);
			memcachedClient.set(GdHappytenQ3.KEY+"_"+prizeInfo.getBatchcode(), 129600,data_gdhappytenq3_1);
			
			
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
		statisticsDataService.merge(sdata);
		logger.info("refresh end:key="+key);
		
	}

	@Override
	public void refreshAll(String batchcode,String lotno) {
		refresh(GdHappytenH1.KEY,batchcode,lotno);
		refresh(GdHappytenS1.KEY,batchcode,lotno);
		refresh(GdHappytenRX.KEY,batchcode,lotno);
		refresh(GdHappytenQ3.KEY,batchcode,lotno);
		
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
