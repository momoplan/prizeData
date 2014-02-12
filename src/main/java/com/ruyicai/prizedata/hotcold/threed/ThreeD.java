package com.ruyicai.prizedata.hotcold.threed;

import java.util.List;

import javax.annotation.Resource;

import net.rubyeye.xmemcached.MemcachedClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruyicai.prizedata.domain.PrizeInfo;
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
		logger.info("threed refresh hotcold start,allcountBatch=" + countBatch);
		List<PrizeInfo> prizeInfos = prizeInfoService.find("F47103", 0, countBatch);
		
		List<PrizeInfo> prizeInfos10 = prizeInfos.subList(0, 10);
		List<PrizeInfo> prizeInfos30 = prizeInfos.subList(0, 30);
		List<PrizeInfo> prizeInfos50 = prizeInfos.subList(0, 50);
		
		refreshCount(10, prizeInfos10);
		refreshCount(30, prizeInfos30);
		refreshCount(50, prizeInfos50);

		logger.info("threed refresh hotcold end,allcountBatch=" + countBatch);
	}

	private void refreshCount(int countBatch, List<PrizeInfo> prizeInfos) {
		
		logger.info("threed refresh hotcold start,countBatch=" + countBatch);
		ThreeDXHC trdXHC = new ThreeDXHC();
		trdXHC.refresh(prizeInfos, countBatch);

		StatisticsData data_threedx = new StatisticsData(new StatisticsPK(
				ThreeDXHC.KEY, "F47103", countBatch + ""));
		data_threedx.setValue(trdXHC.toJson());

		statisticsDataService.merge(data_threedx);

		logger.info("threed refresh hotcold end,countBatch=" + countBatch);

		try {
			logger.info("threed hotcold try to put to cache start:F47103 countBatch="
					+ countBatch);
			memcachedClient.replace(ThreeDXHC.KEY + "_" + countBatch, 864000,
					data_threedx);

			logger.info("threed hotcold try to put to cache end:F47103 countBatch="
					+ countBatch);
		} catch (Exception e) {
			logger.info("threed hotcold try to put to cache err:F47103 countBatch="
					+ countBatch);
		}
	}

}
