package com.day11;

import java.io.File;
import java.io.IOException;
//p557
public class FileTest {
	public static void main(String[] args) throws IOException {
		File file = new File("src\\com\\day11\\FileTest.txt");
		file.createNewFile();
		System.out.println(file.isFile());
		System.out.println(file.isDirectory());
		System.out.println(file.getName());
		System.out.println(file.getAbsolutePath());
		System.out.println(file.getPath());
		System.out.println(file.canRead());
		System.out.println(file.canWrite());
		System.out.println(file.delete());

	}

}





