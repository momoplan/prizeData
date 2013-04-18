package com.ruyicai.prizedata.service;

import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ruyicai.prizedata.domain.PrizeInfo;
import com.ruyicai.prizedata.missvalue.shishicai.ShiShiCai2D;


public class PrizeInfoServiceTest {

	private static PrizeInfoService prizeInfoService;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml","applicationContext-mq.xml"});
		prizeInfoService = (PrizeInfoService) context.getBean("prizeInfoService");
	}
	
	@Test
	public void testPersist() {
//		for(int i = 0;i<=9;i++) {
//			PrizeInfo p = new PrizeInfo();
//			p.setLotno("T01007");
//			p.setBatchcode("201110"+i);
//			p.setWinbasecode(""+i+i+i+i+i);
//			p.setWinspecialcode("");
//			p.setCreatedate(new Date());
//			prizeInfoService.persist(p);
//		}
		
		
	}

	@Test
	public void testFind() {
//		List<PrizeInfo> list = prizeInfoService.find("T01001", 5, 5);
//		for(PrizeInfo p:list) {
//			System.out.println(p.getBatchcode());
//		}
	}
	

}
