package com.ruyicai.prizedata.missvalue.elevenduojin;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ruyicai.prizedata.domain.PrizeInfo;

public class ElevenDuoJinTest {

	private static ElevenDuoJin edj;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"META-INF\\spring\\applicationContext.xml","META-INF\\spring\\applicationContext-mq.xml","META-INF\\spring\\applicationContext-memcache.xml"});
		edj = (ElevenDuoJin) context.getBean("T01012MV");
	}

	@Test
	public void testOnPrize() {
//		PrizeInfo prizeInfo = new PrizeInfo();
//		prizeInfo.setWinbasecode("1006010203");
//		edj.onPrize(prizeInfo);
	}

	@Test
	public void testRefresh() {
//		edj.refresh(ElevenDuoJinRX.KEY);
//		edj.refresh(ElevenDuoJinQ1.KEY);
//		edj.refresh(ElevenDuoJinQ2.KEY);
//		edj.refresh(ElevenDuoJinQ3.KEY);
//		edj.refresh(ElevenDuoJinQ2Z.KEY);
//		edj.refresh(ElevenDuoJinQ3Z.KEY);
		
//		edj.refresh(ElevenDuoJinQ3ZH.KEY);
//		edj.refresh(ElevenDuoJinR5ZH.KEY);
//		edj.refresh(ElevenDuoJinR7ZH.KEY);
//		edj.refresh(ElevenDuoJinR8ZH.KEY);
	}

	@Test
	public void testRefreshAll() {
//		for(int i = 11110101;i<=11110178;i++) {
//			edj.refreshAll(i+"", "T01002");
//		}
	}

}
