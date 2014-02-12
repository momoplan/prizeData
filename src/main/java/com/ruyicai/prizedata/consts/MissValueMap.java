package com.ruyicai.prizedata.consts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruyicai.prizedata.missvalue.chongqing11c5.Chongqing11c5Q1;
import com.ruyicai.prizedata.missvalue.chongqing11c5.Chongqing11c5Q2;
import com.ruyicai.prizedata.missvalue.chongqing11c5.Chongqing11c5Q2Z;
import com.ruyicai.prizedata.missvalue.chongqing11c5.Chongqing11c5Q3;
import com.ruyicai.prizedata.missvalue.chongqing11c5.Chongqing11c5Q3Z;
import com.ruyicai.prizedata.missvalue.chongqing11c5.Chongqing11c5Q3ZH;
import com.ruyicai.prizedata.missvalue.chongqing11c5.Chongqing11c5R5ZH;
import com.ruyicai.prizedata.missvalue.chongqing11c5.Chongqing11c5R7ZH;
import com.ruyicai.prizedata.missvalue.chongqing11c5.Chongqing11c5R8ZH;
import com.ruyicai.prizedata.missvalue.chongqing11c5.Chongqing11c5RX;
import com.ruyicai.prizedata.missvalue.cqhappyten.CqHappytenH1;
import com.ruyicai.prizedata.missvalue.cqhappyten.CqHappytenQ3;
import com.ruyicai.prizedata.missvalue.cqhappyten.CqHappytenR2ZH;
import com.ruyicai.prizedata.missvalue.cqhappyten.CqHappytenR3ZH;
import com.ruyicai.prizedata.missvalue.cqhappyten.CqHappytenR4ZH;
import com.ruyicai.prizedata.missvalue.cqhappyten.CqHappytenR5ZH;
import com.ruyicai.prizedata.missvalue.cqhappyten.CqHappytenRX;
import com.ruyicai.prizedata.missvalue.cqhappyten.CqHappytenS1;
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
import com.ruyicai.prizedata.missvalue.gdhappyten.GdHappytenR2ZH;
import com.ruyicai.prizedata.missvalue.gdhappyten.GdHappytenR3ZH;
import com.ruyicai.prizedata.missvalue.gdhappyten.GdHappytenR4ZH;
import com.ruyicai.prizedata.missvalue.gdhappyten.GdHappytenR5ZH;
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
import com.ruyicai.prizedata.missvalue.jlkuai3.JlK300;
import com.ruyicai.prizedata.missvalue.jlkuai3.JlK301;
import com.ruyicai.prizedata.missvalue.jlkuai3.JlK302;
import com.ruyicai.prizedata.missvalue.jlkuai3.JlK310;
import com.ruyicai.prizedata.missvalue.jlkuai3.JlK330;
import com.ruyicai.prizedata.missvalue.jlkuai3.JlK340;
import com.ruyicai.prizedata.missvalue.jlkuai3.JlK350;
import com.ruyicai.prizedata.missvalue.jlkuai3.JlK391;
import com.ruyicai.prizedata.missvalue.jlkuai3.JlK392;
import com.ruyicai.prizedata.missvalue.jlkuai3.JlK393;
import com.ruyicai.prizedata.missvalue.luckyracing.LuckyRacingDD;
import com.ruyicai.prizedata.missvalue.luckyracing.LuckyRacingPlace;
import com.ruyicai.prizedata.missvalue.luckyracing.LuckyRacingQ1;
import com.ruyicai.prizedata.missvalue.luckyracing.LuckyRacingQ2;
import com.ruyicai.prizedata.missvalue.luckyracing.LuckyRacingQ3;
import com.ruyicai.prizedata.missvalue.luckyracing.LuckyRacingZ2;
import com.ruyicai.prizedata.missvalue.luckyracing.LuckyRacingZ3;
import com.ruyicai.prizedata.missvalue.nmkuai3.NmK300;
import com.ruyicai.prizedata.missvalue.nmkuai3.NmK301;
import com.ruyicai.prizedata.missvalue.nmkuai3.NmK302;
import com.ruyicai.prizedata.missvalue.nmkuai3.NmK310;
import com.ruyicai.prizedata.missvalue.nmkuai3.NmK330;
import com.ruyicai.prizedata.missvalue.nmkuai3.NmK340;
import com.ruyicai.prizedata.missvalue.nmkuai3.NmK350;
import com.ruyicai.prizedata.missvalue.nmkuai3.NmK391;
import com.ruyicai.prizedata.missvalue.nmkuai3.NmK392;
import com.ruyicai.prizedata.missvalue.nmkuai3.NmK393;
import com.ruyicai.prizedata.missvalue.pailiesan.PaiLieSanZ36;
import com.ruyicai.prizedata.missvalue.pailiesan.PaiLieSanZ36HZ;
import com.ruyicai.prizedata.missvalue.pailiesan.PaiLieSanZX;
import com.ruyicai.prizedata.missvalue.pailiesan.PaiLieSanZXHZ;
import com.ruyicai.prizedata.missvalue.pailiewu.PaiLieWuZX;
import com.ruyicai.prizedata.missvalue.qilecai.QiLeCaiX;
import com.ruyicai.prizedata.missvalue.qixingcai.QiXingCaiZX;
import com.ruyicai.prizedata.missvalue.shanghai11c5.Shanghai11c5Q1;
import com.ruyicai.prizedata.missvalue.shanghai11c5.Shanghai11c5Q2;
import com.ruyicai.prizedata.missvalue.shanghai11c5.Shanghai11c5Q2Z;
import com.ruyicai.prizedata.missvalue.shanghai11c5.Shanghai11c5Q3;
import com.ruyicai.prizedata.missvalue.shanghai11c5.Shanghai11c5Q3Z;
import com.ruyicai.prizedata.missvalue.shanghai11c5.Shanghai11c5Q3ZH;
import com.ruyicai.prizedata.missvalue.shanghai11c5.Shanghai11c5R5ZH;
import com.ruyicai.prizedata.missvalue.shanghai11c5.Shanghai11c5R7ZH;
import com.ruyicai.prizedata.missvalue.shanghai11c5.Shanghai11c5R8ZH;
import com.ruyicai.prizedata.missvalue.shanghai11c5.Shanghai11c5RX;
import com.ruyicai.prizedata.missvalue.shishicai.ShiShiCai2D;
import com.ruyicai.prizedata.missvalue.shishicai.ShiShiCai2Z;
import com.ruyicai.prizedata.missvalue.shishicai.ShiShiCai2ZX;
import com.ruyicai.prizedata.missvalue.shishicai.ShiShiCai2ZXHZ;
import com.ruyicai.prizedata.missvalue.shishicai.ShiShiCai3D;
import com.ruyicai.prizedata.missvalue.shishicai.ShiShiCai3Z;
import com.ruyicai.prizedata.missvalue.shishicai.ShiShiCai5X;
import com.ruyicai.prizedata.missvalue.shishicai.ShiShiCai6Z;
import com.ruyicai.prizedata.missvalue.shishicai.ShiShiCaiDD;
import com.ruyicai.prizedata.missvalue.shishicai.ShiShiCaiZ36;
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
		shishicai.add(ShiShiCaiZ36.KEY);
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
		gdhappy10.add(GdHappytenR2ZH.KEY);
		gdhappy10.add(GdHappytenR3ZH.KEY);
		gdhappy10.add(GdHappytenR4ZH.KEY);
		gdhappy10.add(GdHappytenR5ZH.KEY);
		map.put("T01015", gdhappy10);
		
		List<String> nmk3 = new ArrayList<String>();
		nmk3.add(NmK300.KEY);
		nmk3.add(NmK301.KEY);
		nmk3.add(NmK302.KEY);
		nmk3.add(NmK310.KEY);
		nmk3.add(NmK330.KEY);
		nmk3.add(NmK340.KEY);
		nmk3.add(NmK350.KEY);
		nmk3.add(NmK391.KEY);
		nmk3.add(NmK392.KEY);
		nmk3.add(NmK393.KEY);
		map.put("F47107", nmk3);
		
		List<String> cq11c5 = new ArrayList<String>();
		cq11c5.add(Chongqing11c5Q1.KEY);
		cq11c5.add(Chongqing11c5Q2.KEY);
		cq11c5.add(Chongqing11c5Q2Z.KEY);
		cq11c5.add(Chongqing11c5Q3.KEY);
		cq11c5.add(Chongqing11c5Q3Z.KEY);
		cq11c5.add(Chongqing11c5RX.KEY);
		cq11c5.add(Chongqing11c5Q3ZH.KEY);
		cq11c5.add(Chongqing11c5R5ZH.KEY);
		cq11c5.add(Chongqing11c5R7ZH.KEY);
		cq11c5.add(Chongqing11c5R8ZH.KEY);
		map.put("T01016", cq11c5);
		
		List<String> jlk3 = new ArrayList<String>();
		jlk3.add(JlK300.KEY);
		jlk3.add(JlK301.KEY);
		jlk3.add(JlK302.KEY);
		jlk3.add(JlK310.KEY);
		jlk3.add(JlK330.KEY);
		jlk3.add(JlK340.KEY);
		jlk3.add(JlK350.KEY);
		jlk3.add(JlK391.KEY);
		jlk3.add(JlK392.KEY);
		jlk3.add(JlK393.KEY);
		map.put("F47108", jlk3);
		
		List<String> cqhappy10 = new ArrayList<String>();
		cqhappy10.add(CqHappytenH1.KEY);
		cqhappy10.add(CqHappytenQ3.KEY);
		cqhappy10.add(CqHappytenRX.KEY);
		cqhappy10.add(CqHappytenS1.KEY);
		cqhappy10.add(CqHappytenR2ZH.KEY);
		cqhappy10.add(CqHappytenR3ZH.KEY);
		cqhappy10.add(CqHappytenR4ZH.KEY);
		cqhappy10.add(CqHappytenR5ZH.KEY);
		map.put("F47109", cqhappy10);
		
		
		List<String> shanghai11c5 = new ArrayList<String>();
		shanghai11c5.add(Shanghai11c5Q1.KEY);
		shanghai11c5.add(Shanghai11c5Q2.KEY);
		shanghai11c5.add(Shanghai11c5Q2Z.KEY);
		shanghai11c5.add(Shanghai11c5Q3.KEY);
		shanghai11c5.add(Shanghai11c5Q3Z.KEY);
		shanghai11c5.add(Shanghai11c5RX.KEY);
		shanghai11c5.add(Shanghai11c5Q3ZH.KEY);
		shanghai11c5.add(Shanghai11c5R5ZH.KEY);
		shanghai11c5.add(Shanghai11c5R7ZH.KEY);
		shanghai11c5.add(Shanghai11c5R8ZH.KEY);
		map.put("T01017", shanghai11c5);
		
		
		List<String> luckyracing = new ArrayList<String>();
		luckyracing.add(LuckyRacingDD.KEY);
		luckyracing.add(LuckyRacingPlace.KEY);
		luckyracing.add(LuckyRacingQ1.KEY);
		luckyracing.add(LuckyRacingQ2.KEY);
		luckyracing.add(LuckyRacingQ3.KEY);
		luckyracing.add(LuckyRacingZ2.KEY);
		luckyracing.add(LuckyRacingZ3.KEY);
		map.put("T01018", luckyracing);
		
	}
	
	
	public static List<String> getMissValueTypeList(String lotno) {
		if(map.containsKey(lotno)) {
			return map.get(lotno);
		}
		return null;
	}
}
