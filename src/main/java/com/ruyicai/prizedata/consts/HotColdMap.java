package com.ruyicai.prizedata.consts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruyicai.prizedata.hotcold.daletou.DaLeTouXHC;
import com.ruyicai.prizedata.hotcold.five22.Five22XHC;
import com.ruyicai.prizedata.hotcold.pailiesan.PaiLieSanXHC;
import com.ruyicai.prizedata.hotcold.pailiewu.PaiLieWuXHC;
import com.ruyicai.prizedata.hotcold.qilecai.QiLeCaiXHC;
import com.ruyicai.prizedata.hotcold.qixingcai.QiXingCaiXHC;
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

	}
	
	
	public static List<String> getHotColdMapTypeList(String lotno) {
		if(map.containsKey(lotno)) {
			return map.get(lotno);
		}
		return null;
	}
}
