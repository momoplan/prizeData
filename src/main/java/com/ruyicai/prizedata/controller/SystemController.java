package com.ruyicai.prizedata.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruyicai.prizedata.consts.ErrorCode;
import com.ruyicai.prizedata.domain.PrizeInfo;
import com.ruyicai.prizedata.domain.StatisticsData;
import com.ruyicai.prizedata.hotcold.HotColdService;
import com.ruyicai.prizedata.missvalue.MissValueService;
import com.ruyicai.prizedata.service.PrizeInfoService;

@Controller
@RequestMapping("/system")
public class SystemController {

	private static Logger logger = LoggerFactory.getLogger(SystemController.class);
	@Resource
	private MissValueService missValueService;
	
	@Resource
	private PrizeInfoService prizeInfoService;
	
	@Resource
	private HotColdService hotColdService;
	
	@RequestMapping(value="/goMissvalue")
	public String goMissvalue() {
		return "missvalue/main";
	}
	
	
	@RequestMapping(value="/goBetPartition")
	public String goBetPartition() {
		return "betpartition/main";
	}
	
	
	@RequestMapping(value="/refresh",method=RequestMethod.POST)
	public @ResponseBody
	ResponseData refresh(@RequestParam("key") String key,@RequestParam("lotno") String lotno,@RequestParam("batchcode") String batchcode) {
		ResponseData rd = new ResponseData();
		try{
			rd.setErrorCode(ErrorCode.OK.value);
			missValueService.refresh(lotno,key,batchcode);
		}catch(Exception e) {
			rd.setErrorCode(ErrorCode.ERROR.value);
			logger.info("SystemController err", e);
		}
		return rd;
	}
	
	
	/**
	 * 刷新冷热号
	 * @param lotno 彩种
	 * @param countBatch 刷新的期数
	 * @return
	 */
	@RequestMapping(value="/refreshHC",method=RequestMethod.POST)
	public @ResponseBody
	ResponseData refresh(@RequestParam("lotno") String lotno,@RequestParam("countBatch") int countBatch) {
		ResponseData rd = new ResponseData();
		try{
			rd.setErrorCode(ErrorCode.OK.value);
			hotColdService.refresh(lotno,countBatch);
		}catch(Exception e) {
			rd.setErrorCode(ErrorCode.ERROR.value);
			logger.info("SystemController err", e);
		}
		return rd;
	}
	
	
	
	@RequestMapping(value="/refreshByOnPrize",method=RequestMethod.POST)
	public @ResponseBody
	ResponseData refreshByOnPrize(@RequestParam("lotno") String lotno,@RequestParam("batchcode") String batchcode,@RequestParam("prebatchcode") String prebatchcode) {
		ResponseData rd = new ResponseData();
		try{
			rd.setErrorCode(ErrorCode.OK.value);
			missValueService.refreshOnPrize(lotno, batchcode, prebatchcode);
		}catch(Exception e) {
			rd.setErrorCode(ErrorCode.ERROR.value);
			logger.info("SystemController err", e);
		}
		return rd;
	}
	
	
	
	@RequestMapping(value="/onPrize",method=RequestMethod.POST)
	public @ResponseBody
	ResponseData onPrize(@RequestParam("lotno") String lotno,@RequestParam("batchcode") String batchcode,@RequestParam("win1") String win1,@RequestParam("win2") String win2) {
		ResponseData rd = new ResponseData();
		try{
			rd.setErrorCode(ErrorCode.OK.value);
			PrizeInfo prizeInfo = new PrizeInfo();
			prizeInfo.setLotno(lotno);
			prizeInfo.setBatchcode(batchcode);
			prizeInfo.setWinbasecode(win1);
			prizeInfo.setWinspecialcode(win2);
			prizeInfo.setCreatedate(new Date());
			missValueService.onPrize(prizeInfo);
		}catch(Exception e) {
			rd.setErrorCode(ErrorCode.ERROR.value);
			logger.info("SystemController err", e);
		}
		return rd;
	}
	
	
	@RequestMapping(value="/addPrizeInfo",method=RequestMethod.POST)
	public @ResponseBody
	ResponseData addPrizeInfo(@RequestParam("lotno") String lotno,@RequestParam("batchcode") String batchcode,@RequestParam("win1") String win1,@RequestParam("win2") String win2) {
		ResponseData rd = new ResponseData();
		try{
			rd.setErrorCode(ErrorCode.OK.value);
			PrizeInfo prizeInfo = new PrizeInfo();
			prizeInfo.setLotno(lotno);
			prizeInfo.setBatchcode(batchcode);
			prizeInfo.setWinbasecode(win1);
			prizeInfo.setWinspecialcode(win2);
			prizeInfo.setCreatedate(new Date());
			prizeInfoService.merge(prizeInfo);
		}catch(Exception e) {
			rd.setErrorCode(ErrorCode.ERROR.value);
			logger.info("SystemController err", e);
		}
		return rd;
	}
	
	
	@RequestMapping(value="/latestCacheOnPrize",method=RequestMethod.POST)
	public @ResponseBody
	ResponseData latestCacheOnPrize(@RequestParam("lotno") String lotno,@RequestParam("key") String key,
			@RequestParam("count") int count) {
		ResponseData rd = new ResponseData();
		try{
			rd.setErrorCode(ErrorCode.OK.value);
			List<StatisticsData> latestCacheOnPrize = missValueService.latestCacheOnPrize(lotno, key, count, 1800);
			rd.setValue(latestCacheOnPrize);
		}catch(Exception e) {
			rd.setErrorCode(ErrorCode.ERROR.value);
			logger.info("SystemController err", e);
		}
		return rd;
	}
}
