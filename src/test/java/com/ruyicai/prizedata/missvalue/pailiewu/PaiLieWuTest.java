package com.ruyicai.prizedata.missvalue.pailiewu;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PaiLieWuTest {

	private static PaiLieWu pailiewu;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"META-INF\\spring\\applicationContext.xml","META-INF\\spring\\applicationContext-mq.xml","META-INF\\spring\\applicationContext-memcache.xml"});
		pailiewu = (PaiLieWu) context.getBean("T01011MV");
	}
	
	
	
	@Test
	public void testRefresh() {
		
//		for(int i = 2009001;i<=2009358;i++) {
//			pailiewu.refreshAll(i+"", "T01011");
//		}
//		
//		for(int i = 2010001;i<=2010358;i++) {
//			pailiewu.refreshAll(i+"", "T01011");
//		}
//		
//		for(int i = 2011001;i<=2011293;i++) {
//			pailiewu.refreshAll(i+"", "T01011");
//		}
	}
}
