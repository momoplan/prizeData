package com.ruyicai.prizedata.missvalue.nmkuai3;

import java.util.Arrays;

public class NmK3Util {

	public static int[] convertWincodeInt(String wincode) {
		return new int[] { Integer.parseInt(wincode.substring(0, 2)),
				Integer.parseInt(wincode.substring(2, 4)),
				Integer.parseInt(wincode.substring(4, 6)) };
	}
	
	public static boolean isErTong(String wincode) {
		int[] wincodes = convertWincodeInt(wincode);
		Arrays.sort(wincodes);
		if (wincodes[0] == wincodes[1] && wincodes[0] != wincodes[2]) {
			return true;
		}
		if (wincodes[1] == wincodes[2] && wincodes[0] != wincodes[1]) {
			return true;
		}
		return false;
	}
	
	public static String getErTongCode(String win) {
		int[] wincodes = convertWincodeInt(win);
		
		if(wincodes[0]==wincodes[1]) {
			return String.valueOf(wincodes[0])+String.valueOf(wincodes[0])+String.valueOf(wincodes[2]);
		}else {
			return String.valueOf(wincodes[1])+String.valueOf(wincodes[1])+String.valueOf(wincodes[0]);
		}
	}
	
	public static String[] convertWincodeString(String wincode) {
		return new String[] { wincode.substring(0, 2), wincode.substring(2, 4),
				wincode.substring(4, 6) };
	}
	
	
	public static boolean isErBuTong(String wincode) {
		String[] wincodes = convertWincodeString(wincode);
		if (wincodes[0].equals(wincodes[1]) && wincodes[1].equals(wincodes[2])) {
			return false;
		}
		return true;
	}
	
	public static boolean isSanBuTong(String wincode) {
		String[] wincodes = convertWincodeString(wincode);
		if (wincodes[0].equals(wincodes[1]) || wincodes[0].equals(wincodes[2])
				|| wincodes[2].equals(wincodes[1])) {
			return false;
		}
		return true;
	}
	
}
