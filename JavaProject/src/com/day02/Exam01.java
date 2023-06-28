package com.day02;

import java.util.Scanner;

public class Exam01 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("단입력>>");
		int dan = sc.nextInt();
		if(dan % 2 ==0) { //짝수
			for(int i=1; i<10;i++) {
				System.out.println(dan+"*"+i+"="+dan*i);
			}
		}else { //홀수
			System.out.println("홀수단입니다");
		}
		//1부터 10까지의 합 : 1+2+3+    +10 = 55
		int sum=0;
		for(int i=1; i<11;i++) { // i=1  2    3  4
		       sum +=i ;  // sum =  sum+i;         //sum =1 3 6 10
		}
		 System.out.println(sum);
		
		
		
	} //main
}//class





