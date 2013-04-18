package com.ruyicai.prizedata.missvalue.pailiesan;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ruyicai.prizedata.missvalue.qixingcai.QiXingCai;

public class PaiLieSanTest {

	private static PaiLieSan pailiesan;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"META-INF\\spring\\applicationContext.xml","META-INF\\spring\\applicationContext-mq.xml","META-INF\\spring\\applicationContext-memcache.xml"});
		pailiesan = (PaiLieSan) context.getBean("T01002MV");
	}
	
	@Test
	public void testRefresh() {
		
		
//		for(int i = 2009001;i<=2009358;i++) {
//			pailiesan.refreshAll(i+"", "T01002");
//		}
//		
//		for(int i = 2010001;i<=2010358;i++) {
//			pailiesan.refreshAll(i+"", "T01002");
//		}
//		
//		for(int i = 2011001;i<=2011293;i++) {
//			pailiesan.refreshAll(i+"", "T01002");
//		}
	}
}
