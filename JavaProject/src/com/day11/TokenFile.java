package com.day11;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class TokenFile {
	//aa.txt 파일을 읽어서 : 구분하여 숫자만 
	// 숫자를 16진수로 변경시켜 bb.txt  내보내기
	 // Scanner  PrintStream  spilt() 메소드 사용
	public static void main(String[] args) {
		try(Scanner sc = new Scanner(new File("src\\com\\day11\\aa.txt"));
				PrintStream ps = new PrintStream("bb.txt")	){
			while(sc.hasNext()) {
				String str = sc.nextLine();
				String[] tmp = str.split(":");
				for(int i=0; i<tmp.length;i++) {
					//System.out.println(tmp[i]);
				    String hex = 	Integer.toHexString(Integer.parseInt(tmp[i]));
				    ps.print(hex.toUpperCase()+"  ");
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	

}
