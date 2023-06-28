package com.day02;

public class Exam10 {

	public static void main(String[] args) {
		/*
		 * 1부터  10까지의 합  55   // 1 2 3 4 5 6 7 8 9 10
		 * 11부터  20까지의 합  155    //  11 12 13            20
		 * 21부터  30까지의 합 
		 * 31부터  40까지의 합 
		 * 
		 * 91부터  100까지의 합  955
		 */
		
		//1. for문 하나로 표현
		int sum=0;
		for(int i =1; i<101; i++) {
			sum+=i;
			if(i%10 ==0 ) {  // i=10,20,30
				System.out.println((i-9)+"부터  "+ i +"까지의 합 " + sum);
				sum = 0;
			}
		}
		System.out.println("===========");
		//2. 이중for문으로 표현
		int hap=0;
		for(int i =1 ; i<101;i+=10) {  // i =1              11           21          31   41
			int j;
			for(j =i ; j<i+10; j++ ) {   //j = 1~10    11~20   21~30
				hap+=j;
			}
			System.out.println(i+"부터 " + (j-1) +"까지의 합 : " + hap);
			hap = 0;
			
		}
		System.out.println("===========");
		
		for(int i =1 ; i<101;i+=10) {  // i =1              11           21          31   41
			int h = 0;
			int j;
			for(j =i ; j<i+10; j++ ) {   //j = 1~10    11~20   21~30
				h+=j;
			}
			System.out.println(i+"부터 " + (j-1) +"까지의 합 : " + h);
		
		}
		System.out.println("===========");
		 /*
		    *  123456789
		    *  123456789
		    *  123456789
		    *  123456789
		    */
		for(int i=9; i>5; i--) {  // 9 8 7 6 
			for (int j = 1; j<10;j++) {
				System.out.print(j);
			}
			System.out.println();
		}
		  System.out.println("==========");  	
		  /*
		    *  123456789
		    *  1234567
		    *  12345
		    *  123
		    *  1
		    */
		  for(int i=9; i>0;i-=2) {   // 5번        9   7   5   3  1
			  for (int j=1; j <= i; j++) { //1~9    1~7  1~5  1~3  1
			   System.out.print(j);
			  }
			  System.out.println();
		  }
		  System.out.println("==========");  	
		  int n = 9;
		  for(int i=1; i <6;i++) {
			  for(int j=1; j<=n; j++) {
				  System.out.print(j);
			  }
			  n-=2;
			  System.out.println();
		  }

	}

}
