package com.ruyicai.prizedata.hotcold;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("hotColdService")
public class HotColdService {

	private static Logger logger = LoggerFactory.getLogger(HotColdService.class);
	
	@Autowired
	private Map<String,LotTypeHotCold> map;
	
	
	public void refresh(String lotno,int countBatch) {
		
		LotTypeHotCold type = map.get(lotno+"HC");
		if(type!=null) {
			type.refresh(countBatch);
		}else {
			logger.info("not fund lottype:"+lotno);
		}
		
		
	}
	
}
