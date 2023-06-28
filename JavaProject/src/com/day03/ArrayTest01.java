package com.day03;

import java.util.Scanner;

public class ArrayTest01 {

	public static void main(String[] args) {
		//데이터를 5개 입력받아 합계를 출력한 뒤  10 20 30 40 50   
		// 입력 받은 데이터 5개를 출력하시오  ==> 합계 : ???  , 입력데이터 : 10 20 30 40 50
       Scanner sc =new Scanner(System.in);
       System.out.println("5개의 정수 입력>>");
       int sum = 0;
       int[] arr =new int[5]; // 배열 선언
       for(int i =0 ; i<5; i++) {
    	   arr[i]  = sc.nextInt(); // 입력데이터가 arr 각 인덱스에 저장
    	   sum +=  arr[i];
       }
       System.out.println("합계 : " + sum);
       System.out.println("입력데이터 : ");  //arr[0]~arr[4]
       for(int i=0 ; i<arr.length;i++) {
    	   System.out.println(arr[i]);
       }
       System.out.println("=====");
       //for-each
       for(int a  : arr) {
    	   System.out.println(a);
       }
       
	}

}




