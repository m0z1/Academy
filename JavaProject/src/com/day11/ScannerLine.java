package com.day11;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class ScannerLine {
	//com.day10.Member.java 파일을 읽어서
	// output3.txt 로 내보내는데

	// 1 : package com.day10;
	// Scanner ,PrintStream 사용
	public static void main(String[] args) {
		int cnt = 1;
		try(Scanner sc = new Scanner(new File("src\\com\\day10\\Member.java"));
				PrintStream ps = new PrintStream("output4.txt")	){
			while(sc.hasNext()) {
				ps.println(cnt++ + sc.nextLine());
				
			}
			
		} catch (FileNotFoundException e) {
				e.printStackTrace();
		}

	}

}
