package com.day02;

public class Exam07 {
	public static void main(String[] args) {
		//1부터 10까지 홀수 합
		//1. for if 사용해서
		int sum=0;
		for(int i = 1; i<11;i++) {
			if(i%2==1) {
				sum +=i;
			}
		}
		System.out.println("홀수 합계 : " + sum);
			//2. for만 사용
		int hap=0;
		for(int i =1; i<10; i+=2) {
			hap += i;
		}
		System.out.println("홀수 합계 : " + hap);
	
		//3. for if continue
		int s = 0;
		for(int i = 1; i<11;i++) {
			if(i%2 ==0)  continue;// 짝수면 더하지 않고
			s += i;
		}
		System.out.println("홀수 합계 : " + s);
		//break 사용
		
		int h= 0;
		for(int i = 1; i<11;i++) {
			if(i%2 ==0)  break;// 짝수면 반복문 빠져나오기
			h += i;
		}
		System.out.println("홀수 합계 : " + h);
		
		
		
		
		
		
		
		
		
		
	}

}
