package com.day06;

import java.util.StringTokenizer;

public class StringTok2 {

	public static void main(String[] args) {
		String tmp = "1 0 15 23 6 21 17 10 11 12 13 14";
		//StringTokenizer 이용해서 공백으로 구분
		//16, 8, 2진수로 변환하여 출력
		StringTokenizer stk = new StringTokenizer(tmp);
		String hex ="";
		String oct ="";
		String bin = "";
		while(stk.hasMoreTokens()) {
			String str = stk.nextToken(); // 1
			int v = Integer.parseInt(str);
			hex += Integer.toHexString(v)+"  ";
			oct += Integer.toOctalString(v)+"  ";
			bin += Integer.toBinaryString(v)+" ";
		}
		System.out.println("16진수 : " + hex);
		System.out.println("8진수 : " + oct);
		System.out.println("2진수 : " + bin);

	}

}
