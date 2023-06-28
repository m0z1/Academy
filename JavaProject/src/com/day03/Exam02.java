package com.day03;

public class Exam02 {

	public static void main(String[] args) {
		for(char i ='z'; i>='a';i--) {        //i==> z
			for (char j='a'; j<='z'; j++) { //j ==> a b      z
				if(i>j) {   //  z>a  z>b                             z>z
					System.out.print("*");  //**
				}else {
					System.out.print(j);  // z
				}
			}//j
			System.out.println();
		}//i
		System.out.println();
		
		
		
		  /*
	        *      *(1)
	        *      ***(3)
	        *      *****(5)
	        *      *******(7)
	        */  
		for(int i=1; i<8; i+=2) {    // i = 0
			for(int j=1; j<=i; j++) { // j =1 3 5 7
				System.out.print("*");
			}
			System.out.println("("+ i+")");
		}
		/*
		 *    *******(7)
		 *    *****(5)
		 *    ***(3)
		 *    *(1)
		 */
		System.out.println();
		for(int i=7; i>0; i -=2) {  // i = 7 5 3 1
			for(int j=1; j<=i;j++) {                //j =   7  5   3  1
				System.out.print("*");
			}
			System.out.println("("+ i+")");
		}
		System.out.println();
		for(int i=7; i>0; i -=2) {  // i = 7 5 3 1
			for(int j=i; j>0;j--) {                //j =   7  5   3  1
				System.out.print("*");
			}
			System.out.println("("+ i+")");
		}
		System.out.println("===========");
		
		for(char i ='A'; i<'Z';i++) {
			System.out.print((char)(i+1));
		}
	}

}





