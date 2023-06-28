package com.day11;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import com.day10.CapitalApp;

public class MyCapital {
	private HashMap<String, String> hm = new HashMap<>();
    private File dir, file;
	public MyCapital() { // 파일을 읽어서 맵에 추가
		dir = new File("src\\com\\day11");
		file = new File(dir, "capital.txt");
	
		try {
				if(!file.exists()) { //파일이 없다면
					file.createNewFile() ;  //파일 생성
					return; //종료
				}
				//파일이 있다면
				Scanner sc = new Scanner(file);  //파일 읽기
				//맵에 추가
				while(sc.hasNext()) {
					String key = sc.next(); //나라
					String value = sc.next() ;// 수도
					hm.put(key, value);
				}
				System.out.println(hm);
				sc.close();
				
		} catch (IOException e) {
				e.printStackTrace();
		} 
	
		
	}
	//문제추가
	public void input() {
		while(true) {
			System.out.println("현재 : "+ hm.size());
			System.out.println("나라 와 수도 입력(종료 x)>>>");
			String nara = CapitalApp.sc.next(); //나라
			if(nara.equalsIgnoreCase("x")) break;
			if(hm.containsKey(nara)) { //맵에 있음
				System.out.println("이미  입력한 나라입니다.");
				continue;
			}
			String sudo = CapitalApp.sc.next();
			hm.put(nara, sudo);
		}
		
		
	}
	//문제
	public void quiz() {
		Set<String> set = hm.keySet(); //키값 구함
		 Object[] arr =set.toArray(); // 배열에 담기 ==>순서
		 while(true) {
			 int r = (int)(Math.random()*hm.size());
			 String country = (String)arr[r];// 문제
			 String result = hm.get(country) ;//정답
			 
			 //문제출제
			  System.out.println(country  +" 수도는 ? (종료 x) >>");
			  String dap = CapitalApp.sc.next();
			  if(dap.equalsIgnoreCase("x")) break;
			  if(result.equals(dap)) {
				  System.out.println("정답!!!");
			  }else {
				  System.out.println("틀렸습니다.");
			  }
		 }
	}
	//맵을 파일로 저장 후 종료
	public void save() {
		try {
			FileWriter fw = new FileWriter(file);
			Set<String> set = hm.keySet();  //키(나라)만 구함
			Iterator<String> it =  set.iterator();
			while(it.hasNext()) {
				String key = it.next(); //나라
				String value =  hm.get(key) ;  //수도
				fw.write(key +"  ");
				fw.write(value+"\n");
			}
			fw.close();
		} catch (IOException e) {
				e.printStackTrace();
		}
		System.out.println("종료");
		System.exit(0);  //종료
		
	}

	public static void main(String[] args) {
		MyCapital app = new MyCapital();
		while(true) {
			CapitalApp.showMenu();
			int choice = CapitalApp.sc.nextInt();
			switch(choice) {
				case 1: app.input(); break;
				case 2: app.quiz(); break;
				case 3: app.save(); break;
				default:System.out.println("입력오류");
			}
		}

	}

}
