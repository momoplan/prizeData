package com.ruyicai.prizedata.missvalue.shuangseqiu;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ruyicai.prizedata.domain.PrizeInfo;

public class ShuangSeQiuTest {

	private static ShuangSeQiu ssq;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"META-INF\\spring\\applicationContext.xml","META-INF\\spring\\applicationContext-mq.xml","META-INF\\spring\\applicationContext-memcache.xml"});
		ssq = (ShuangSeQiu) context.getBean("F47104MV");
	}

	@Test
	public void testOnPrize() {
//		PrizeInfo prizeInfo = new PrizeInfo();
//		prizeInfo.setWinbasecode("010203040506");
//		prizeInfo.setWinspecialcode("16");
//		ssq.onPrize(prizeInfo);
	}

	@Test
	public void testRefresh() {
		
//		for(int i = 2011076;i<=2011126;i++) {
//			ssq.refreshAll(i+"", "F47104");
//		}
	}

	@Test
	public void testRefreshAll() {
//		ssq.refreshAll();
	}

}
