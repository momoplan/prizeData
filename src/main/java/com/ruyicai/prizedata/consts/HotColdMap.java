package com.ruyicai.prizedata.consts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruyicai.prizedata.hotcold.chongqing11c5.Chongqing11c5Q1HC;
import com.ruyicai.prizedata.hotcold.chongqing11c5.Chongqing11c5Q2HC;
import com.ruyicai.prizedata.hotcold.chongqing11c5.Chongqing11c5Q2ZHC;
import com.ruyicai.prizedata.hotcold.chongqing11c5.Chongqing11c5Q3HC;
import com.ruyicai.prizedata.hotcold.chongqing11c5.Chongqing11c5Q3ZHC;
import com.ruyicai.prizedata.hotcold.chongqing11c5.Chongqing11c5RXHC;
import com.ruyicai.prizedata.hotcold.cqhappy10.CqHappytenH1HC;
import com.ruyicai.prizedata.hotcold.cqhappy10.CqHappytenQ3HC;
import com.ruyicai.prizedata.hotcold.cqhappy10.CqHappytenRXHC;
import com.ruyicai.prizedata.hotcold.cqhappy10.CqHappytenS1HC;
import com.ruyicai.prizedata.hotcold.daletou.DaLeTouXHC;
import com.ruyicai.prizedata.hotcold.five22.Five22XHC;
import com.ruyicai.prizedata.hotcold.gdhappy10.GdHappytenH1HC;
import com.ruyicai.prizedata.hotcold.gdhappy10.GdHappytenQ3HC;
import com.ruyicai.prizedata.hotcold.gdhappy10.GdHappytenRXHC;
import com.ruyicai.prizedata.hotcold.gdhappy10.GdHappytenS1HC;
import com.ruyicai.prizedata.hotcold.guangdong11c5.Guangdong11c5Q1HC;
import com.ruyicai.prizedata.hotcold.guangdong11c5.Guangdong11c5Q2HC;
import com.ruyicai.prizedata.hotcold.guangdong11c5.Guangdong11c5Q2ZHC;
import com.ruyicai.prizedata.hotcold.guangdong11c5.Guangdong11c5Q3HC;
import com.ruyicai.prizedata.hotcold.guangdong11c5.Guangdong11c5Q3ZHC;
import com.ruyicai.prizedata.hotcold.guangdong11c5.Guangdong11c5RXHC;
import com.ruyicai.prizedata.hotcold.jiangxi11c5.Jiangxi11c5Q1HC;
import com.ruyicai.prizedata.hotcold.jiangxi11c5.Jiangxi11c5Q2HC;
import com.ruyicai.prizedata.hotcold.jiangxi11c5.Jiangxi11c5Q2ZHC;
import com.ruyicai.prizedata.hotcold.jiangxi11c5.Jiangxi11c5Q3HC;
import com.ruyicai.prizedata.hotcold.jiangxi11c5.Jiangxi11c5Q3ZHC;
import com.ruyicai.prizedata.hotcold.jiangxi11c5.Jiangxi11c5RXHC;
import com.ruyicai.prizedata.hotcold.jilinkuai3.JilinK301HC;
import com.ruyicai.prizedata.hotcold.jilinkuai3.JilinK302HC;
import com.ruyicai.prizedata.hotcold.jilinkuai3.JilinK310HC;
import com.ruyicai.prizedata.hotcold.jilinkuai3.JilinK330HC;
import com.ruyicai.prizedata.hotcold.jilinkuai3.JilinK340HC;
import com.ruyicai.prizedata.hotcold.jilinkuai3.JilinK350HC;
import com.ruyicai.prizedata.hotcold.jilinkuai3.JilinK3BaseHC;
import com.ruyicai.prizedata.hotcold.jilinkuai3.JilinK3XHC;
import com.ruyicai.prizedata.hotcold.nmkuai3.NmK301HC;
import com.ruyicai.prizedata.hotcold.nmkuai3.NmK302HC;
import com.ruyicai.prizedata.hotcold.nmkuai3.NmK310HC;
import com.ruyicai.prizedata.hotcold.nmkuai3.NmK330HC;
import com.ruyicai.prizedata.hotcold.nmkuai3.NmK340HC;
import com.ruyicai.prizedata.hotcold.nmkuai3.NmK350HC;
import com.ruyicai.prizedata.hotcold.nmkuai3.NmK3BaseHC;
import com.ruyicai.prizedata.hotcold.nmkuai3.NmK3XHC;
import com.ruyicai.prizedata.hotcold.pailiesan.PaiLieSanXHC;
import com.ruyicai.prizedata.hotcold.pailiewu.PaiLieWuXHC;
import com.ruyicai.prizedata.hotcold.qilecai.QiLeCaiXHC;
import com.ruyicai.prizedata.hotcold.qixingcai.QiXingCaiXHC;
import com.ruyicai.prizedata.hotcold.shandong11c5.Shandong11c5Q1HC;
import com.ruyicai.prizedata.hotcold.shandong11c5.Shandong11c5Q2HC;
import com.ruyicai.prizedata.hotcold.shandong11c5.Shandong11c5Q2ZHC;
import com.ruyicai.prizedata.hotcold.shandong11c5.Shandong11c5Q3HC;
import com.ruyicai.prizedata.hotcold.shandong11c5.Shandong11c5Q3ZHC;
import com.ruyicai.prizedata.hotcold.shandong11c5.Shandong11c5RXHC;
import com.ruyicai.prizedata.hotcold.shuangseqiu.ShuangSeQiuXHC;
import com.ruyicai.prizedata.hotcold.threed.ThreeDXHC;

public class HotColdMap {

	private static Map<String,List<String>> map = new HashMap<String,List<String>>();
	
	static {
		
		List<String> shuangseqiu = new ArrayList<String>();
		shuangseqiu.add(ShuangSeQiuXHC.KEY);
		map.put("F47104", shuangseqiu);
		
		List<String> threed = new ArrayList<String>();
		threed.add(ThreeDXHC.KEY);
		map.put("F47103", threed);
		
		List<String> qilecai = new ArrayList<String>();
		qilecai.add(QiLeCaiXHC.KEY);
		map.put("F47102", qilecai);
		
		List<String> daletou = new ArrayList<String>();
		daletou.add(DaLeTouXHC.KEY);
		map.put("T01001", daletou);
		
		List<String> pailiesan = new ArrayList<String>();
		pailiesan.add(PaiLieSanXHC.KEY);
		map.put("T01002", pailiesan);
		
		List<String> pailiewu = new ArrayList<String>();
		pailiewu.add(PaiLieWuXHC.KEY);
		map.put("T01011", pailiewu);
		
		List<String> qixingcai = new ArrayList<String>();
		qixingcai.add(QiXingCaiXHC.KEY);
		map.put("T01009", qixingcai);
		
		List<String> five22 = new ArrayList<String>();
		five22.add(Five22XHC.KEY);
		map.put("T01013", five22);
		
		
		List<String> nmk3 = new ArrayList<String>();
		nmk3.add(NmK301HC.KEY);
		nmk3.add(NmK302HC.KEY);
		nmk3.add(NmK310HC.KEY);
		nmk3.add(NmK330HC.KEY);
		nmk3.add(NmK340HC.KEY);
		nmk3.add(NmK350HC.KEY);
		nmk3.add(NmK3BaseHC.KEY);
		nmk3.add(NmK3XHC.KEY);
		map.put("F47107", nmk3);
		
		
		List<String> gdhappy10 = new ArrayList<String>();
		gdhappy10.add(GdHappytenH1HC.KEY);
		gdhappy10.add(GdHappytenQ3HC.KEY);
		gdhappy10.add(GdHappytenRXHC.KEY);
		gdhappy10.add(GdHappytenS1HC.KEY);
		map.put("T01015", gdhappy10);
		
		List<String> gd11c5 = new ArrayList<String>();
		gd11c5.add(Guangdong11c5Q1HC.KEY);
		gd11c5.add(Guangdong11c5Q2HC.KEY);
		gd11c5.add(Guangdong11c5Q3HC.KEY);
		gd11c5.add(Guangdong11c5Q2ZHC.KEY);
		gd11c5.add(Guangdong11c5Q3ZHC.KEY);
		gd11c5.add(Guangdong11c5RXHC.KEY);
		map.put("T01014", gd11c5);
		
		List<String> jx11c5 = new ArrayList<String>();
		jx11c5.add(Jiangxi11c5Q1HC.KEY);
		jx11c5.add(Jiangxi11c5Q2HC.KEY);
		jx11c5.add(Jiangxi11c5Q3HC.KEY);
		jx11c5.add(Jiangxi11c5Q2ZHC.KEY);
		jx11c5.add(Jiangxi11c5Q3ZHC.KEY);
		jx11c5.add(Jiangxi11c5RXHC.KEY);
		map.put("T01010", jx11c5);
		
		List<String> sd11c5 = new ArrayList<String>();
		sd11c5.add(Shandong11c5Q1HC.KEY);
		sd11c5.add(Shandong11c5Q2HC.KEY);
		sd11c5.add(Shandong11c5Q3HC.KEY);
		sd11c5.add(Shandong11c5Q2ZHC.KEY);
		sd11c5.add(Shandong11c5Q3ZHC.KEY);
		sd11c5.add(Shandong11c5RXHC.KEY);
		map.put("T01012", sd11c5);
		
		List<String> cq11c5 = new ArrayList<String>();
		cq11c5.add(Chongqing11c5Q1HC.KEY);
		cq11c5.add(Chongqing11c5Q2HC.KEY);
		cq11c5.add(Chongqing11c5Q3HC.KEY);
		cq11c5.add(Chongqing11c5Q2ZHC.KEY);
		cq11c5.add(Chongqing11c5Q3ZHC.KEY);
		cq11c5.add(Chongqing11c5RXHC.KEY);
		map.put("T01016", cq11c5);
		
		
		List<String> jilink3 = new ArrayList<String>();
		jilink3.add(JilinK301HC.KEY);
		jilink3.add(JilinK302HC.KEY);
		jilink3.add(JilinK310HC.KEY);
		jilink3.add(JilinK330HC.KEY);
		jilink3.add(JilinK340HC.KEY);
		jilink3.add(JilinK350HC.KEY);
		jilink3.add(JilinK3BaseHC.KEY);
		jilink3.add(JilinK3XHC.KEY);
		map.put("F47108", jilink3);
		
		List<String> cqhappy10 = new ArrayList<String>();
		cqhappy10.add(CqHappytenH1HC.KEY);
		cqhappy10.add(CqHappytenQ3HC.KEY);
		cqhappy10.add(CqHappytenRXHC.KEY);
		cqhappy10.add(CqHappytenS1HC.KEY);
		map.put("F47109", cqhappy10);

	}
	
	
	public static List<String> getHotColdMapTypeList(String lotno) {
		if(map.containsKey(lotno)) {
			return map.get(lotno);
		}
		return null;
	}
}
