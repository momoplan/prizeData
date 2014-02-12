package com.ruyicai.prizedata.jms.listener;

import java.util.Map;

import javax.annotation.Resource;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.activemq.command.ActiveMQMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ruyicai.prizedata.domain.PrizeInfo;
import com.ruyicai.prizedata.hotcold.HotColdService;
import com.ruyicai.prizedata.missvalue.MissValueService;

@Service("onPrizeListener")
public class OnPrizeListener implements MessageListener{
	private static Logger logger = LoggerFactory.getLogger(OnPrizeListener.class);
	
	@Resource
	private MissValueService missValueService;
	
	@Resource
	private HotColdService hotColdService;

	@Override
	public void onMessage(Message message) {
		try {
			logger.info("reciveTopic");
			Map<String,Object> map = ((ActiveMQMessage)message).getProperties();
			PrizeInfo prizeInfo = new PrizeInfo();
			prizeInfo.setLotno((String)map.get("lotno"));
			prizeInfo.setBatchcode((String)map.get("batchcode"));
			prizeInfo.setWinbasecode((String)map.get("wincode"));
			logger.info(prizeInfo.getLotno()+" "+prizeInfo.getBatchcode()+" "+prizeInfo.getWinbasecode()+" "+prizeInfo.getWinspecialcode());
			missValueService.onPrize(prizeInfo);
			hotColdService.refresh((String)map.get("lotno"),100);
			
			
			missValueService.latestCacheOnPrize("F47104", "F47104MV_X", 10, 43200);
		} catch (Exception e) {
			logger.info("OnPrizeListener err",e);
		}
		
	}

	
}
