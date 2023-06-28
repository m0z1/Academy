package com.day10;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopyTest {

	public static void main(String[] args) {
		long millisecond = 0;
		//p497    try-with-resources 
		try(FileInputStream fis = new FileInputStream("cap2.txt");
				FileOutputStream fos = new FileOutputStream("copy.zip")){
			millisecond = System.currentTimeMillis();
			int i ;
			while((i=fis.read())!=-1) {
				fos.write(i);
			}
			millisecond = System.currentTimeMillis() - millisecond;
		} catch (FileNotFoundException e) {
					e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("복사시간 : "+millisecond + " millisecond 소요 " );
	}

}







