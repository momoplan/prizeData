package com.ruyicai.prizedata.hotcold.pailiesan;

import javax.annotation.Resource;

import net.rubyeye.xmemcached.MemcachedClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruyicai.prizedata.domain.StatisticsData;
import com.ruyicai.prizedata.domain.StatisticsPK;
import com.ruyicai.prizedata.hotcold.LotTypeHotCold;
import com.ruyicai.prizedata.service.PrizeInfoService;
import com.ruyicai.prizedata.service.StatisticsDataService;

@Service("T01002HC")
public class PaiLieSan implements LotTypeHotCold{

	private static Logger logger = LoggerFactory.getLogger(PaiLieSan.class);
	@Resource
	private PrizeInfoService prizeInfoService;

	@Resource
	private StatisticsDataService statisticsDataService;

	@Autowired
	MemcachedClient memcachedClient;

	@Override
	public void refresh(int countBatch) {
		logger.info("pailiesan refresh hotcold start,countBatch=" + countBatch);
		PaiLieSanXHC plsXHC = new PaiLieSanXHC();
		plsXHC.refresh(prizeInfoService, countBatch);

		StatisticsData data_pailiesanx = new StatisticsData(new StatisticsPK(
				PaiLieSanXHC.KEY, "T01002", countBatch + ""));
		data_pailiesanx.setValue(plsXHC.toJson());

		statisticsDataService.merge(data_pailiesanx);

		logger.info("pailiesan refresh hotcold end,countBatch=" + countBatch);

		try {
			logger.info("pailiesan hotcold try to put to cache start:T01002 countBatch="
					+ countBatch);
			memcachedClient.add(PaiLieSanXHC.KEY + "_" + countBatch, 864000,
					data_pailiesanx);

			logger.info("pailiesan hotcold try to put to cache end:T01002 countBatch="
					+ countBatch);
		} catch (Exception e) {
			logger.info("pailiesan hotcold try to put to cache err:T01002 countBatch="
					+ countBatch);
		}

	}
}
