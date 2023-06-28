package com.day07;

import java.util.ArrayList;
import java.util.Scanner;

public class TeacherMain {
	static Scanner sc = new Scanner(System.in);
	//Student[] str = new Student[50];
	ArrayList<Student> arr = new ArrayList<>();
	//int cnt;
	
	public static void showMenu() {
		System.out.println("선택하세요>>");
		System.out.println("1. 입력  2. 전체보기/종료");
		System.out.println("선택>>");
	}
	public void inputData() {
		System.out.println("--- 학생 성적 입력 ----");
		System.out.println("이름>>");
		String name = sc.next();
		System.out.println("국어 영어 수학>>");
		int kor = sc.nextInt();
		int eng = sc.nextInt();
		int math = sc.nextInt();
		//str[cnt++] = new Student(name, kor, eng, math);
		arr.add(new Student(name, kor, eng, math));
	}

	public void viewData() {
		//for-each
//		for(Student s  : arr) {
//			System.out.println("이름 : " +s.getName());
//			System.out.println("국어 : " + s.getKor());
//			System.out.println("영어 : " + s.getEng());
//			System.out.println("수학 : " + s.getMath());
//			System.out.println("총점 : " + s.getTotal());
//			System.out.println("평균 : " + s.getAvg());
//			System.out.println();
//		}
		
		
		
		for(int i = 0; i<arr.size(); i++) {
			System.out.println("이름 : " + arr.get(i).getName());
			System.out.println("국어 : " + arr.get(i).getKor());
			System.out.println("영어 : " + arr.get(i).getEng());
			System.out.println("수학 : " + arr.get(i).getMath());
			System.out.println("총점 : " + arr.get(i).getTotal());
			System.out.println("평균 : " + arr.get(i).getAvg());
			System.out.println();
		}

		
		
	}

	public static void main(String[] args) {
			TeacherMain t = new TeacherMain();
			while(true) {
				TeacherMain.showMenu();
				int num = sc.nextInt();
				switch(num) {
				case 1: t.inputData(); break;
				case 2: t.viewData();
				            System.exit(0); //종료
				default:System.out.println("입력오류");
				}//switch
			}//while
		}//main

} //class
