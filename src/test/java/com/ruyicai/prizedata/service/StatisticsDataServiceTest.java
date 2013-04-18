package com.ruyicai.prizedata.service;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ruyicai.prizedata.domain.StatisticsData;

public class StatisticsDataServiceTest {

	private static StatisticsDataService statisticsDataService;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml","applicationContext-mq.xml"});
		statisticsDataService = (StatisticsDataService) context.getBean("statisticsDataService");
	}

	@Test
	public void testPersist() {
//		StatisticsData statisticsData = new StatisticsData();
//		statisticsData.setName("xiong");
//		statisticsData.setValue("1234567890");
//		statisticsDataService.persist(statisticsData);
	}

	@Test
	public void testFind() {
//		StatisticsData statisticsData = statisticsDataService.find("xiong");
//		System.out.println(statisticsData.getName());
//		System.out.println(statisticsData.getValue());
	}

}
