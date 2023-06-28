package com.day02;

public class Exam03 {
	public static void main(String[] args) {
		/*
		 * 1부터  10까지의 합  55
		 * 1부터  20까지의 합  ??
		 * 1부터  30까지의 합 
		 * 1부터  40까지의 합 
		 * 
		 * 1부터  100까지의 합  5050
		 */
		int sum=0;
		for(int i=1; i<=100;i++) {
			sum+=i;
			if(i%10==0) {
				System.out.println("1에서 "+ i +"까지의 합 : " + sum);
			}
		}
		System.out.println("======");
		/*
		 * 1부터  10까지의 합  55
		 * 11부터  20까지의 합  155  
		 * 21부터  30까지의 합 
		 * 31부터  40까지의 합 
		 * 
		 * 91부터  100까지의 합  955
		 */
		int hap = 0;
		for(int i =1; i<=100;i++) {
			hap+=i;
			if( i%10 ==0) {  //  10 20 30
				System.out.println((i-9)+"에서 "+ i +"까지의 합 : " + hap);
				hap = 0;
			}
		}
		
	}//main

}//class












