package com.day02;

import java.util.Scanner;

public class Exam05 {
	public static void main(String[] args) {
		/*
		 *  학생수와 한줄에 앉을 학생 수 입력받아 출력
		 *  15
		 *  7
		 *  
		 *  1 2 3 4 5 6 7
		 *  8 9 10 11 12 13 14
		 *  15
		 */
		Scanner sc = new Scanner(System.in);
		System.out.println("학생 수 입력>>");
		int tot = sc.nextInt();  //15
		System.out.println("한 줄 학생 수 입력>>");
		int line = sc.nextInt(); //7
		for(int i=1 ; i<=tot;i++) {
			System.out.print(i+"\t");
			if(i%line ==0) {
				System.out.println();
			}
		} //for
		//필요한 라인 수 출력
		int row = tot/line;
		if(tot%line !=0) {
			row +=1;
		}
		System.out.println("\n필요한 총 라인 수 : "+row);
		int r;
		if(tot % line ==0) {
			r = tot/line;
		}else {
			r = tot/line + 1;
		}
		System.out.println("필요한 총 라인 수 r : "+r);
		//조건연산자(삼항연산자)
		int rr =(tot % line ==0)? tot/line : tot/line + 1;
		System.out.println("필요한 총 라인 수 rr : "+rr);
		
	} //main

}//class






