package com.ruyicai.prizedata.missvalue.qixingcai;

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

@Service("T01009MV")
public class QiXingCai implements LotTypeMissValue{

	private static Logger logger = LoggerFactory.getLogger(QiXingCai.class);
	
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
		StatisticsData data_qixingcaizx = statisticsDataService.findLatestOrByBatchcode("T01009",QiXingCaiZX.KEY,batchcode);
		QiXingCaiZX qixingcaizx = QiXingCaiZX.fromJsonToQiXingCaiZX(data_qixingcaizx.getValue());
		qixingcaizx.onPrize(prizeInfo);
		
		StatisticsData data_qixingcaizx_1 = new StatisticsData(new StatisticsPK(QiXingCaiZX.KEY, prizeInfo.getLotno(), prizeInfo.getBatchcode()));
		data_qixingcaizx_1.setValue(qixingcaizx.toJson());
		data_qixingcaizx_1.setValueString(qixingcaizx.toString());
		statisticsDataService.merge(data_qixingcaizx_1);
		
		logger.info("onPrize end :"+prizeInfo.getLotno()+ " " + prizeInfo.getBatchcode());
		try {
			logger.info("onPrize try to put to cache:"+prizeInfo.getLotno()+ " " + prizeInfo.getBatchcode());
			
			memcachedClient.set(QiXingCaiZX.KEY+"_"+prizeInfo.getBatchcode(), 864000, data_qixingcaizx_1);
			
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
		if(QiXingCaiZX.KEY.equals(key)) {
			QiXingCaiZX rx = new QiXingCaiZX().refresh(prizeInfoService,index);
			sdata.setValue(rx.toJson());
			sdata.setValueString(rx.toString());
		}
		statisticsDataService.merge(sdata);
		logger.info("refresh end:key="+key);
		
	}

	@Override
	public void refreshAll(String batchcode,String lotno) {
		refresh(QiXingCaiZX.KEY,batchcode,lotno);
		
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
