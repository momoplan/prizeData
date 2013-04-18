package com.ruyicai.prizedata.missvalue.five22;

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

@Service("T01013MV")
public class Five22 implements LotTypeMissValue {

	
	private static Logger logger = LoggerFactory.getLogger(Five22.class);
	@Resource
	private PrizeInfoService prizeInfoService;
	
	@Resource
	private StatisticsDataService statisticsDataService;
	
	@Autowired
	MemcachedClient memcachedClient;
	
	@Override
	public PrizeInfo transformPrizeInfo(PrizeInfo prizeInfo) {
		prizeInfo.setWinbasecode(prizeInfo.getWinbasecode().replace(" ", ""));
		return prizeInfo;
	}

	@Override
	public void onPrize(PrizeInfo prizeInfo, String batchcode) {
		logger.info("onPrize start :"+prizeInfo.getLotno()+ " " + prizeInfo.getBatchcode());
		StatisticsData data_five22x = statisticsDataService.findLatestOrByBatchcode("T01013",Five22X.KEY,batchcode);
		Five22X five22x = Five22X.fromJsonToFive22X(data_five22x.getValue());
		five22x.onPrize(prizeInfo);
		
		StatisticsData data_five22x_1 = new StatisticsData(new StatisticsPK(Five22X.KEY,prizeInfo.getLotno(),prizeInfo.getBatchcode()));
		data_five22x_1.setValue(five22x.toJson());
		data_five22x_1.setValueString(five22x.toString());
		
		statisticsDataService.merge(data_five22x_1);
		logger.info("onPrize end :"+prizeInfo.getLotno()+ " " + prizeInfo.getBatchcode());

		try {
			logger.info("onPrize try to put to cache:"+prizeInfo.getLotno()+ " " + prizeInfo.getBatchcode());
			memcachedClient.add(Five22X.KEY+"_"+prizeInfo.getBatchcode(),864000, data_five22x_1);
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
		if(Five22X.KEY.equals(key)) {
			Five22X x = new Five22X().refresh(prizeInfoService,index);
			sdata.setValue(x.toJson());
			sdata.setValueString(x.toString());
		}
		statisticsDataService.merge(sdata);
		logger.info("refresh end:key="+key);

	}

	@Override
	public void refreshAll(String batchcode, String lotno) {
		refresh(Five22X.KEY,batchcode,lotno);

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
