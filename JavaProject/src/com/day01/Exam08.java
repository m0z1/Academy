package com.day01;

import java.util.Scanner;

public class Exam08 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("두 수 입력>> ");
		//첫번째를 최대값으로 두번째를 최소값으로 생각
		int max = sc.nextInt();
		int min = sc.nextInt();
		if(max < min) {
			int tmp = max;
			max = min;
			min = tmp;
		}
		System.out.println("큰 수 : " + max);
		System.out.println("작은 수 : " +  min);
		System.out.println("두 수 차이 : " + (max- min));
	
		
	//	int first = sc.nextInt();
	//	int sec = sc.nextInt();
	//	if(first>sec) {
	//	System.out.println("큰 수 : "+first + " 작은 수 :" + sec +" 차이 : " + (first-sec));
	//	}else {
	//		System.out.println("큰 수 : "+sec + " 작은 수 :" + first +" 차이 : " + (sec-first));
	//	}
		
		
		
		
		
	} //main 

} //class





