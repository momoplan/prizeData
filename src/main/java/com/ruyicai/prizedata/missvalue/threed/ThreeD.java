package com.ruyicai.prizedata.missvalue.threed;

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

@Service("F47103MV")
public class ThreeD implements LotTypeMissValue {

	private static Logger logger = LoggerFactory.getLogger(ThreeD.class);
	
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
		StatisticsData data_pailiesanzx = statisticsDataService.findLatestOrByBatchcode("F47103",ThreeDZX.KEY,batchcode);
		StatisticsData date_pailiesanz36 = statisticsDataService.findLatestOrByBatchcode("F47103",ThreeDZ36.KEY,batchcode);
		StatisticsData date_pailiesanz36hz = statisticsDataService.findLatestOrByBatchcode("F47103",ThreeDZ36HZ.KEY,batchcode);
		StatisticsData date_pailiesanzxhz = statisticsDataService.findLatestOrByBatchcode("F47103", ThreeDZXHZ.KEY, batchcode);
		
		ThreeDZX threedzx = ThreeDZX.fromJsonToThreeDZX(data_pailiesanzx.getValue());
		ThreeDZ36 threedz36 = ThreeDZ36.fromJsonToThreeDZ36(date_pailiesanz36.getValue());
		ThreeDZ36HZ threedz36hz = ThreeDZ36HZ.fromJsonToThreeDZ36HZ(date_pailiesanz36hz.getValue());
		ThreeDZXHZ threedzxhz = ThreeDZXHZ.fromJsonToThreeDZXHZ(date_pailiesanzxhz.getValue());
		
		
		threedzx.onPrize(prizeInfo);
		threedz36.onPrize(prizeInfo);
		threedz36hz.onPrize(prizeInfo);
		threedzxhz.onPrize(prizeInfo);
		
		StatisticsData data_threedzx_1 = new StatisticsData(new StatisticsPK(ThreeDZX.KEY, prizeInfo.getLotno(), prizeInfo.getBatchcode()));
		StatisticsData date_threedz36_1 = new StatisticsData(new StatisticsPK(ThreeDZ36.KEY, prizeInfo.getLotno(), prizeInfo.getBatchcode()));
		StatisticsData date_threedz36hz_1 = new StatisticsData(new StatisticsPK(ThreeDZ36HZ.KEY, prizeInfo.getLotno(), prizeInfo.getBatchcode()));
		StatisticsData date_threedzxhz_1 = new StatisticsData(new StatisticsPK(ThreeDZXHZ.KEY, prizeInfo.getLotno(), prizeInfo.getBatchcode()));
		
		data_threedzx_1.setValue(threedzx.toJson());
		date_threedz36_1.setValue(threedz36.toJson());
		date_threedz36hz_1.setValue(threedz36hz.toJson());
		date_threedzxhz_1.setValue(threedzxhz.toJson());
		
		
		data_threedzx_1.setValueString(threedzx.toString());
		date_threedz36_1.setValueString(threedz36.toString());
		date_threedz36hz_1.setValueString(threedz36hz.toString());
		date_threedzxhz_1.setValueString(threedzxhz.toString());
		
		
		statisticsDataService.merge(data_threedzx_1);
		statisticsDataService.merge(date_threedz36_1);
		statisticsDataService.merge(date_threedz36hz_1);
		statisticsDataService.merge(date_threedzxhz_1);
		
		logger.info("onPrize end :"+prizeInfo.getLotno()+ " " + prizeInfo.getBatchcode());
		try {
			logger.info("onPrize try to put to cache:"+prizeInfo.getLotno()+ " " + prizeInfo.getBatchcode());
			
			memcachedClient.set(ThreeDZX.KEY+"_"+prizeInfo.getBatchcode(), 864000, data_threedzx_1);
			memcachedClient.set(ThreeDZ36.KEY+"_"+prizeInfo.getBatchcode(), 864000, date_threedz36_1);
			memcachedClient.set(ThreeDZ36HZ.KEY+"_"+prizeInfo.getBatchcode(), 864000, date_threedz36hz_1);
			memcachedClient.set(ThreeDZXHZ.KEY+"_"+prizeInfo.getBatchcode(), 864000, date_threedzxhz_1);
			
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
		if(ThreeDZX.KEY.equals(key)) {
			ThreeDZX rx = new ThreeDZX().refresh(prizeInfoService,index);
			sdata.setValue(rx.toJson());
			sdata.setValueString(rx.toString());
		} else if(ThreeDZ36.KEY.equals(key)) {
			ThreeDZ36 rx = new ThreeDZ36().refresh(prizeInfoService,index);
			sdata.setValue(rx.toJson());
			sdata.setValueString(rx.toString());
		} else if(ThreeDZ36HZ.KEY.equals(key)) {
			ThreeDZ36HZ rx = new ThreeDZ36HZ().refresh(prizeInfoService,index);
			sdata.setValue(rx.toJson());
			sdata.setValueString(rx.toString());
		} else if(ThreeDZXHZ.KEY.equals(key)) {
			ThreeDZXHZ rx = new ThreeDZXHZ().refresh(prizeInfoService,index);
			sdata.setValue(rx.toJson());
			sdata.setValueString(rx.toString());
		}
		statisticsDataService.merge(sdata);
		logger.info("refresh end:key="+key);

	}

	@Override
	public void refreshAll(String batchcode,String lotno) {
		refresh(ThreeDZX.KEY,batchcode,lotno);
		refresh(ThreeDZ36.KEY,batchcode,lotno);
		refresh(ThreeDZ36HZ.KEY,batchcode,lotno);
		refresh(ThreeDZXHZ.KEY,batchcode,lotno);

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
