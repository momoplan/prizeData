package com.ruyicai.prizedata.missvalue.daletou;

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

@Service("T01001MV")
public class DaLeTou implements LotTypeMissValue {

	
	private static Logger logger = LoggerFactory.getLogger(DaLeTou.class);
	
	@Resource
	private PrizeInfoService prizeInfoService;
	
	@Resource
	private StatisticsDataService statisticsDataService;
	
	@Autowired
	MemcachedClient memcachedClient;
	
	@Override
	public PrizeInfo transformPrizeInfo(PrizeInfo prizeInfo) {
		String[] win = prizeInfo.getWinbasecode().split("\\+");
		prizeInfo.setWinbasecode(win[0].replace(" ", ""));
		prizeInfo.setWinspecialcode(win[1].replace(" ", ""));
		return prizeInfo;
	}

	@Override
	public void onPrize(PrizeInfo prizeInfo,String batchcode) {
		logger.info("onPrize start :"+prizeInfo.getLotno()+ " " + prizeInfo.getBatchcode());
		
		//根据key查询最新的遗漏结果，如果batchcode存在，则根据key和batchcode查询遗漏结果
		StatisticsData data_daletounormal = statisticsDataService.findLatestOrByBatchcode("T01001",DaLeTouNormal.KEY,batchcode);
		DaLeTouNormal daletounormal = DaLeTouNormal.fromJsonToDaLeTouNormal(data_daletounormal.getValue());
		daletounormal.onPrize(prizeInfo);
		
		//创建新的遗漏数据
		StatisticsData data_daletounormal_1 = new StatisticsData(new StatisticsPK(DaLeTouNormal.KEY,prizeInfo.getLotno(),prizeInfo.getBatchcode()));
		data_daletounormal_1.setValue(daletounormal.toJson());
		data_daletounormal_1.setValueString(daletounormal.toString());
		
		//保存新的遗漏数据
		statisticsDataService.merge(data_daletounormal_1);
		logger.info("onPrize end :"+prizeInfo.getLotno()+ " " + prizeInfo.getBatchcode());
		
		try {
			logger.info("onPrize try to put to cache:"+prizeInfo.getLotno()+ " " + prizeInfo.getBatchcode());
			
			//将新的遗漏结果保存到缓存中
			memcachedClient.add(DaLeTouNormal.KEY+"_"+prizeInfo.getBatchcode(),864000, data_daletounormal_1);
			
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
		if(DaLeTouNormal.KEY.equals(key)) {
			DaLeTouNormal x = new DaLeTouNormal().refresh(prizeInfoService,index);
			sdata.setValue(x.toJson());
			sdata.setValueString(x.toString());
		}
		statisticsDataService.merge(sdata);
		logger.info("refresh end:key="+key);
	}

	@Override
	public void refreshAll(String batchcode,String lotno) {
		refresh(DaLeTouNormal.KEY,batchcode,lotno);

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
