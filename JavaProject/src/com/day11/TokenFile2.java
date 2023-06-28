package com.day11;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.StringTokenizer;

public class TokenFile2 {
	//aa.txt 파일을 읽어서 : 구분하여 숫자만 
	// 숫자를 8진수로 변경시켜 cc.txt  내보내기
	 // BufferedReader  PrintStream  StringTokenizer 사용
	public static void main(String[] args) {
	
		try(BufferedReader br 
				  = new BufferedReader(new FileReader("src\\com\\day11\\aa.txt"));
				PrintStream ps = new PrintStream("cc.txt")	){
			String str ;
			while((str = br.readLine())!=null) {
				StringTokenizer st = new StringTokenizer(str,":");
					while(st.hasMoreTokens()){
					    String oct = 	Integer.toOctalString(Integer.parseInt(st.nextToken()));
					    ps.print(oct+"  ");
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
				e.printStackTrace();
		}
	}




}
