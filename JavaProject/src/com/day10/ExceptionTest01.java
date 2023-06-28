package com.day10;

public class ExceptionTest01 {

	public static void main(String[] args) {
		String[] message = {"one","two","three"};
//		for(int i =0; i<message.length;i++) {
//			System.out.println(message[i]);
//		}
		String str = null;
		try {
		//	System.out.println(str.length()); //문자열 길이
			for(int i =0; i<message.length;i++) {
				System.out.println(message[i]);
			}
		}catch(NullPointerException n){
			System.out.println("null 값");
		}catch(ArrayIndexOutOfBoundsException  a) {
			System.out.println("배열 범위 초과");
		}finally { //반드시 수행되는 구문
			System.out.println("반드시 수행");
		}
		
		
		
		
	}

}
