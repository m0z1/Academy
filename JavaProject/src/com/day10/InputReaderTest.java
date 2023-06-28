package com.day10;

import java.io.IOException;
import java.io.InputStreamReader;

public class InputReaderTest {

	public static void main(String[] args) {
		InputStreamReader isr =
				new InputStreamReader(System.in);
		while(true) {
			  try {
				int i =	isr.read();
				if(i==-1) break;
				System.out.print((char)i);
			} catch (IOException e) {
			
				e.printStackTrace();
			}
		}

	}

}
