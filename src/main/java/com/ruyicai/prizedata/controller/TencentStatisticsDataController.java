package com.ruyicai.prizedata.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruyicai.prizedata.consts.ErrorCode;
import com.ruyicai.prizedata.domain.StatisticsData;
import com.ruyicai.prizedata.service.StatisticsDataService;

@Controller
@RequestMapping("/tencent")
public class TencentStatisticsDataController {

	private static Logger logger = LoggerFactory
			.getLogger(TencentStatisticsDataController.class);
	@Resource
	private StatisticsDataService statisticsDataService;

	@RequestMapping(value = "/missvalue", method = RequestMethod.POST)
	public @ResponseBody
	ResponseData selectMissValue(@RequestParam("lotno") String lotno,
			@RequestParam(value="key",defaultValue="X")  String key,
			@RequestParam("batchcode") String batchcode) {
		ResponseData rd = new ResponseData();
		logger.info("====tencent==== missvalue:key="+key+" lotno:"+lotno+" batchcode"+batchcode);
		if("T01002".equals(lotno)&&"Z3".equals(key)) {
			key = "Z36";
		}else if("T01002".equals(lotno)&&"Z6".equals(key)) {
			key = "Z36";
		}else if("T01002".equals(lotno)&&"S3".equals(key)) {
			key = "Z36HZ";
		}else if("T01002".equals(lotno)&&"S6".equals(key)) {
			key = "Z36HZ";
		}else if("T01002".equals(lotno)&&"1".equals(key)) {
			key = "ZX";
		}else if("T01012".equals(lotno)&&"102".equals(key)) {
			key = "RX";
		}else if("T01012".equals(lotno)&&"103".equals(key)) {
			key = "RX";
		}else if("T01012".equals(lotno)&&"104".equals(key)) {
			key = "RX";
		}else if("T01012".equals(lotno)&&"105".equals(key)) {
			key = "RX";
		}else if("T01012".equals(lotno)&&"106".equals(key)) {
			key = "RX";
		}else if("T01012".equals(lotno)&&"107".equals(key)) {
			key = "RX";
		}else if("T01012".equals(lotno)&&"111".equals(key)) {
			key = "RX";
		}else if("T01012".equals(lotno)&&"112".equals(key)) {
			key = "RX";
		}else if("T01012".equals(lotno)&&"113".equals(key)) {
			key = "RX";
		}else if("T01012".equals(lotno)&&"114".equals(key)) {
			key = "RX";
		}else if("T01012".equals(lotno)&&"115".equals(key)) {
			key = "RX";
		}else if("T01012".equals(lotno)&&"116".equals(key)) {
			key = "RX";
		}else if("T01012".equals(lotno)&&"117".equals(key)) {
			key = "RX";
		}else if("T01012".equals(lotno)&&"141".equals(key)) {
			key = "Q2";
		}else if("T01012".equals(lotno)&&"142".equals(key)) {
			key = "Q2";
		}else if("T01012".equals(lotno)&&"144".equals(key)) {
			key = "Q2Z";
		}else if("T01012".equals(lotno)&&"161".equals(key)) {
			key = "Q3";
		}else if("T01012".equals(lotno)&&"162".equals(key)) {
			key = "Q3";
		}else if("T01012".equals(lotno)&&"164".equals(key)) {
			key = "Q3Z";
		}else if("T01012".equals(lotno)&&"108".equals(key)) {
			key = "Q2Z";
		}else if("T01012".equals(lotno)&&"109".equals(key)) {
			key = "Q3Z";
		}else if(("T01001").equals(lotno)) {
			key = "X";
		}else if(("T01009").equals(lotno)) {
			key = "ZX";
		}else if(("T01011").equals(lotno)) {
			key = "ZX";
		}else if(("T01013").equals(lotno)) {
			key = "X";
		}
			
			
		String new_key = lotno + "MV_" + key;	
			
			
			
			
		try {
			StatisticsData sdata = statisticsDataService
					.findByLotnoAndBatchcodeAndKeyThroughCache(lotno,
							batchcode, new_key);
			if (sdata != null) {
				rd.setErrorCode(ErrorCode.OK.value);
				rd.setValue(sdata.getValueString());
			} else {
				rd.setErrorCode(ErrorCode.ERROR.value);
			}

		} catch (Exception e) {

			logger.info("TencentStatisticsDataController err", e);
		}
		return rd;
	}

}
