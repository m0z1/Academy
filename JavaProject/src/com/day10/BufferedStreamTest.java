package com.day10;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

//p545
public class BufferedStreamTest {

	public static void main(String[] args) {
		long millisecond = 0;
		try(FileInputStream fis = new FileInputStream("cap2.txt");
				FileOutputStream fos = new FileOutputStream("cap4.zip");
				BufferedInputStream bis = new BufferedInputStream(fis);
				BufferedOutputStream bos = new BufferedOutputStream(fos)){
			millisecond = System.currentTimeMillis();  // 복사 전 시각
			int i ;
			while((i=bis.read())!=-1) {
				bos.write(i);
			}
			millisecond = System.currentTimeMillis() - millisecond; //복사에 걸린 시간
		} catch (FileNotFoundException e) {
				e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("복사시간 : " +millisecond +" millisecond  소요" );

	}

}



