package com.day06;

import java.util.StringTokenizer;

public class StringTok3 {

	public static void main(String[] args) {
		String tmp = "1 0 15 23 6 21 17 10 11 12 13 14";
		//StringTokenizer 이용해서 공백으로 구분
		//16, 8, 2진수로 변환하여 출력
		StringTokenizer sk = new StringTokenizer(tmp);
         StringBuilder hex = new StringBuilder();
         StringBuilder oct = new StringBuilder();
         StringBuilder bin = new StringBuilder();
         while(sk.hasMoreElements()) {
        	 String str = sk.nextToken();
        	 int v = Integer.parseInt(str);
             hex.append(Integer.toHexString(v)+"  ");
             oct.append(Integer.toOctalString(v)+"  ");
             bin.append(Integer.toBinaryString(v)+"  ");
         }
 		System.out.println("16진수 : " + hex);
 		System.out.println("8진수 : " + oct);
 		System.out.println("2진수 : " + bin);
	}

}








