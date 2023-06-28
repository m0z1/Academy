package com.day01;

import java.util.Scanner;

public class Exam10 {
	public static void main(String[] args) {
		for(int i=0;i<5;i++) {  //i=1  i=2 i=3 i=4 i=5 i=6
			System.out.println(i + " : Hello world");
		}
		/*
		 *  2*1=2
		 *  2*2=4
		 *  2*3=6
		 *  
		 *  
		 *  
		 *  2*9=18
		  */
		for(int i =1; i<10;i++) {
			System.out.println("2 * "+i+"="+2*i);
		}
		// 1 2 3 4 5 6 7 8 9 10
		for(int i=1; i<=10;i++) {
			System.out.print(i+"\t");
		}
		System.out.println("\n=====");
		//1부터 10까지 짝수만 출력
		for(int i=1; i<11; i++) {
			if(i %2==0) {
				System.out.print(i + "\t");
			} //if
		} //for
		System.out.println("\n=====");
		for(int i=2; i<=10; i+=2 ) {
			System.out.print(i + "\t");
		}
		//  단을 입력받아 그 단의 구구단 출력
		Scanner sc = new Scanner(System.in);
		System.out.println("\n단 입력>>");
		int dan = sc.nextInt();
		for(int i=1; i<10;i++) {
			System.out.println(dan + " * " + i +" = " + dan*i);
		}
		
		
		
	} //main

}//class






