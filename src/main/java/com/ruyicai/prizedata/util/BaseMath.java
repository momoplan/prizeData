package com.ruyicai.prizedata.util;

import java.util.List;

public class BaseMath {

	/**
	 * @param a 
	 * @param n 
	 * @param m 
	 * @param b
	 * @param M 
	 * @param list 
	 */
	public static void combine(int a[], int n, int m, int b[], final int M, List<int[]> list) {
		for (int i = n; i >= m; i--) {
			b[m - 1] = i - 1;
			if (m > 1)
				combine(a, i - 1, m - 1, b, M, list);
			else {
				int[] result = new int[M];
				for (int j = M - 1; j >= 0; j--) {
					result[j] = a[b[j]];
				}
				list.add(result);
			}
		}
	}
}
