package com.day01;

import java.util.Scanner;

public class Exam03 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//System.out.println("이름 입력>>");
		//String name = sc.next(); //문자입력
		//System.out.println(name);
		System.out.println("숫자 입력>>");
		int num = sc.nextInt();  //정수입력
		//System.out.println(num);
		//입력한 숫자가 짝수인지 홀수인지 출력
		if(num % 2 ==0) {
			System.out.println("짝수");
		}else {
			System.out.println("홀수");
		}
		
		System.out.println("나이 입력>>");
		
		//나이(age)를 입력받아 나이가 20이상이면 입장가능
		// 15세이상 20미만이면 부모님과 동반입장
		// 15세 미만이면 입장불가능
		int age = sc.nextInt();
		if( age >= 20) {
			System.out.println("입장가능");
		}else if(age >=15) {
			System.out.println("부모님과 동반입장");
		}else {
			System.out.println("입장불가능");
		}
		System.out.println("==========");
		// if 문으로만 사용해서 수정하기
		if( age >= 20) {
			System.out.println("입장가능");
		}
		if(age < 20 && age >=15) {
			System.out.println("부모님과 동반입장");
		}else {
			System.out.println("입장불가능");
		}
		sc.close();
	} //main
} //class






