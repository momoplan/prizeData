package com.ruyicai.prizedata.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruyicai.prizedata.consts.ErrorCode;
import com.ruyicai.prizedata.consts.HotColdMap;
import com.ruyicai.prizedata.consts.MissValueMap;
import com.ruyicai.prizedata.domain.StatisticsData;
import com.ruyicai.prizedata.missvalue.MissValueService;
import com.ruyicai.prizedata.service.StatisticsDataService;

@Controller
@RequestMapping("/select")
public class StatisticsDataController {

	private static Logger logger = LoggerFactory.getLogger(StatisticsDataController.class);
	
	@Resource
	private StatisticsDataService statisticsDataService;
	
	@Resource
	private MissValueService missValueService;
	
	
	@RequestMapping(value="/missvalue",method=RequestMethod.GET)
	public @ResponseBody
	ResponseData selectMissValue(@RequestParam("key") String key) {
		logger.info("select missvalue:key="+key);
		ResponseData rd = new ResponseData();
		try{
			StatisticsData sdata = statisticsDataService.findThroughCache(key);
			rd.setErrorCode(ErrorCode.OK.value);
			rd.setValue(sdata.getValue());
		}catch (Exception e){
			rd.setErrorCode(ErrorCode.ERROR.value);
			logger.info("StatisticsDataController missvalue err", e);
		}
		return rd;
	}
	
	
	@RequestMapping(value = "/missvalue2nd", method = RequestMethod.GET)
	public @ResponseBody
	ResponseData selectMissValue2nd(@RequestParam("lotno") String lotno,
			@RequestParam("key") String key,
			@RequestParam("batchcode") String batchcode) {
		logger.info("select missvalue2nd:key="+key+" lotno:"+lotno+" batchcode:"+batchcode);
		ResponseData rd = new ResponseData();
		try {
			StatisticsData sdata = statisticsDataService
					.findByLotnoAndBatchcodeAndKeyThroughCache(lotno,
							batchcode, key);
			if (sdata != null) {
				rd.setErrorCode(ErrorCode.OK.value);
				rd.setValue(sdata.getValue());
			} else {
				rd.setErrorCode(ErrorCode.ERROR.value);
			}

		} catch (Exception e) {

			logger.info("StatisticsDataController missvalue2nd err", e);
		}
		return rd;
	}
	
	
	
	
	@RequestMapping(value = "/missvalue2ndSimple", method = RequestMethod.GET)
	public @ResponseBody
	ResponseData selectMissValue2ndSimple(@RequestParam("lotno") String lotno,
			@RequestParam("key") String key,
			@RequestParam("batchcode") String batchcode) {
		logger.info("select missvalue2ndSimple:key="+key+" lotno:"+lotno+" batchcode:"+batchcode);
		ResponseData rd = new ResponseData();
		try {
			StatisticsData sdata = statisticsDataService
					.findByLotnoAndBatchcodeAndKeyThroughCache(lotno,
							batchcode, key);
			if (sdata != null) {
				rd.setErrorCode(ErrorCode.OK.value);
				rd.setValue(sdata.getValueString());
			} else {
				rd.setErrorCode(ErrorCode.ERROR.value);
			}

		} catch (Exception e) {

			logger.info("StatisticsDataController missvalue2ndSimple err", e);
		}
		return rd;
	}
	
	
	
	@RequestMapping(value = "/missvalue2ndList", method = RequestMethod.GET)
	public @ResponseBody
	ResponseData selectMissValue2ndList(@RequestParam("lotno") String lotno,
			@RequestParam("batchcode") String batchcode) {
		logger.info("select missvalue2ndList:lotno"+lotno+" batchcode:"+batchcode);
		ResponseData rd = new ResponseData();
		List<String> types = MissValueMap.getMissValueTypeList(lotno);
		if(types==null) {
			rd.setErrorCode(ErrorCode.ERROR.value);
			return rd;
		}
		
		try {
			Map<String,String> map = new HashMap<String,String>();
			for(String key:types) {
				StatisticsData sdata = statisticsDataService
									.findByLotnoAndBatchcodeAndKeyThroughCache(lotno,batchcode, key);
				if (sdata != null) {
					map.put(key, sdata.getValue());
				}
			}
			rd.setErrorCode(ErrorCode.OK.value);
			rd.setValue(map);
		} catch (Exception e) {
			rd.setErrorCode(ErrorCode.ERROR.value);
			logger.info("StatisticsDataController missvalue2ndList err", e);
		}
		return rd;
	}
	
	
	
	@RequestMapping(value = "/hotcoldList")
	public @ResponseBody
	ResponseData selectHotColdList(@RequestParam("lotno") String lotno,
			@RequestParam("countBatch") String countBatch) {
		logger.info("select hotcoldList:lotno"+lotno+" countBatch:"+countBatch);
		ResponseData rd = new ResponseData();
		List<String> types = HotColdMap.getHotColdMapTypeList(lotno);
		if(types==null) {
			rd.setErrorCode(ErrorCode.ERROR.value);
			return rd;
		}
		
		try {
			Map<String,String> map = new HashMap<String,String>();
			for(String key:types) {
				StatisticsData sdata = statisticsDataService
									.findByLotnoAndBatchcodeAndKeyThroughCache(lotno,countBatch, key);
				if (sdata != null) {
					map.put(key, sdata.getValue());
				}
			}
			rd.setErrorCode(ErrorCode.OK.value);
			rd.setValue(map);
		} catch (Exception e) {
			rd.setErrorCode(ErrorCode.ERROR.value);
			logger.info("StatisticsDataController hotcoldList err", e);
		}
		return rd;
		
	}
	
	
	
	
	@RequestMapping(value = "/hotcold")
	public @ResponseBody
	ResponseData selectHotCold(@RequestParam("lotno") String lotno,
			@RequestParam("countBatch") String countBatch,@RequestParam("key") String key) {
		logger.info("select hotcold:lotno"+lotno+" countBatch:"+countBatch);
		ResponseData rd = new ResponseData();
		
		try {
				StatisticsData sdata = statisticsDataService
									.findByLotnoAndBatchcodeAndKeyThroughCache(lotno,countBatch, key);
			rd.setErrorCode(ErrorCode.OK.value);
			rd.setValue(sdata.getValue());
		} catch (Exception e) {
			rd.setErrorCode(ErrorCode.ERROR.value);
			logger.info("StatisticsDataController hotcoldList err", e);
		}
		return rd;
		
	}
	
	
	
	@RequestMapping(value = "/latestCache")
	public @ResponseBody
	ResponseData latestCache(@RequestParam("lotno") String lotno,
			@RequestParam("key") String key,@RequestParam("count") int count) {
		logger.info("select latestCache:lotno"+lotno+" key:"+key+ " count:"+count);
		ResponseData rd = new ResponseData();
		
		try {
			List<StatisticsData> latestCache = missValueService.getLatestCache(lotno, key, count, 1800);
			rd.setErrorCode(ErrorCode.OK.value);
			rd.setValue(latestCache);
		} catch (Exception e) {
			rd.setErrorCode(ErrorCode.ERROR.value);
			logger.info("StatisticsDataController latestCache err", e);
		}
		return rd;
		
	}
	
	/**
	 * 
	 * @param lotno
	 * @param key 按照分号分割
	 * @param batchcode
	 * @return
	 */
	@RequestMapping(value = "/missvalueByKeys", method = RequestMethod.GET)
	public @ResponseBody
	ResponseData missvalueByKeys(@RequestParam("lotno") String lotno,
			@RequestParam("keys") String keys,
			@RequestParam("batchcode") String batchcode) {
		logger.info("select missvalueByKeys:keys="+keys+" lotno:"+lotno+" batchcode:"+batchcode);
		ResponseData rd = new ResponseData();
		try {
			Map<String,String> map = new HashMap<String,String>();
			for(String key:keys.split(";")) {
				StatisticsData sdata = statisticsDataService.findByLotnoAndBatchcodeAndKeyThroughCache(lotno,batchcode, key);
				if (sdata != null) {
					map.put(key, sdata.getValue());
				}
			}
			rd.setErrorCode(ErrorCode.OK.value);
			rd.setValue(map);

		} catch (Exception e) {

			logger.info("StatisticsDataController missvalueByKeys err", e);
		}
		return rd;
	}
	
	
}
