package com.day01;

import java.util.Scanner;

public class Exam07 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("밑변, 높이 입력>>");  
		//int width = sc.nextInt(); // 밑변
		//int height = sc.nextInt(); //높이
		float width = sc.nextFloat(); 
		float height = sc.nextFloat(); 
		//삼각형넓이 = 밑변 *높이*1/2
		System.out.println("삼각형의 넓이 : " +width* height/2.0);
		
		
	}

}




