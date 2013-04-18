package com.ruyicai.prizedata.lottype.shishicai;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ruyicai.prizedata.domain.PrizeInfo;
import com.ruyicai.prizedata.missvalue.shishicai.ShiShiCai;
import com.ruyicai.prizedata.missvalue.shishicai.ShiShiCai2D;
import com.ruyicai.prizedata.missvalue.shishicai.ShiShiCai2Z;
import com.ruyicai.prizedata.missvalue.shishicai.ShiShiCai3D;
import com.ruyicai.prizedata.missvalue.shishicai.ShiShiCai3Z;
import com.ruyicai.prizedata.missvalue.shishicai.ShiShiCai5X;
import com.ruyicai.prizedata.missvalue.shishicai.ShiShiCai6Z;
import com.ruyicai.prizedata.missvalue.shishicai.ShiShiCaiDD;

public class ShiShiCaiTest {

	private static ShiShiCai shishicai;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"META-INF\\spring\\applicationContext.xml","META-INF\\spring\\applicationContext-mq.xml","META-INF\\spring\\applicationContext-memcache.xml"});
		shishicai = (ShiShiCai) context.getBean("T01007MV");
	}

	@Test
	public void testOnPrize() {
//		PrizeInfo prizeInfo = new PrizeInfo();
//		prizeInfo.setWinbasecode("12345");
//		shishicai.onPrize(prizeInfo);
	}

	@Test
	public void testRefresh() {
//		for(int i = 111021111;i<=111021120;i++) {
//			shishicai.refreshAll(i+"", "T01007");
//		}
	}

	@Test
	public void testRefreshAll() {
//		ShiShiCai2D d2 = ShiShiCai2D.fromJsonToShiShiCai2D("{\"result\":{\"66\":3,\"77\":2,\"44\":5,\"55\":4,\"22\":7,\"33\":6,\"00\":9,\"99\":0,\"11\":8,\"88\":1}}");
//		System.out.println("aa");
	}

}
