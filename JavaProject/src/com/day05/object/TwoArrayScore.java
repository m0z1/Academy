package com.day05.object;

import java.util.Scanner;

public class TwoArrayScore {
	//학번, 국어, 영어, 수학, 총점, 평균, 석차
	int[][] arr = new int[50][7];
	static Scanner sc = new Scanner(System.in);
	int row;
	
//	public void showMenu() {
//		System.out.println("선택하세요>>");
//		System.out.println("1. 입력  2. 전체보기/종료");
//		System.out.println("선택>>");
//	}
	public void inputData() {
		System.out.println("--- 학생 성적 입력 ----");
		System.out.println("학번>>");
		int stNum = sc.nextInt();
		System.out.println("국어 영어 수학>>");
		int kor = sc.nextInt();
		int eng = sc.nextInt();
		int math = sc.nextInt();
		
		arr[row][0] = stNum;
		arr[row][1]= kor;
		arr[row][2]=eng;
		arr[row][3]=math;
		arr[row][4]=kor+eng+math;
		arr[row][5] = arr[row][4]/3;
		arr[row][6] =1;
		row++;
	}
	public void viewData() {
		rankMethod();
		System.out.println("---- 성적보기 -----");
		for(int i = 0; i<row; i++) {
			for(int j=0; j < arr[i].length; j++) {
				System.out.print(arr[i][j]+"\t");
			}
			System.out.println();
		}
	}
	private void rankMethod() { //석차구하기
		for(int i =0 ;i <row-1;i++) {
			for (int j = i+1; j<row ; j++) {
				if(arr[i][4]  > arr[j][4]) { //총점
					arr[j][6]++;
				}else if(arr[i][4] < arr[j][4]){
					arr[i][6]++;
				}
			}
		}
		
	}

	public static void main(String[] args) {
		TwoArrayScore manager = new TwoArrayScore();
		while(true) {
			TeacherMain.showMenu();
			int num = sc.nextInt();
			//성적입력  inputData()
			//성적출력  viewData()
			switch(num) {
				case 1: manager.inputData(); break;
				case 2: manager.viewData();
					System.exit(0); // 종료
				default: System.out.println("입력오류");
			}
			
		}

	}

}
