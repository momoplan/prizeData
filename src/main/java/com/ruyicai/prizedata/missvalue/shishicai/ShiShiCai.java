package com.ruyicai.prizedata.missvalue.shishicai;

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

@Service("T01007MV")
public class ShiShiCai implements LotTypeMissValue{

	private static Logger logger = LoggerFactory.getLogger(ShiShiCai.class);
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

	/* (non-Javadoc)
	 * @see com.ruyicai.prizedata.missvalue.LotTypeMissValue#onPrize(com.ruyicai.prizedata.domain.PrizeInfo, java.lang.String)
	 */
	@Override
	public void onPrize(PrizeInfo prizeInfo,String batchcode) {
		logger.info("onPrize start :"+prizeInfo.getLotno()+ " " + prizeInfo.getBatchcode());
		StatisticsData data_shishicai5x = statisticsDataService.findLatestOrByBatchcode("T01007", ShiShiCai5X.KEY, batchcode);
		StatisticsData data_shishicai2d = statisticsDataService.findLatestOrByBatchcode("T01007", ShiShiCai2D.KEY, batchcode);
		StatisticsData data_shishicai3d = statisticsDataService.findLatestOrByBatchcode("T01007", ShiShiCai3D.KEY, batchcode);
		StatisticsData data_shishicai2z = statisticsDataService.findLatestOrByBatchcode("T01007", ShiShiCai2Z.KEY, batchcode);
		StatisticsData data_shishicai3z = statisticsDataService.findLatestOrByBatchcode("T01007", ShiShiCai6Z.KEY, batchcode);
		StatisticsData data_shishicai6z = statisticsDataService.findLatestOrByBatchcode("T01007", ShiShiCai3Z.KEY, batchcode);
		StatisticsData data_shishicaidd = statisticsDataService.findLatestOrByBatchcode("T01007", ShiShiCaiDD.KEY, batchcode);
		StatisticsData data_shishicai2zx = statisticsDataService.findLatestOrByBatchcode("T01007", ShiShiCai2ZX.KEY, batchcode);
		StatisticsData data_shishicai2zxhz = statisticsDataService.findLatestOrByBatchcode("T01007", ShiShiCai2ZXHZ.KEY, batchcode);
		StatisticsData data_shishicaiz36 = statisticsDataService.findLatestOrByBatchcode("T01007", ShiShiCaiZ36.KEY, batchcode);
		
		ShiShiCai5X shishicai5x = ShiShiCai5X.fromJsonToShiShiCai5X(data_shishicai5x.getValue());
		ShiShiCai2D shishicai2d = ShiShiCai2D.fromJsonToShiShiCai2D(data_shishicai2d.getValue());
		ShiShiCai3D shishicai3d = ShiShiCai3D.fromJsonToShiShiCai3D(data_shishicai3d.getValue());
		ShiShiCai2Z shishicai2z = ShiShiCai2Z.fromJsonToShiShiCai2Z(data_shishicai2z.getValue());
		ShiShiCai6Z shishicai3z = ShiShiCai6Z.fromJsonToShiShiCai3Z(data_shishicai3z.getValue());
		ShiShiCai3Z shishicai6z = ShiShiCai3Z.fromJsonToShiShiCai6Z(data_shishicai6z.getValue());
		ShiShiCaiDD shishicaidd = ShiShiCaiDD.fromJsonToShiShiCaiDD(data_shishicaidd.getValue());
		ShiShiCai2ZX shishicai2zx = ShiShiCai2ZX.fromJsonToShiShiCai2ZX(data_shishicai2zx.getValue());
		ShiShiCai2ZXHZ shishicai2zxhz = ShiShiCai2ZXHZ.fromJsonToShiShiCai2ZXHZ(data_shishicai2zxhz.getValue());
		ShiShiCaiZ36 shishicaiz36 = ShiShiCaiZ36.fromJsonToShiShiCaiZ36(data_shishicaiz36.getValue());
		
		shishicai5x.onPrize(prizeInfo);
		shishicai2d.onPrize(prizeInfo);
		shishicai3d.onPrize(prizeInfo);
		shishicai2z.onPrize(prizeInfo);
		shishicai3z.onPrize(prizeInfo);
		shishicai6z.onPrize(prizeInfo);
		shishicaidd.onPrize(prizeInfo);
		shishicai2zx.onPrize(prizeInfo);
		shishicai2zxhz.onPrize(prizeInfo);
		shishicaiz36.onPrize(prizeInfo);
		
		
		StatisticsData data_shishicai5x_1 = new StatisticsData(new StatisticsPK(ShiShiCai5X.KEY, prizeInfo.getLotno(), prizeInfo.getBatchcode()));
		StatisticsData data_shishicai2d_1 = new StatisticsData(new StatisticsPK(ShiShiCai2D.KEY, prizeInfo.getLotno(), prizeInfo.getBatchcode()));
		StatisticsData data_shishicai3d_1 = new StatisticsData(new StatisticsPK(ShiShiCai3D.KEY, prizeInfo.getLotno(), prizeInfo.getBatchcode()));
		StatisticsData data_shishicai2z_1 = new StatisticsData(new StatisticsPK(ShiShiCai2Z.KEY, prizeInfo.getLotno(), prizeInfo.getBatchcode()));
		StatisticsData data_shishicai3z_1 = new StatisticsData(new StatisticsPK(ShiShiCai6Z.KEY, prizeInfo.getLotno(), prizeInfo.getBatchcode()));
		StatisticsData data_shishicai6z_1 = new StatisticsData(new StatisticsPK(ShiShiCai3Z.KEY, prizeInfo.getLotno(), prizeInfo.getBatchcode()));
		StatisticsData data_shishicaidd_1 = new StatisticsData(new StatisticsPK(ShiShiCaiDD.KEY, prizeInfo.getLotno(), prizeInfo.getBatchcode()));
		StatisticsData data_shishicai2zx_1 = new StatisticsData(new StatisticsPK(ShiShiCai2ZX.KEY, prizeInfo.getLotno(), prizeInfo.getBatchcode()));
		StatisticsData data_shishicai2zxhz_1 = new StatisticsData(new StatisticsPK(ShiShiCai2ZXHZ.KEY, prizeInfo.getLotno(), prizeInfo.getBatchcode()));
		StatisticsData data_shishicaiz36_1 = new StatisticsData(new StatisticsPK(ShiShiCaiZ36.KEY, prizeInfo.getLotno(), prizeInfo.getBatchcode()));
		
		data_shishicai5x_1.setValue(shishicai5x.toJson());
		data_shishicai2d_1.setValue(shishicai2d.toJson());
		data_shishicai3d_1.setValue(shishicai3d.toJson());
		data_shishicai2z_1.setValue(shishicai2z.toJson());
		data_shishicai3z_1.setValue(shishicai3z.toJson());
		data_shishicai6z_1.setValue(shishicai6z.toJson());
		data_shishicaidd_1.setValue(shishicaidd.toJson());
		data_shishicai2zx_1.setValue(shishicai2zx.toJson());
		data_shishicai2zxhz_1.setValue(shishicai2zxhz.toJson());
		data_shishicaiz36_1.setValue(shishicaiz36.toJson());
		
		data_shishicai5x_1.setValueString(shishicai5x.toString());
		data_shishicai2d_1.setValueString(shishicai2d.toString());
		data_shishicai3d_1.setValueString(shishicai3d.toString());
		data_shishicai2z_1.setValueString(shishicai2z.toString());
		data_shishicai3z_1.setValueString(shishicai3z.toString());
		data_shishicai6z_1.setValueString(shishicai6z.toString());
		data_shishicaidd_1.setValueString(shishicaidd.toString());
		data_shishicai2zx_1.setValueString(shishicai2zx.toString());
		data_shishicai2zxhz_1.setValueString(shishicai2zxhz.toString());
		data_shishicaiz36_1.setValueString(shishicaiz36.toString());
		
		statisticsDataService.merge(data_shishicai5x_1);
		statisticsDataService.merge(data_shishicai2d_1);
		statisticsDataService.merge(data_shishicai3d_1);
		statisticsDataService.merge(data_shishicai2z_1);
		statisticsDataService.merge(data_shishicai3z_1);
		statisticsDataService.merge(data_shishicai6z_1);
		statisticsDataService.merge(data_shishicaidd_1);
		statisticsDataService.merge(data_shishicai2zx_1);
		statisticsDataService.merge(data_shishicai2zxhz_1);
		statisticsDataService.merge(data_shishicaiz36_1);
		
		logger.info("onPrize end :"+prizeInfo.getLotno()+ " " + prizeInfo.getBatchcode());
		try {
			logger.info("onPrize try to put to cache:"+prizeInfo.getLotno()+ " " + prizeInfo.getBatchcode());
			
			memcachedClient.set(ShiShiCai5X.KEY+"_"+prizeInfo.getBatchcode(), 129600,data_shishicai5x_1);
			memcachedClient.set(ShiShiCai2D.KEY+"_"+prizeInfo.getBatchcode(), 129600,data_shishicai2d_1);
			memcachedClient.set(ShiShiCai3D.KEY+"_"+prizeInfo.getBatchcode(), 129600,data_shishicai3d_1);
			memcachedClient.set(ShiShiCai2Z.KEY+"_"+prizeInfo.getBatchcode(), 129600,data_shishicai2z_1);
			memcachedClient.set(ShiShiCai3Z.KEY+"_"+prizeInfo.getBatchcode(), 129600,data_shishicai3z_1);
			memcachedClient.set(ShiShiCai6Z.KEY+"_"+prizeInfo.getBatchcode(), 129600,data_shishicai6z_1);
			memcachedClient.set(ShiShiCaiDD.KEY+"_"+prizeInfo.getBatchcode(), 129600,data_shishicaidd_1);
			memcachedClient.set(ShiShiCai2ZX.KEY+"_"+prizeInfo.getBatchcode(), 129600,data_shishicai2zx_1);
			memcachedClient.set(ShiShiCai2ZXHZ.KEY+"_"+prizeInfo.getBatchcode(), 129600,data_shishicai2zxhz_1);
			memcachedClient.set(ShiShiCaiZ36.KEY+"_"+prizeInfo.getBatchcode(), 129600,data_shishicaiz36_1);
			
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
		if(ShiShiCai5X.KEY.equals(key)) {
			ShiShiCai5X x5 = new ShiShiCai5X().refresh(prizeInfoService,index);
			sdata.setValue(x5.toJson());
			sdata.setValueString(x5.toString());
		}else if(ShiShiCai2D.KEY.equals(key)) {
			ShiShiCai2D d2 = new ShiShiCai2D().refresh(prizeInfoService,index);
			sdata.setValue(d2.toJson());
			sdata.setValueString(d2.toString());
		}else if(ShiShiCai3D.KEY.equals(key)) {
			ShiShiCai3D d3 = new ShiShiCai3D().refresh(prizeInfoService,index);
			sdata.setValue(d3.toJson());
			sdata.setValueString(d3.toString());
		}else if(ShiShiCai2Z.KEY.equals(key)) {
			ShiShiCai2Z z2 = new ShiShiCai2Z().refresh(prizeInfoService,index);
			sdata.setValue(z2.toJson());
			sdata.setValueString(z2.toString());
		}else if(ShiShiCai6Z.KEY.equals(key)) {
			ShiShiCai6Z z3 = new ShiShiCai6Z().refresh(prizeInfoService,index);
			sdata.setValue(z3.toJson());
			sdata.setValueString(z3.toString());
		}else if(ShiShiCai3Z.KEY.equals(key)) {
			ShiShiCai3Z z6 = new ShiShiCai3Z().refresh(prizeInfoService,index);
			sdata.setValue(z6.toJson());
			sdata.setValueString(z6.toString());
		}else if(ShiShiCaiDD.KEY.equals(key)) {
			ShiShiCaiDD dd = new ShiShiCaiDD().refresh(prizeInfoService,index);
			sdata.setValue(dd.toJson());
			sdata.setValueString(dd.toString());
		}else if(ShiShiCai2ZX.KEY.equals(key)) {
			ShiShiCai2ZX zx2 = new ShiShiCai2ZX().refresh(prizeInfoService,index);
			sdata.setValue(zx2.toJson());
			sdata.setValueString(zx2.toString());
		}else if(ShiShiCai2ZXHZ.KEY.equals(key)) {
			ShiShiCai2ZXHZ ZXHZ2 = new ShiShiCai2ZXHZ().refresh(prizeInfoService,index);
			sdata.setValue(ZXHZ2.toJson());
			sdata.setValueString(ZXHZ2.toString());
		}else if(ShiShiCaiZ36.KEY.equals(key)) {
			ShiShiCaiZ36 Z36 = new ShiShiCaiZ36().refresh(prizeInfoService,index);
			sdata.setValue(Z36.toJson());
			sdata.setValueString(Z36.toString());
		}
		statisticsDataService.merge(sdata);
		logger.info("refresh end:key="+key);
		
	}

	@Override
	public void refreshAll(String batchcode,String lotno) {
		refresh(ShiShiCai5X.KEY,batchcode,lotno);
		refresh(ShiShiCai2D.KEY,batchcode,lotno);
		refresh(ShiShiCai3D.KEY,batchcode,lotno);
		refresh(ShiShiCai2Z.KEY,batchcode,lotno);
		refresh(ShiShiCai3Z.KEY,batchcode,lotno);
		refresh(ShiShiCai6Z.KEY,batchcode,lotno);
		refresh(ShiShiCaiDD.KEY,batchcode,lotno);
		refresh(ShiShiCai2ZX.KEY,batchcode,lotno);
		refresh(ShiShiCai2ZXHZ.KEY,batchcode,lotno);
		refresh(ShiShiCaiZ36.KEY,batchcode,lotno);
		
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
