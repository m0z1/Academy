package com.day03;

import java.util.Scanner;

public class Exam01 {

	public static void main(String[] args) {
		// 수를 입력받아(-1 종료) 짝수만 더하기  while continue 사용
		Scanner sc = new Scanner(System.in);
		System.out.println("수를 입력하세요. 마지막은 -1 >>>");
		int sum =0;
		while(true) {
			int num = sc.nextInt();
			if(num == -1) break;
			if(num % 2 != 0)   continue;   // 홀수이면 그냥  while 다시 수행
			sum += num;  //짝수
			
		}
		System.out.println("합계 : " + sum);
		sc.close();
	}

}
