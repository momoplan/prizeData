package com.ruyicai.prizedata.consts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruyicai.prizedata.missvalue.daletou.DaLeTouNormal;
import com.ruyicai.prizedata.missvalue.duolecai.DuoLeCaiQ1;
import com.ruyicai.prizedata.missvalue.duolecai.DuoLeCaiQ2;
import com.ruyicai.prizedata.missvalue.duolecai.DuoLeCaiQ2Z;
import com.ruyicai.prizedata.missvalue.duolecai.DuoLeCaiQ3;
import com.ruyicai.prizedata.missvalue.duolecai.DuoLeCaiQ3Z;
import com.ruyicai.prizedata.missvalue.duolecai.DuoLeCaiQ3ZH;
import com.ruyicai.prizedata.missvalue.duolecai.DuoLeCaiR5ZH;
import com.ruyicai.prizedata.missvalue.duolecai.DuoLeCaiR7ZH;
import com.ruyicai.prizedata.missvalue.duolecai.DuoLeCaiR8ZH;
import com.ruyicai.prizedata.missvalue.duolecai.DuoLeCaiRX;
import com.ruyicai.prizedata.missvalue.elevenduojin.ElevenDuoJinQ1;
import com.ruyicai.prizedata.missvalue.elevenduojin.ElevenDuoJinQ2;
import com.ruyicai.prizedata.missvalue.elevenduojin.ElevenDuoJinQ2Z;
import com.ruyicai.prizedata.missvalue.elevenduojin.ElevenDuoJinQ3;
import com.ruyicai.prizedata.missvalue.elevenduojin.ElevenDuoJinQ3Z;
import com.ruyicai.prizedata.missvalue.elevenduojin.ElevenDuoJinQ3ZH;
import com.ruyicai.prizedata.missvalue.elevenduojin.ElevenDuoJinR5ZH;
import com.ruyicai.prizedata.missvalue.elevenduojin.ElevenDuoJinR7ZH;
import com.ruyicai.prizedata.missvalue.elevenduojin.ElevenDuoJinR8ZH;
import com.ruyicai.prizedata.missvalue.elevenduojin.ElevenDuoJinRX;
import com.ruyicai.prizedata.missvalue.gdhappyten.GdHappytenH1;
import com.ruyicai.prizedata.missvalue.gdhappyten.GdHappytenQ3;
import com.ruyicai.prizedata.missvalue.gdhappyten.GdHappytenRX;
import com.ruyicai.prizedata.missvalue.gdhappyten.GdHappytenS1;
import com.ruyicai.prizedata.missvalue.guangdong11c5.Guangdong11c5Q1;
import com.ruyicai.prizedata.missvalue.guangdong11c5.Guangdong11c5Q2;
import com.ruyicai.prizedata.missvalue.guangdong11c5.Guangdong11c5Q2Z;
import com.ruyicai.prizedata.missvalue.guangdong11c5.Guangdong11c5Q3;
import com.ruyicai.prizedata.missvalue.guangdong11c5.Guangdong11c5Q3Z;
import com.ruyicai.prizedata.missvalue.guangdong11c5.Guangdong11c5Q3ZH;
import com.ruyicai.prizedata.missvalue.guangdong11c5.Guangdong11c5R5ZH;
import com.ruyicai.prizedata.missvalue.guangdong11c5.Guangdong11c5R7ZH;
import com.ruyicai.prizedata.missvalue.guangdong11c5.Guangdong11c5R8ZH;
import com.ruyicai.prizedata.missvalue.guangdong11c5.Guangdong11c5RX;
import com.ruyicai.prizedata.missvalue.pailiesan.PaiLieSanZ36;
import com.ruyicai.prizedata.missvalue.pailiesan.PaiLieSanZ36HZ;
import com.ruyicai.prizedata.missvalue.pailiesan.PaiLieSanZX;
import com.ruyicai.prizedata.missvalue.pailiesan.PaiLieSanZXHZ;
import com.ruyicai.prizedata.missvalue.pailiewu.PaiLieWuZX;
import com.ruyicai.prizedata.missvalue.qilecai.QiLeCaiX;
import com.ruyicai.prizedata.missvalue.qixingcai.QiXingCaiZX;
import com.ruyicai.prizedata.missvalue.shishicai.ShiShiCai2D;
import com.ruyicai.prizedata.missvalue.shishicai.ShiShiCai2Z;
import com.ruyicai.prizedata.missvalue.shishicai.ShiShiCai2ZX;
import com.ruyicai.prizedata.missvalue.shishicai.ShiShiCai2ZXHZ;
import com.ruyicai.prizedata.missvalue.shishicai.ShiShiCai3D;
import com.ruyicai.prizedata.missvalue.shishicai.ShiShiCai3Z;
import com.ruyicai.prizedata.missvalue.shishicai.ShiShiCai5X;
import com.ruyicai.prizedata.missvalue.shishicai.ShiShiCai6Z;
import com.ruyicai.prizedata.missvalue.shishicai.ShiShiCaiDD;
import com.ruyicai.prizedata.missvalue.shuangseqiu.ShuangSeQiuX;
import com.ruyicai.prizedata.missvalue.threed.ThreeDZ36;
import com.ruyicai.prizedata.missvalue.threed.ThreeDZ36HZ;
import com.ruyicai.prizedata.missvalue.threed.ThreeDZX;
import com.ruyicai.prizedata.missvalue.threed.ThreeDZXHZ;

public class MissValueMap {

	private static Map<String,List<String>> map = new HashMap<String,List<String>>();
	
	static {
		List<String> daletou = new ArrayList<String>();
		daletou.add(DaLeTouNormal.KEY);
		map.put("T01001", daletou);
		
		List<String> elevenduojin = new ArrayList<String>();
		elevenduojin.add(ElevenDuoJinQ1.KEY);
		elevenduojin.add(ElevenDuoJinQ2.KEY);
		elevenduojin.add(ElevenDuoJinQ2Z.KEY);
		elevenduojin.add(ElevenDuoJinQ3.KEY);
		elevenduojin.add(ElevenDuoJinQ3Z.KEY);
		elevenduojin.add(ElevenDuoJinQ3ZH.KEY);
		elevenduojin.add(ElevenDuoJinR5ZH.KEY);
		elevenduojin.add(ElevenDuoJinR7ZH.KEY);
		elevenduojin.add(ElevenDuoJinR8ZH.KEY);
		elevenduojin.add(ElevenDuoJinRX.KEY);
		map.put("T01012", elevenduojin);
		
		List<String> pailiesan = new ArrayList<String>();
		pailiesan.add(PaiLieSanZ36.KEY);
		pailiesan.add(PaiLieSanZ36HZ.KEY);
		pailiesan.add(PaiLieSanZX.KEY);
		pailiesan.add(PaiLieSanZXHZ.KEY);
		map.put("T01002", pailiesan);
		
		List<String> pailiewu = new ArrayList<String>();
		pailiewu.add(PaiLieWuZX.KEY);
		map.put("T01011", pailiewu);
		
		List<String> qilecai = new ArrayList<String>();
		qilecai.add(QiLeCaiX.KEY);
		map.put("F47102", qilecai);
		
		List<String> qixingcai = new ArrayList<String>();
		qixingcai.add(QiXingCaiZX.KEY);
		map.put("T01009", qixingcai);
		
		
		List<String> shishicai = new ArrayList<String>();
		shishicai.add(ShiShiCai2D.KEY);
		shishicai.add(ShiShiCai2Z.KEY);
		shishicai.add(ShiShiCai3D.KEY);
		shishicai.add(ShiShiCai3Z.KEY);
		shishicai.add(ShiShiCai5X.KEY);
		shishicai.add(ShiShiCai6Z.KEY);
		shishicai.add(ShiShiCaiDD.KEY);
		shishicai.add(ShiShiCai2ZX.KEY);
		shishicai.add(ShiShiCai2ZXHZ.KEY);
		map.put("T01007", shishicai);
		
		
		List<String> shuangseqiu = new ArrayList<String>();
		shuangseqiu.add(ShuangSeQiuX.KEY);
		map.put("F47104", shuangseqiu);
		
		List<String> threed = new ArrayList<String>();
		threed.add(ThreeDZ36.KEY);
		threed.add(ThreeDZ36HZ.KEY);
		threed.add(ThreeDZX.KEY);
		threed.add(ThreeDZXHZ.KEY);
		map.put("F47103", threed);
		
		
		List<String> duolecai = new ArrayList<String>();
		duolecai.add(DuoLeCaiQ3Z.KEY);
		duolecai.add(DuoLeCaiQ3.KEY);
		duolecai.add(DuoLeCaiQ2Z.KEY);
		duolecai.add(DuoLeCaiQ2.KEY);
		duolecai.add(DuoLeCaiQ1.KEY);
		duolecai.add(DuoLeCaiQ3ZH.KEY);
		duolecai.add(DuoLeCaiR5ZH.KEY);
		duolecai.add(DuoLeCaiR7ZH.KEY);
		duolecai.add(DuoLeCaiR8ZH.KEY);
		duolecai.add(DuoLeCaiRX.KEY);
		map.put("T01010", duolecai);
		
		List<String> gd11c5 = new ArrayList<String>();
		gd11c5.add(Guangdong11c5Q3Z.KEY);
		gd11c5.add(Guangdong11c5Q3.KEY);
		gd11c5.add(Guangdong11c5Q2Z.KEY);
		gd11c5.add(Guangdong11c5Q2.KEY);
		gd11c5.add(Guangdong11c5Q1.KEY);
		gd11c5.add(Guangdong11c5Q3ZH.KEY);
		gd11c5.add(Guangdong11c5R5ZH.KEY);
		gd11c5.add(Guangdong11c5R7ZH.KEY);
		gd11c5.add(Guangdong11c5R8ZH.KEY);
		gd11c5.add(Guangdong11c5RX.KEY);
		map.put("T01014", gd11c5);
		
		
		List<String> gdhappy10 = new ArrayList<String>();
		gdhappy10.add(GdHappytenS1.KEY);
		gdhappy10.add(GdHappytenRX.KEY);
		gdhappy10.add(GdHappytenQ3.KEY);
		gdhappy10.add(GdHappytenH1.KEY);
		map.put("T01015", gdhappy10);
		
	}
	
	
	public static List<String> getMissValueTypeList(String lotno) {
		if(map.containsKey(lotno)) {
			return map.get(lotno);
		}
		return null;
	}
}
