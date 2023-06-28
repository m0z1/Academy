package com.day06;

import java.util.StringTokenizer;

public class StringTok {

	public static void main(String[] args) {
		 StringTokenizer st 
		    = new StringTokenizer("홍길동,장화/홍련/팥쥐/주인공",",/");
		 System.out.println(st.countTokens());
//		 int len = st.countTokens();
//		 for(int i=0 ; i<len;i++) { //0<5    1<4   2<3  3<2
//			 System.out.println(st.nextToken());
//		 }
		 System.out.println(st.hasMoreTokens());
		 while(st.hasMoreTokens()) {
			 System.out.println("count : " + st.countTokens());
			 System.out.println(st.nextToken());
		 }

	}

}






