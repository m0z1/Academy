package com.day05.object;

import java.util.Scanner;

public class ChangeMoney {

	public static void main(String[] args) {
		// int don = 65321;
		 /*
		  *  50000 원  : 1
		  *  10000 원  :1
		  *  1000원  :
		  *  500원 :
		  *  100원 :
		  *  1원  
		 */
		Scanner sc = new Scanner(System.in);
		System.out.println("금액을 입력하세요");
		int don = sc.nextInt();
		 int[] unit= {50000,10000,1000,500,100,1};
		 for(int  i=0; i<unit.length;i++) {
			 if(don/unit[i] > 0) {
				 System.out.println(unit[i] +"원 : " + don/unit[i]);
			 }
			 don = don%unit[i];
		 }

	}

}







