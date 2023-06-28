package com.day10;

import java.io.IOException;

public class InputStreamTest {

	public static void main(String[] args) {
		while(true) {
			try {
				int i =System.in.read();
				if(i==-1) break;
				System.out.print((char)i);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
