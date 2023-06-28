package com.day06;

public class StringTest02 {

	public static void main(String[] args) {
		String str = "안녕하세요 Hello  지금은 자바 공부!!!";
		System.out.println("str 길이 :"+str.length());
		System.out.println("H 위치 : "+str.indexOf("H"));
		System.out.println("Hello 위치 : "+str.indexOf("Hello"));
		System.out.println("4번째 문자 :" + str.charAt(4));
		String tmp ="열심히 합시다";
		System.out.println("str 과 tmp 연결 : " +str+tmp );
		System.out.println("str 과 tmp 연결 : "+str.concat(tmp));
		int value =7;
		//str 과 value 연결
		System.out.println("str 과 value 연결 : "+str+value);
		//str 과 value 연결 concat  이용
		//value(int) ===> String
		String s = String.valueOf(value);
		System.out.println("str 과 value 연결 : "+str.concat(s));
		System.out.println("str 과 value 연결 : "+str.concat(String.valueOf(value)));
		System.out.println("str 과 value 연결 : "+str.concat(value+""));
		
		String tmp2 = "abc def ghi test java good";
		System.out.println("tmp2 : "+ tmp2);
		System.out.println("tmp2   대문자 : " +tmp2.toUpperCase());
		System.out.println("tmp2   소문자 : " +tmp2.toUpperCase().toLowerCase());
		System.out.println("tmp2   길이 :"+tmp2.length()); 
		
		System.out.println(tmp2.indexOf("a"));
	    System.out.println(tmp2.lastIndexOf("a")); //뒤에서 찾아 그 때 인덱스 값
	  //tmp2 마지막 문자
	    System.out.println(tmp2.charAt(tmp2.length()-1));
	    System.out.println(tmp2.isEmpty());
	    System.out.println(tmp2.substring(5));
	    System.out.println(tmp2.substring(5,10));
	    String[] arr = tmp2.split(" ");
	    System.out.println(arr.length);
	    for(String val : arr) {
	    	System.out.println(val);
	    }
	    
		

	}

}







