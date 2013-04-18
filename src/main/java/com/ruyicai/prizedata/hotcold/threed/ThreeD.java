package com.ruyicai.prizedata.hotcold.threed;

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

@Service("F47103HC")
public class ThreeD implements LotTypeHotCold {

	private static Logger logger = LoggerFactory.getLogger(ThreeD.class);
	@Resource
	private PrizeInfoService prizeInfoService;

	@Resource
	private StatisticsDataService statisticsDataService;

	@Autowired
	MemcachedClient memcachedClient;

	@Override
	public void refresh(int countBatch) {
		logger.info("threed refresh hotcold start,countBatch=" + countBatch);
		ThreeDXHC trdXHC = new ThreeDXHC();
		trdXHC.refresh(prizeInfoService, countBatch);

		StatisticsData data_threedx = new StatisticsData(new StatisticsPK(
				ThreeDXHC.KEY, "F47103", countBatch + ""));
		data_threedx.setValue(trdXHC.toJson());

		statisticsDataService.merge(data_threedx);

		logger.info("threed refresh hotcold end,countBatch=" + countBatch);

		try {
			logger.info("threed hotcold try to put to cache start:F47103 countBatch="
					+ countBatch);
			memcachedClient.add(ThreeDXHC.KEY + "_" + countBatch, 864000,
					data_threedx);

			logger.info("threed hotcold try to put to cache end:F47103 countBatch="
					+ countBatch);
		} catch (Exception e) {
			logger.info("threed hotcold try to put to cache err:F47103 countBatch="
					+ countBatch);
		}

	}

}
