package com.day01;

import java.util.Scanner;

public class Exam05 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("이름 입력 >>");
		String name = sc.next();
		System.out.println("국어 영어 수학 성적 입력 >>");
		int kor = sc.nextInt();
		int eng = sc.nextInt();
		int math = sc.nextInt();
	//	System.out.println(name + " :  총점 "+(kor+eng+math));
	//	System.out.println(name + " :  평균 "+(kor+eng+math)/3);
		int tot = kor+eng+math;
	//	System.out.println(name + " :  총점 "+tot );
	//	System.out.println(name + " :  평균 "+ tot/3);
		float avg = tot/3.0f;
		System.out.println(name + " :  총점 "+tot );
		System.out.println(name + " :  평균 "+ avg);
		/*
		 * 평균 90점 이상이면 A학점
		 * 평균 80점 이상이면 B학점
		 * 평균 70점 이상이면 C학점
		 * 나머지 F학점
		 */
		if(avg >=90) {
			System.out.println("A학점");
		}else if(avg>=80) {
			System.out.println("B학점");
		}else if(avg>=70) {
			System.out.println("C학점");
		}else {
			System.out.println("F학점");
		}
		System.out.println("============");
		String grade;
		if(avg >=90) {
			grade = "A";
		}else if(avg >=80) {
			grade = "B";
		}else if(avg >=70) {
			grade = "C";
		}else {
			grade = "F"; 
		}
		System.out.println(grade + "학점");
		//switch  90~99 / 80~89/ 70~79
		int average = (int)avg;
		switch(average/10) {
			case 10:  
			case 9:  grade = "A"; break;
			case 8:  grade = "B"; break;
			case 7:   grade = "C"; break;
			default:  grade = "F"; 
		}
		System.out.println("학점 switch : "+ grade);
		int a = 10;
		switch(a) {
			case 10: System.out.println("10");break;
			case 9: System.out.println("9"); break;
			case 8: System.out.println("8"); break;
			default: System.out.println("default");
		}
	}

}












