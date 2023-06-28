package com.day10;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileInputTest {

	public static void main(String[] args) {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		
		
		try {
			fis = new FileInputStream("src\\com\\day10\\CapitalApp.java");
			fos = new FileOutputStream("cap2.txt");
			int i ; 
			while(( i = fis.read()) !=-1) {
				System.out.print((char)i);
				fos.write(i);
			}
					
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}








