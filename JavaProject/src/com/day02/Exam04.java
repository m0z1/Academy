package com.day02;

import java.util.Scanner;

public class Exam04 {
	public static void main(String[] args) {
		/*
		 *  몇 개 입력 받을 지를 물어보고--->5
		 *  그 수만큼 숫자를 입력하여  --> 10 3 5 7 20
		 *  그 수들의 합계를 구하시오 -->45
		 */
		Scanner sc = new Scanner(System.in);
		System.out.println("입력갯수 >>");
		int cnt = sc.nextInt();  //--->5
		int sum = 0;
		for(int i=0; i<cnt;i++) { //       i=       0    1   2 3   4
		//	int num = sc.nextInt(); // num = 10  3  5 7   20
		//	sum  += num;                  //sum = 10+3+5 +7+20
			sum += sc.nextInt();
		}
		System.out.println("입력합계 : " + sum);
	}


}




