package com.ruyicai.prizedata.missvalue.five22;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Five22Test {

	private static Five22 five22;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"META-INF\\spring\\applicationContext.xml","META-INF\\spring\\applicationContext-mq.xml","META-INF\\spring\\applicationContext-memcache.xml"});
		five22 = (Five22) context.getBean("T01013MV");
	}
	
	
	@Test
	public void testRefresh() {
//		five22.refreshAll("2011331", "T01013");
	}
}
