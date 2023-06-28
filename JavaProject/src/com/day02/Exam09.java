package com.day02;

public class Exam09 {
	public static void main(String[] args) {
		//2단
		// 3단
		// 4단
		for(int d=2; d<5; d++) {  // d=2           3          4 
			System.out.println(d +"단" );
			for(int i=1; i<10;i++) {  // i=1~9     1~9    1~9
				System.out.println(d+" * "+ i +"="+ d*i);
			}
			System.out.println();
		}
		// 5단
		//6단
		//7단
		for(int i=5 ; i<8;i++) {
			for(int j=1; j<10;j++) {
				System.out.println(i+" * "+ j +"="+ i*j);
			}
		}
		/*
	        *  1*1=1        1*2=2             1*9=9
	        *  2*1=2                                 2*9=18
	        *  
	        *  
	        *  9*1=9                                9*9=81
	        */
		System.out.println("==========");
		for(int i=1; i<10;i++) {
			for(int j=1 ; j<10;j++) {
				System.out.print(i+" * "+ j +"="+ i*j+"\t");
			}
			System.out.println();
		}
		System.out.println("==========");
		  /*
	        *  3*1=3      4*1=4               7*1=7
	        *  3*2=6      4*2=8               7*2=14
	        *  
	        *  
	        *   3*9=27                              7*9=63
	        */
	
		for(int i=1; i<10;i++) {
			for(int j=3; j <8 ; j++) {
				System.out.print(j+" * "+ i+"="+ i*j+"\t");
			}
			System.out.println();
		}
		 System.out.println("==========");
		 /*     3단
			3*1=3
		
			3*9=27
			
			5단
			5*1=5
			
			5*9=45
			
			7단
			7*1=7
			
			7*9=63
	 */
		 for(int i=3; i<8;i+=2) {
			 for(int j=1;j<10;j++) {
				 System.out.println(i+ "*" + j +"="+i*j);
			 }
			 System.out.println();
		 }
		 
		 for(int i=3; i<8;i+=2) {
			 if(i%2==0) continue;
			 for(int j=1;j<10;j++) {
					 System.out.println(i+ "*" + j +"="+i*j);
			 }
			 System.out.println();
		 }
	}

}









