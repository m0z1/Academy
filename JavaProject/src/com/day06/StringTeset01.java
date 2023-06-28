package com.day06;

public class StringTeset01 {

	public static void main(String[] args) {
		String str = "안녕";
		String str1 = "안녕";
		String tmp = new String("안녕");
		if(str == str1) {
			System.out.println("str == str1 주소 같다");  //
		}else {
			System.out.println("str == str1 주소 다르다");
		}
		
		if(str == tmp) {
			System.out.println("str == tmp 주소 같다");
		}else {
			System.out.println("str == tmp  주소 다르다");  //
		}
		if(str.equals(str1)) {
			System.out.println("str equals str1 내용 같다");//
		}else {
			System.out.println("str equals str1 내용 다르다");
		}
		
		if(str.equals(tmp)) {
			System.out.println("str equals tmp 내용 같다");//
		}else {
			System.out.println("str equals tmp 내용 다르다");
		}
		System.out.println("str = " + str);
		str = str + str1 ; //안녕안녕
		System.out.println("str = " + str);
		if(str == str1) {
			System.out.println("str == str1 주소 같다");  
		}else {
			System.out.println("str == str1 주소 다르다");  //
		}
		System.out.println(str.length());
		

	}

}




