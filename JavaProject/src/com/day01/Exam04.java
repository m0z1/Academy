package com.day01;

import java.util.Scanner;

public class Exam04 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("점수와 커트라인 점수 입력>>");
		int score = sc.nextInt();
		int cutline = sc.nextInt();
		if(score >=   cutline) {  //점수>=  커트라인점수
			System.out.println(score + " : 합격");
		}else {
			System.out.println(score  + " : 불합격");
		}
		
		//score 가 3의배수 인지 아닌지 출력
		if(score % 3 ==0) {
			System.out.println(score + " : 3 의 배수");
		}else {
			System.out.println(score + " : 3 의 배수 아님 ");
		}
		
		
	}

}







