package com.ruyicai.prizedata.missvalue.qilecai;

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

@Service("F47102MV")
public class QiLeCai implements LotTypeMissValue {

	
	private static Logger logger = LoggerFactory.getLogger(QiLeCai.class);
	@Resource
	private PrizeInfoService prizeInfoService;
	
	@Resource
	private StatisticsDataService statisticsDataService;
	
	@Autowired
	MemcachedClient memcachedClient;
	
	
	@Override
	public PrizeInfo transformPrizeInfo(PrizeInfo prizeInfo) {
		String[] win = prizeInfo.getWinbasecode().split("\\|");
		prizeInfo.setWinbasecode(win[0]);
		prizeInfo.setWinspecialcode(win[1]);
		return prizeInfo;
	}

	@Override
	public void onPrize(PrizeInfo prizeInfo,String batchcode) {
		
		logger.info("onPrize start :"+prizeInfo.getLotno()+ " " + prizeInfo.getBatchcode());
		
		StatisticsData data_qilecaix = statisticsDataService.findLatestOrByBatchcode("F47102",QiLeCaiX.KEY,batchcode);
		
		QiLeCaiX qilecaix = QiLeCaiX.fromJsonToQiLeCaiX(data_qilecaix.getValue());
		
		qilecaix.onPrize(prizeInfo);
		
		StatisticsData data_qilecaix_1 = new StatisticsData(new StatisticsPK(QiLeCaiX.KEY,prizeInfo.getLotno(),prizeInfo.getBatchcode()));
		
		data_qilecaix_1.setValue(qilecaix.toJson());
		data_qilecaix_1.setValueString(qilecaix.toString());
		
		statisticsDataService.merge(data_qilecaix_1);
		logger.info("onPrize end :"+prizeInfo.getLotno()+ " " + prizeInfo.getBatchcode());
		try {
			logger.info("onPrize try to put to cache:"+prizeInfo.getLotno()+ " " + prizeInfo.getBatchcode());
			
			memcachedClient.add(QiLeCaiX.KEY+"_"+prizeInfo.getBatchcode(),864000, data_qilecaix_1);
			
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
		if(QiLeCaiX.KEY.equals(key)) {
			QiLeCaiX x = new QiLeCaiX().refresh(prizeInfoService,index);
			sdata.setValue(x.toJson());
			sdata.setValueString(x.toString());
		}
		statisticsDataService.merge(sdata);
		logger.info("refresh end:key="+key);

	}

	@Override
	public void refreshAll(String batchcode,String lotno) {
		refresh(QiLeCaiX.KEY,batchcode,lotno);

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
