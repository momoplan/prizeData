package com.ruyicai.prizedata.missvalue.daletou;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DaLeTouTest {

	private static DaLeTou daletou;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"META-INF\\spring\\applicationContext.xml","META-INF\\spring\\applicationContext-mq.xml","META-INF\\spring\\applicationContext-memcache.xml"});
		daletou = (DaLeTou) context.getBean("T01001MV");
	}
	
	
	@Test
	public void testRefresh() {
//		for(int i = 2009001;i<=2009153;i++) {
//			daletou.refreshAll(i+"", "T01001");
//		}
//		
//		for(int i = 2010001;i<=2010153;i++) {
//			daletou.refreshAll(i+"", "T01001");
//		}
//		
//		for(int i = 2011001;i<=2011126;i++) {
//			daletou.refreshAll(i+"", "T01001");
//		}
	}
	
	
	@Test
	public void testRefreshOnPrize() {
		
	}
}
