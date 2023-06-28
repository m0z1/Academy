package com.day03;

import java.util.Scanner;

public class ArrayTest02 {

	public static void main(String[] args) {
		// 입력개수를 입력받아 
		//  그 수만큼 데이터를 입력받고
		 // 입력받은 데이터의 합계 , 최대값 출력하고
		// 입력 받은 데이터를  출력하시오 
		Scanner sc = new Scanner(System.in);
		System.out.println("입력갯수 >> ");
		int length  =sc.nextInt();
		int[] arr = new int[length]; 	//배열생성
		int sum = 0;
		int max =arr[0];
		//while
		int n = 0;
		while(true) {   //while(n< length)
			arr[n] = sc.nextInt();
			sum += arr[n];
			if(max  < arr[n]) {
				max = arr[n];
			}
			n++;
			if(n==length) break;
			
		}
		
		
		
//		for(int i =0 ;i <arr.length;i++) {
//			arr[i] = sc.nextInt(); //입력값을 배열에 저장
//			sum += arr[i];  // 합계
//			if(max < arr[i]) { //최대값 결정
//				max = arr[i];
//			}
//		} //for
		
	
		System.out.println("합계 : " +sum);
		System.out.println("최대값 : " +max);
		System.out.println("입력데이터 :");
		for(int i = 0; i<arr.length;i++) {
			System.out.println(arr[i]);
		}
		
	
	}

}








