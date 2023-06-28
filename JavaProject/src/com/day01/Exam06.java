package com.day01;

import java.util.Scanner;

public class Exam06 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("두 수와 연산자 입력 >>");
		int num1 = sc.nextInt();  // 10
		int num2 = sc.nextInt();  // 20
		String op = sc.next();  // +
		float result=0;
		switch(op) {
			case "+" : result = num1+num2; break;
			case "-" :  result = num1-num2; break;
			case "*" :  result = num1*num2; break;
			case "/" :  result = num1/num2; break;
			default : System.out.println("연산자 오류11");
		}
		 System.out.println("결과 : " + result);
		
	}

}






