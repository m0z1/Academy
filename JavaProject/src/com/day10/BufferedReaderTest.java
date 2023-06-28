package com.day10;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class BufferedReaderTest {

	public static void main(String[] args) {
		 //한줄 입력
		BufferedReader br 
		    = new BufferedReader(new InputStreamReader(System.in));
		PrintStream ps = null;
		String str;
			try {
				str = br.readLine();
			//	ps = new PrintStream("test1.txt");
				ps = new PrintStream(new File("test1.txt"));
				ps.print(str);
				System.out.println(str);
			
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		

	}

}




