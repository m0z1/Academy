package com.day05.object;

import java.util.Scanner;

public class TeacherMain {
	static Scanner sc = new Scanner(System.in);
	Student[] str = new Student[50];
	int cnt;
	
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
	//	str[cnt] = new Student(name, kor, eng, math);
 //		cnt++;
		str[cnt++] = new Student(name, kor, eng, math);
	}
	private  void rankMethod() {
		for(int i =0 ;i <cnt-1;i++) {
			for(int j = i+1; j<cnt;j++) {
				if(str[i].getTotal()   >  str[j].getTotal()) {
					str[j].setRank();
				}else if(str[i].getTotal()   <  str[j].getTotal()) {
					str[i].setRank();
				}
			}
		}
	}
	public void viewData() {
		rankMethod();
		//for-each
		for(Student s  : str) {
			if(s==null) break;
			System.out.println("이름 : " +s.getName());
			System.out.println("국어 : " + s.getKor());
			System.out.println("영어 : " + s.getEng());
			System.out.println("수학 : " + s.getMath());
			System.out.println("총점 : " + s.getTotal());
			System.out.println("평균 : " + s.getAvg());
			System.out.println();
		}
		
		
		
//		for(int i = 0; i<cnt; i++) {
//			System.out.println("이름 : " + str[i].getName());
//			System.out.println("국어 : " + str[i].getKor());
//			System.out.println("영어 : " + str[i].getEng());
//			System.out.println("수학 : " + str[i].getMath());
//			System.out.println("총점 : " + str[i].getTotal());
//			System.out.println("평균 : " + str[i].getAvg());
//			System.out.println();
//		}

		
		
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
