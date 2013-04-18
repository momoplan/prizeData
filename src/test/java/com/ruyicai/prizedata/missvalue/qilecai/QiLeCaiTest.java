package com.ruyicai.prizedata.missvalue.qilecai;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ruyicai.prizedata.domain.PrizeInfo;

public class QiLeCaiTest {

	private static QiLeCai qlc;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"META-INF\\spring\\applicationContext.xml","META-INF\\spring\\applicationContext-mq.xml","META-INF\\spring\\applicationContext-memcache.xml"});
		qlc = (QiLeCai) context.getBean("F47102MV");
	}

	@Test
	public void testOnPrize() {
//		PrizeInfo prizeInfo = new PrizeInfo();
//		prizeInfo.setWinbasecode("01020304050607");
//		qlc.onPrize(prizeInfo);
	}

	@Test
	public void testRefresh() {
		
//		for(int i = 2011076;i<=2011125;i++) {
//			qlc.refreshAll(i+"", "F47102");
//		}
	}

	@Test
	public void testRefreshAll() {
//		qlc.refreshAll();
	}

}
