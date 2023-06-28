package com.day02;

import java.util.Scanner;

public class Exam08 {

	public static void main(String[] args) {
		// 수를 입력하고 마지막은 -1
		// while 문 사용
		// 입력한 수의 합계와 평균 구하기
		// 예) 10 5 6 9 -1
		Scanner sc = new Scanner(System.in);
//		System.out.println("수 입력. 마지막은 -1 >>>");
//		int sum = 0;
//		int n=0;
//		while(true) {
//			int num = sc.nextInt();
//			if(num == -1) break;
//			sum += num;
//			n++;
//		}
//		System.out.println("합계 : " + sum);
//		System.out.println("평균 : " + (float)sum/n);
		

		/*
		 *  학생의 점수를 입력받아 총점과 평균을 구하시오 
		 *  마지막은 0으로 입력
		 */
		System.out.println("학생의 점수 입력. 마지막은 0 >>>");
		int hap=0;
		int cnt = 0;
		while(true) {
			int score = sc.nextInt();
			if(score ==0) break;
			hap += score;
			cnt++;
		}
		System.out.println("과목합계 : " + hap);
		System.out.println("과목평균 : " + (float)hap/cnt);
		
	}

}



