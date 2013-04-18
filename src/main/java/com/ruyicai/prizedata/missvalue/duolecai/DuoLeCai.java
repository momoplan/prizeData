package com.ruyicai.prizedata.missvalue.duolecai;

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

@Service("T01010MV")
public class DuoLeCai implements LotTypeMissValue {

	
	private static Logger logger = LoggerFactory.getLogger(DuoLeCai.class);
	
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
		StatisticsData data_duolecairx = statisticsDataService.findLatestOrByBatchcode("T01010",DuoLeCaiRX.KEY,batchcode);
		StatisticsData data_duolecaiq1 = statisticsDataService.findLatestOrByBatchcode("T01010",DuoLeCaiQ1.KEY,batchcode);
		StatisticsData data_duolecaiq2 = statisticsDataService.findLatestOrByBatchcode("T01010",DuoLeCaiQ2.KEY,batchcode);
		StatisticsData data_duolecaiq3 = statisticsDataService.findLatestOrByBatchcode("T01010",DuoLeCaiQ3.KEY,batchcode);
		StatisticsData data_duolecaiq2z = statisticsDataService.findLatestOrByBatchcode("T01010",DuoLeCaiQ2Z.KEY,batchcode);
		StatisticsData data_duolecaiq3z = statisticsDataService.findLatestOrByBatchcode("T01010",DuoLeCaiQ3Z.KEY,batchcode);
		StatisticsData data_duolecaiq3zh = statisticsDataService.findLatestOrByBatchcode("T01010",DuoLeCaiQ3ZH.KEY,batchcode);
		StatisticsData data_duolecaiqr5zh = statisticsDataService.findLatestOrByBatchcode("T01010",DuoLeCaiR5ZH.KEY,batchcode);
		StatisticsData data_duolecaiqr7zh = statisticsDataService.findLatestOrByBatchcode("T01010",DuoLeCaiR7ZH.KEY,batchcode);
		StatisticsData data_duolecaiqr8zh = statisticsDataService.findLatestOrByBatchcode("T01010",DuoLeCaiR8ZH.KEY,batchcode);
		
		DuoLeCaiRX duolecairx = DuoLeCaiRX.fromJsonToDuoLeCaiRX(data_duolecairx.getValue());
		DuoLeCaiQ1 duolecaiq1 = DuoLeCaiQ1.fromJsonToDuoLeCaiQ1(data_duolecaiq1.getValue());
		DuoLeCaiQ2 duolecaiq2 = DuoLeCaiQ2.fromJsonToDuoLeCaiQ2(data_duolecaiq2.getValue());
		DuoLeCaiQ3 duolecaiq3 = DuoLeCaiQ3.fromJsonToDuoLeCaiQ3(data_duolecaiq3.getValue());
		DuoLeCaiQ2Z duolecaiq2z = DuoLeCaiQ2Z.fromJsonToDuoLeCaiQ2Z(data_duolecaiq2z.getValue());
		DuoLeCaiQ3Z duolecaiq3z = DuoLeCaiQ3Z.fromJsonToDuoLeCaiQ3Z(data_duolecaiq3z.getValue());
		DuoLeCaiQ3ZH duolecaiq3zh = DuoLeCaiQ3ZH.fromJsonToDuoLeCaiQ3ZH(data_duolecaiq3zh.getValue());
		DuoLeCaiR5ZH duolecaiqr5zh = DuoLeCaiR5ZH.fromJsonToDuoLeCaiR5ZH(data_duolecaiqr5zh.getValue());
		DuoLeCaiR7ZH duolecaiqr7zh = DuoLeCaiR7ZH.fromJsonToDuoLeCaiR7ZH(data_duolecaiqr7zh.getValue());
		DuoLeCaiR8ZH duolecaiqr8zh = DuoLeCaiR8ZH.fromJsonToDuoLeCaiR8ZH(data_duolecaiqr8zh.getValue());
		
		duolecairx.onPrize(prizeInfo);
		duolecaiq1.onPrize(prizeInfo);
		duolecaiq2.onPrize(prizeInfo);
		duolecaiq3.onPrize(prizeInfo);
		duolecaiq2z.onPrize(prizeInfo);
		duolecaiq3z.onPrize(prizeInfo);
		duolecaiq3zh.onPrize(prizeInfo);
		duolecaiqr5zh.onPrize(prizeInfo);
		duolecaiqr7zh.onPrize(prizeInfo);
		duolecaiqr8zh.onPrize(prizeInfo);
		
		StatisticsData data_duolecairx_1 = new StatisticsData(new StatisticsPK(DuoLeCaiRX.KEY,prizeInfo.getLotno(),prizeInfo.getBatchcode()));
		StatisticsData data_duolecaiq1_1 = new StatisticsData(new StatisticsPK(DuoLeCaiQ1.KEY,prizeInfo.getLotno(),prizeInfo.getBatchcode()));
		StatisticsData data_duolecaiq2_1 = new StatisticsData(new StatisticsPK(DuoLeCaiQ2.KEY,prizeInfo.getLotno(),prizeInfo.getBatchcode()));
		StatisticsData data_duolecaiq3_1 = new StatisticsData(new StatisticsPK(DuoLeCaiQ3.KEY,prizeInfo.getLotno(),prizeInfo.getBatchcode()));
		StatisticsData data_duolecaiq2z_1 = new StatisticsData(new StatisticsPK(DuoLeCaiQ2Z.KEY,prizeInfo.getLotno(),prizeInfo.getBatchcode()));
		StatisticsData data_duolecaiq3z_1 = new StatisticsData(new StatisticsPK(DuoLeCaiQ3Z.KEY,prizeInfo.getLotno(),prizeInfo.getBatchcode()));
		StatisticsData data_duolecaiq3zh_1 = new StatisticsData(new StatisticsPK(DuoLeCaiQ3ZH.KEY,prizeInfo.getLotno(),prizeInfo.getBatchcode()));
		StatisticsData data_duolecaiqr5zh_1 = new StatisticsData(new StatisticsPK(DuoLeCaiR5ZH.KEY,prizeInfo.getLotno(),prizeInfo.getBatchcode()));
		StatisticsData data_duolecaiqr7zh_1 = new StatisticsData(new StatisticsPK(DuoLeCaiR7ZH.KEY,prizeInfo.getLotno(),prizeInfo.getBatchcode()));
		StatisticsData data_duolecaiqr8zh_1 = new StatisticsData(new StatisticsPK(DuoLeCaiR8ZH.KEY,prizeInfo.getLotno(),prizeInfo.getBatchcode()));
		
		data_duolecairx_1.setValue(duolecairx.toJson());
		data_duolecaiq1_1.setValue(duolecaiq1.toJson());
		data_duolecaiq2_1.setValue(duolecaiq2.toJson());
		data_duolecaiq3_1.setValue(duolecaiq3.toJson());
		data_duolecaiq2z_1.setValue(duolecaiq2z.toJson());
		data_duolecaiq3z_1.setValue(duolecaiq3z.toJson());
		data_duolecaiq3zh_1.setValue(duolecaiq3zh.toJson());
		data_duolecaiqr5zh_1.setValue(duolecaiqr5zh.toJson());
		data_duolecaiqr7zh_1.setValue(duolecaiqr7zh.toJson());
		data_duolecaiqr8zh_1.setValue(duolecaiqr8zh.toJson());
		
		
		data_duolecairx_1.setValueString(duolecairx.toString());
		data_duolecaiq1_1.setValueString(duolecaiq1.toString());
		data_duolecaiq2_1.setValueString(duolecaiq2.toString());
		data_duolecaiq3_1.setValueString(duolecaiq3.toString());
		data_duolecaiq2z_1.setValueString(duolecaiq2z.toString());
		data_duolecaiq3z_1.setValueString(duolecaiq3z.toString());
		data_duolecaiq3zh_1.setValueString(duolecaiq3zh.toString());
		data_duolecaiqr5zh_1.setValueString(duolecaiqr5zh.toString());
		data_duolecaiqr7zh_1.setValueString(duolecaiqr7zh.toString());
		data_duolecaiqr8zh_1.setValueString(duolecaiqr8zh.toString());
		
		
		statisticsDataService.merge(data_duolecairx_1);
		statisticsDataService.merge(data_duolecaiq1_1);
		statisticsDataService.merge(data_duolecaiq2_1);
		statisticsDataService.merge(data_duolecaiq3_1);
		statisticsDataService.merge(data_duolecaiq2z_1);
		statisticsDataService.merge(data_duolecaiq3z_1);
		statisticsDataService.merge(data_duolecaiq3zh_1);
		statisticsDataService.merge(data_duolecaiqr5zh_1);
		statisticsDataService.merge(data_duolecaiqr7zh_1);
		statisticsDataService.merge(data_duolecaiqr8zh_1);
		
		logger.info("onPrize end :"+prizeInfo.getLotno()+ " " + prizeInfo.getBatchcode());
		
		try {
			logger.info("onPrize try to put to cache:"+prizeInfo.getLotno()+ " " + prizeInfo.getBatchcode());
			
			
			memcachedClient.set(DuoLeCaiRX.KEY+"_"+prizeInfo.getBatchcode(), 129600, data_duolecairx_1);
			memcachedClient.set(DuoLeCaiQ1.KEY+"_"+prizeInfo.getBatchcode(), 129600,data_duolecaiq1_1);
			memcachedClient.set(DuoLeCaiQ2.KEY+"_"+prizeInfo.getBatchcode(), 129600,data_duolecaiq2_1);
			memcachedClient.set(DuoLeCaiQ3.KEY+"_"+prizeInfo.getBatchcode(), 129600,data_duolecaiq3_1);
			memcachedClient.set(DuoLeCaiQ2Z.KEY+"_"+prizeInfo.getBatchcode(), 129600,data_duolecaiq2z_1);
			memcachedClient.set(DuoLeCaiQ3Z.KEY+"_"+prizeInfo.getBatchcode(), 129600,data_duolecaiq3z_1);
			memcachedClient.set(DuoLeCaiQ3ZH.KEY+"_"+prizeInfo.getBatchcode(), 129600,data_duolecaiq3zh_1);
			memcachedClient.set(DuoLeCaiR5ZH.KEY+"_"+prizeInfo.getBatchcode(), 129600,data_duolecaiqr5zh_1);
			memcachedClient.set(DuoLeCaiR7ZH.KEY+"_"+prizeInfo.getBatchcode(), 129600,data_duolecaiqr7zh_1);
			memcachedClient.set(DuoLeCaiR8ZH.KEY+"_"+prizeInfo.getBatchcode(), 129600,data_duolecaiqr8zh_1);
			
			
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
		if(DuoLeCaiRX.KEY.equals(key)) {
			DuoLeCaiRX rx = new DuoLeCaiRX().refresh(prizeInfoService,index);
			sdata.setValue(rx.toJson());
			sdata.setValueString(rx.toString());
		}else if(DuoLeCaiQ1.KEY.equals(key)) {
			DuoLeCaiQ1 q1 = new DuoLeCaiQ1().refresh(prizeInfoService,index);
			sdata.setValue(q1.toJson());
			sdata.setValueString(q1.toString());
		}else if(DuoLeCaiQ2.KEY.equals(key)) {
			DuoLeCaiQ2 q2 = new DuoLeCaiQ2().refresh(prizeInfoService,index);
			sdata.setValue(q2.toJson());
			sdata.setValueString(q2.toString());
		}else if(DuoLeCaiQ3.KEY.equals(key)) {
			DuoLeCaiQ3 q3 = new DuoLeCaiQ3().refresh(prizeInfoService,index);
			sdata.setValue(q3.toJson());
			sdata.setValueString(q3.toString());
		}else if(DuoLeCaiQ2Z.KEY.equals(key)) {
			DuoLeCaiQ2Z q2z = new DuoLeCaiQ2Z().refresh(prizeInfoService,index);
			sdata.setValue(q2z.toJson());
			sdata.setValueString(q2z.toString());
		}else if(DuoLeCaiQ3Z.KEY.equals(key)) {
			DuoLeCaiQ3Z q3z = new DuoLeCaiQ3Z().refresh(prizeInfoService,index);
			sdata.setValue(q3z.toJson());
			sdata.setValueString(q3z.toString());
		}else if(DuoLeCaiQ3ZH.KEY.equals(key)) {
			DuoLeCaiQ3ZH q3zh = new DuoLeCaiQ3ZH().refresh(prizeInfoService,index);
			sdata.setValue(q3zh.toJson());
			sdata.setValueString(q3zh.toString());
		}else if(DuoLeCaiR5ZH.KEY.equals(key)) {
			DuoLeCaiR5ZH r5zh = new DuoLeCaiR5ZH().refresh(prizeInfoService,index);
			sdata.setValue(r5zh.toJson());
			sdata.setValueString(r5zh.toString());
		}else if(DuoLeCaiR7ZH.KEY.equals(key)) {
			DuoLeCaiR7ZH r7zh = new DuoLeCaiR7ZH().refresh(prizeInfoService,index);
			sdata.setValue(r7zh.toJson());
			sdata.setValueString(r7zh.toString());
		}else if(DuoLeCaiR8ZH.KEY.equals(key)) {
			DuoLeCaiR8ZH r8zh = new DuoLeCaiR8ZH().refresh(prizeInfoService,index);
			sdata.setValue(r8zh.toJson());
			sdata.setValueString(r8zh.toString());
		}
		statisticsDataService.merge(sdata);
		logger.info("refresh end:key="+key);
		
	}

	@Override
	public void refreshAll(String batchcode,String lotno) {
		refresh(DuoLeCaiRX.KEY,batchcode,lotno);
		refresh(DuoLeCaiQ1.KEY,batchcode,lotno);
		refresh(DuoLeCaiQ2.KEY,batchcode,lotno);
		refresh(DuoLeCaiQ3.KEY,batchcode,lotno);
		refresh(DuoLeCaiQ2Z.KEY,batchcode,lotno);
		refresh(DuoLeCaiQ3Z.KEY,batchcode,lotno);
		refresh(DuoLeCaiQ3ZH.KEY,batchcode,lotno);
		refresh(DuoLeCaiR5ZH.KEY,batchcode,lotno);
		refresh(DuoLeCaiR7ZH.KEY,batchcode,lotno);
		refresh(DuoLeCaiR8ZH.KEY,batchcode,lotno);
		
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
