package com.day10;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileReaderTest {

	public static void main(String[] args) {
		FileReader fr = null;
		FileWriter fw = null;
		
		try {
			fr = new  FileReader("cap2.txt");
			fw = new FileWriter("cap2_out.txt");
			int i;
			while((i=fr.read())!=-1) {
				System.out.print((char)i);
				fw.write(i);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				fr.close();
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		

	}

}
