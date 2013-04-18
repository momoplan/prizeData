package com.ruyicai.prizedata.missvalue.pailiesan;

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

@Service("T01002MV")
public class PaiLieSan implements LotTypeMissValue {

	private static Logger logger = LoggerFactory.getLogger(PaiLieSan.class);
	
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
	public void onPrize(PrizeInfo prizeInfo,String batchcode) {
		logger.info("onPrize start :"+prizeInfo.getLotno()+ " " + prizeInfo.getBatchcode());
		StatisticsData data_pailiesanzx = statisticsDataService.findLatestOrByBatchcode("T01002",PaiLieSanZX.KEY,batchcode);
		StatisticsData date_pailiesanz36 = statisticsDataService.findLatestOrByBatchcode("T01002",PaiLieSanZ36.KEY,batchcode);
		StatisticsData date_pailiesanz36hz = statisticsDataService.findLatestOrByBatchcode("T01002",PaiLieSanZ36HZ.KEY,batchcode);
		StatisticsData date_pailiesanzxhz = statisticsDataService.findLatestOrByBatchcode("T01002", PaiLieSanZXHZ.KEY, batchcode);
		
		PaiLieSanZX pailiesanzx = PaiLieSanZX.fromJsonToPaiLieSanZX(data_pailiesanzx.getValue());
		PaiLieSanZ36 pailiesanz36 = PaiLieSanZ36.fromJsonToPaiLieSanZ36(date_pailiesanz36.getValue());
		PaiLieSanZ36HZ pailiesanz36hz = PaiLieSanZ36HZ.fromJsonToPaiLieSanZ36HZ(date_pailiesanz36hz.getValue());
		PaiLieSanZXHZ pailiesanzxhz = PaiLieSanZXHZ.fromJsonToPaiLieSanZXHZ(date_pailiesanzxhz.getValue());
		
		
		pailiesanzx.onPrize(prizeInfo);
		pailiesanz36.onPrize(prizeInfo);
		pailiesanz36hz.onPrize(prizeInfo);
		pailiesanzxhz.onPrize(prizeInfo);
		
		StatisticsData data_pailiesanzx_1 = new StatisticsData(new StatisticsPK(PaiLieSanZX.KEY, prizeInfo.getLotno(), prizeInfo.getBatchcode()));
		StatisticsData date_pailiesanz36_1 = new StatisticsData(new StatisticsPK(PaiLieSanZ36.KEY, prizeInfo.getLotno(), prizeInfo.getBatchcode()));
		StatisticsData date_pailiesanz36hz_1 = new StatisticsData(new StatisticsPK(PaiLieSanZ36HZ.KEY, prizeInfo.getLotno(), prizeInfo.getBatchcode()));
		StatisticsData date_pailiesanzxhz_1 = new StatisticsData(new StatisticsPK(PaiLieSanZXHZ.KEY, prizeInfo.getLotno(), prizeInfo.getBatchcode()));
		
		data_pailiesanzx_1.setValue(pailiesanzx.toJson());
		date_pailiesanz36_1.setValue(pailiesanz36.toJson());
		date_pailiesanz36hz_1.setValue(pailiesanz36hz.toJson());
		date_pailiesanzxhz_1.setValue(pailiesanzxhz.toJson());
		
		
		data_pailiesanzx_1.setValueString(pailiesanzx.toString());
		date_pailiesanz36_1.setValueString(pailiesanz36.toString());
		date_pailiesanz36hz_1.setValueString(pailiesanz36hz.toString());
		date_pailiesanzxhz_1.setValueString(pailiesanzxhz.toString());
		
		
		statisticsDataService.merge(data_pailiesanzx_1);
		statisticsDataService.merge(date_pailiesanz36_1);
		statisticsDataService.merge(date_pailiesanz36hz_1);
		statisticsDataService.merge(date_pailiesanzxhz_1);
		
		logger.info("onPrize end :"+prizeInfo.getLotno()+ " " + prizeInfo.getBatchcode());
		try {
			logger.info("onPrize try to put to cache:"+prizeInfo.getLotno()+ " " + prizeInfo.getBatchcode());
			
			memcachedClient.set(PaiLieSanZX.KEY+"_"+prizeInfo.getBatchcode(), 864000, data_pailiesanzx_1);
			memcachedClient.set(PaiLieSanZ36.KEY+"_"+prizeInfo.getBatchcode(), 864000, date_pailiesanz36_1);
			memcachedClient.set(PaiLieSanZ36HZ.KEY+"_"+prizeInfo.getBatchcode(), 864000, date_pailiesanz36hz_1);
			memcachedClient.set(PaiLieSanZXHZ.KEY+"_"+prizeInfo.getBatchcode(), 864000, date_pailiesanzxhz_1);
			
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
		if(PaiLieSanZX.KEY.equals(key)) {
			PaiLieSanZX rx = new PaiLieSanZX().refresh(prizeInfoService,index);
			sdata.setValue(rx.toJson());
			sdata.setValueString(rx.toString());
		} else if(PaiLieSanZ36.KEY.equals(key)) {
			PaiLieSanZ36 rx = new PaiLieSanZ36().refresh(prizeInfoService,index);
			sdata.setValue(rx.toJson());
			sdata.setValueString(rx.toString());
		} else if(PaiLieSanZ36HZ.KEY.equals(key)) {
			PaiLieSanZ36HZ rx = new PaiLieSanZ36HZ().refresh(prizeInfoService,index);
			sdata.setValue(rx.toJson());
			sdata.setValueString(rx.toString());
		} else if(PaiLieSanZXHZ.KEY.equals(key)) {
			PaiLieSanZXHZ rx = new PaiLieSanZXHZ().refresh(prizeInfoService,index);
			sdata.setValue(rx.toJson());
			sdata.setValueString(rx.toString());
		}
		statisticsDataService.merge(sdata);
		logger.info("refresh end:key="+key);

	}

	@Override
	public void refreshAll(String batchcode,String lotno) {
		refresh(PaiLieSanZX.KEY,batchcode,lotno);
		refresh(PaiLieSanZ36.KEY,batchcode,lotno);
		refresh(PaiLieSanZ36HZ.KEY,batchcode,lotno);
		refresh(PaiLieSanZXHZ.KEY,batchcode,lotno);

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
