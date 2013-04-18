package com.ruyicai.prizedata.missvalue.shuangseqiu;

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

@Service("F47104MV")
public class ShuangSeQiu implements LotTypeMissValue{

	private static Logger logger = LoggerFactory.getLogger(ShuangSeQiu.class);
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
		StatisticsData data_shuangseqiux = statisticsDataService.findLatestOrByBatchcode("F47104", ShuangSeQiuX.KEY,batchcode);
		
		ShuangSeQiuX shuangseqiux = ShuangSeQiuX.fromJsonToShuangSeQiuX(data_shuangseqiux.getValue());
		
		shuangseqiux.onPrize(prizeInfo);
		
		StatisticsData data_shuangseqiux_1 = new StatisticsData(new StatisticsPK(ShuangSeQiuX.KEY, prizeInfo.getLotno(), prizeInfo.getBatchcode()));
		
		data_shuangseqiux_1.setValue(shuangseqiux.toJson());
		data_shuangseqiux_1.setValueString(shuangseqiux.toString());
		
		statisticsDataService.merge(data_shuangseqiux_1);
		
		logger.info("onPrize end :"+prizeInfo.getLotno()+ " " + prizeInfo.getBatchcode());
		
		try {
			logger.info("onPrize try to put to cache:"+prizeInfo.getLotno()+ " " + prizeInfo.getBatchcode());
			memcachedClient.add(ShuangSeQiuX.KEY+"_"+prizeInfo.getBatchcode(), 864000,data_shuangseqiux_1);
			
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
		if(ShuangSeQiuX.KEY.equals(key)) {
			ShuangSeQiuX x = new ShuangSeQiuX().refresh(prizeInfoService,index);
			sdata.setValue(x.toJson());
			sdata.setValueString(x.toString());
		}
		statisticsDataService.merge(sdata);
		logger.info("refresh end:key="+key);
	}

	@Override
	public void refreshAll(String batchcode,String lotno) {
		refresh(ShuangSeQiuX.KEY,batchcode,lotno);
		
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
