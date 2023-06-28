package com.day10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class CapitalApp {
	private HashMap<String, String> map = new HashMap<>();
	public static Scanner sc = new Scanner(System.in);
	//생성자
	public CapitalApp() {
		 map.put("한국","서울");
	 	 map.put("일본", "동경");
	 	 map.put("중국","베이찡");
	 	 map.put("미국", "워싱턴");
	 	 map.put("영국","런던");
	 	 map.put("프랑스", "파리");
	 	 map.put("독일","베를린");
	}
	public static void showMenu() {
		 System.out.println("===수도 맞추기 게임 시작====");
		 System.out.println("1.문제입력 2. 퀴즈 3.종료 >>");
	}
	//문제추가
	public void input() {
		while(true) {
			System.out.println("나라와 수도 입력( 종료는 x)>>");
			String nara = sc.next(); //나라
			if(nara.equalsIgnoreCase("x"))break;
			if(map.containsKey(nara)) {
				System.out.println("이미 입력한 나라입니다.");
				continue;
			}
			
			String sudo = sc.next();
			map.put(nara, sudo);  /////
			System.out.println("추가 성공 :  "+map.size() +"개 문제");
			
		}
	}
	public void quiz() {
	  Set<String>	 set = map.keySet(); //키값만 구하기
	  //구한 키값 set을 ArrayList에 담기 ===> 순서
	  ArrayList<String> list = new ArrayList<>(set);
	  while(true) {
		  int r = (int)(Math.random()*map.size());
		  String country = list.get(r); //  문제(로 출제될 나라)
		  String result =   map.get(country) ; //정답(수도)
		
		  //문제출제
		  System.out.println(country  +" 수도는 ? (종료 x) >>");
		  String dap = sc.next();
		  if(dap.equalsIgnoreCase("x")) break;
		  if(result.equals(dap)) {
			  System.out.println("정답!!!");
		  }else {
			  System.out.println("틀렸습니다.");
		  }
	  }//while
 }//quiz

	public static void main(String[] args) {
		CapitalApp app = new CapitalApp();
		while(true) {
			CapitalApp.showMenu();
			int choice = CapitalApp.sc.nextInt();
			switch(choice) {
				case 1: app.input(); break;
				case 2: app.quiz(); break;
				case 3: System.out.println("종료");
								System.exit(0);
				default: System.out.println("입력오류");
			}
		}

	}

}
