package com.ruyicai.prizedata.missvalue;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.rubyeye.xmemcached.MemcachedClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruyicai.prizedata.domain.PrizeInfo;
import com.ruyicai.prizedata.domain.StatisticsData;
import com.ruyicai.prizedata.service.PrizeInfoService;
import com.ruyicai.prizedata.service.StatisticsDataService;

@Service("missValueService")
public class MissValueService {

	private static Logger logger = LoggerFactory.getLogger(MissValueService.class);
	@Autowired
	private Map<String,LotTypeMissValue> map;
	
	@Resource
	private PrizeInfoService prizeInfoService;
	
	@Resource
	private StatisticsDataService statisticsDataService;
	
	@Autowired
	MemcachedClient memcachedClient;
	
	/**
	 * 
	 * @param prizeInfo
	 */
	public synchronized void onPrize(PrizeInfo prizeInfo) {
		if(prizeInfoService.findByLotnoAndBatchcode(prizeInfo.getLotno(), prizeInfo.getBatchcode())!=null) {
			logger.info("开奖信息已经存在，重复开奖，不再统计遗漏");
			return;
		}
		LotTypeMissValue type = map.get(prizeInfo.getLotno()+"MV");
		if(type!=null) {
			prizeInfo = type.transformPrizeInfo(prizeInfo);
			type.onPrize(prizeInfo,null);
			prizeInfoService.merge(prizeInfo);
		}else {
			logger.info("not fund lottype:"+prizeInfo.getLotno());
		}
		
		
	}
	
	
	public void refresh(String lotno,String key,String batchcode) {
		LotTypeMissValue type = map.get(lotno+"MV");
		if(key.equals("all")) {
			type.refreshAll(batchcode,lotno);
		}else {
			type.refresh(key,batchcode,lotno);
		}
	}
	
	
	public List<StatisticsData> getLatestCache(String lotno,String key,int count,int time) {
		try {
			List<StatisticsData> list = memcachedClient.get(lotno+"_list_"+key+"_"+count);
			if(list==null||list.isEmpty()) {
				list = statisticsDataService.findListByLotnoAndKey(lotno,key,count);
				memcachedClient.set(lotno+"_list_"+key+"_"+count, time, list);
			}
			return list;
		} catch (Exception e) {
			logger.info("findListByLotnoAndKeyThroughCache err",e);
			return null;
		} 
	}
	
	
	public List<StatisticsData> latestCacheOnPrize(String lotno,String key,int count,int time) {

		List<StatisticsData> list = statisticsDataService.findListByLotnoAndKey(lotno,key,count);
		try {
			memcachedClient.set(lotno+"_list_"+key+"_"+count, time, list);
		} catch (Exception e) {
			logger.info("更新最近"+count+"期缓存出错", e);
		}
		return list;
	}
	
	
	public void refreshOnPrize(String lotno,String batchcode,String preBatchcode) {
		LotTypeMissValue type = map.get(lotno+"MV");
		type.refreshAllByOnPrize(lotno, batchcode, preBatchcode);
	}
}
