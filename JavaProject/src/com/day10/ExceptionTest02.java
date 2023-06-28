package com.day10;

public class ExceptionTest02 {

	public static void main(String[] args)  
			        throws ArrayIndexOutOfBoundsException{
		 String[] msg = {"one","two","three"};
		 for(int i=0; i<10;i++) {
			 System.out.println(msg[i]);
		 }

	}

}
