package com.ruyicai.prizedata.missvalue.pailiewu;

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

@Service("T01011MV")
public class PaiLieWu implements LotTypeMissValue {

	private static Logger logger = LoggerFactory.getLogger(PaiLieWu.class);
	
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
		StatisticsData data_pailiewuzx = statisticsDataService.findLatestOrByBatchcode("T01011",PaiLieWuZX.KEY,batchcode);
		PaiLieWuZX pailiewuzx = PaiLieWuZX.fromJsonToPaiLieWuZX(data_pailiewuzx.getValue());
		pailiewuzx.onPrize(prizeInfo);
		
		StatisticsData data_pailiewuzx_1 = new StatisticsData(new StatisticsPK(PaiLieWuZX.KEY, prizeInfo.getLotno(), prizeInfo.getBatchcode()));
		data_pailiewuzx_1.setValue(pailiewuzx.toJson());
		data_pailiewuzx_1.setValueString(pailiewuzx.toString());
		statisticsDataService.merge(data_pailiewuzx_1);
		
		logger.info("onPrize end :"+prizeInfo.getLotno()+ " " + prizeInfo.getBatchcode());
		try {
			logger.info("onPrize try to put to cache:"+prizeInfo.getLotno()+ " " + prizeInfo.getBatchcode());
			
			memcachedClient.set(PaiLieWuZX.KEY+"_"+prizeInfo.getBatchcode(), 864000, data_pailiewuzx_1);
			
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
		if(PaiLieWuZX.KEY.equals(key)) {
			PaiLieWuZX rx = new PaiLieWuZX().refresh(prizeInfoService,index);
			sdata.setValue(rx.toJson());
			sdata.setValueString(rx.toString());
		}
		statisticsDataService.merge(sdata);
		logger.info("refresh end:key="+key);

	}

	@Override
	public void refreshAll(String batchcode,String lotno) {
		refresh(PaiLieWuZX.KEY,batchcode,lotno);

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
