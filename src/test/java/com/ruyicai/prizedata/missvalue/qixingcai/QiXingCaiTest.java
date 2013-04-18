package com.ruyicai.prizedata.missvalue.qixingcai;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ruyicai.prizedata.missvalue.daletou.DaLeTou;

public class QiXingCaiTest {

	private static QiXingCai qixingcai;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"META-INF\\spring\\applicationContext.xml","META-INF\\spring\\applicationContext-mq.xml","META-INF\\spring\\applicationContext-memcache.xml"});
		qixingcai = (QiXingCai) context.getBean("T01009MV");
	}
	
	
	@Test
	public void testRefresh() {
		
//		for(int i = 2009001;i<=2009153;i++) {
//			qixingcai.refreshAll(i+"", "T01009");
//		}
//		
//		for(int i = 2010001;i<=2010154;i++) {
//			qixingcai.refreshAll(i+"", "T01009");
//		}
//		
//		for(int i = 2011001;i<=2011125;i++) {
//			qixingcai.refreshAll(i+"", "T01009");
//		}
	}
}
