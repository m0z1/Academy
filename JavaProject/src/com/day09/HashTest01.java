package com.day09;

import java.util.HashMap;
import java.util.Scanner;

public class HashTest01 {

	public static void main(String[] args) {
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("java","1111");
		hm.put("oracle","1234");
		hm.put("jsp","abcd");
		Scanner sc = new Scanner(System.in);
		System.out.println("id/password 를 입력하세요");
		System.out.println("id 입력 >>");
		 String id =sc.next();
		 System.out.println("password 입력 >>");
		 String password =sc.next();
		
		//존재하지 않는 ID입니다.
		 if(!hm.containsKey(id)) {
			 System.out.println("존재하지 않는 ID입니다");
			 return;
		 }
		
		 if(hm.get(id).equals(password)) {  //모두 일치합니다.
			 System.out.println("모두 일치합니다");
		 }else {  //비밀번호가 일치하지 않습니다.
			 System.out.println("비밀번호가 일치하지 않습니다");
		 }
		

	}

}
