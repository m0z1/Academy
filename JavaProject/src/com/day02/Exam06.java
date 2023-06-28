package com.day02;

public class Exam06 {
	public static void main(String[] args) {
		//1 부터 5까지 출력
		for(int i=1; i<6;i++) {
			System.out.print(i +"\t");
		}
		System.out.println();
		//while
		int n=6;
		while(n<6) {
			System.out.print(n +"\t");
			n++;
		}
		System.out.println();
		//do~while(무조건 1번은 실행)
		int m=6;
		do {
			System.out.print(m +"\t");
			m++;
		}while(m<6);
		System.out.println("\n=======");
		int a =1;
		while(a<6) {
			if(a==3) break;
			System.out.println("a= "+ a);
			a++;
		}
		System.out.println("=======");
		int b=0;
		while(b<6) {
			b++;
			if(b==3) continue;
			System.out.println("b= "+ b);
		}
		for(int i=1; i<10;i++) {
			if (i==5) break;
			System.out.print(i +"\t");
		} 
		System.out.println();
		for(int i=1; i<10;i++) {
			if (i==5) continue;
			System.out.print(i +"\t");
		} 
		System.out.println();
		int t=3;
		while(true) {  //무한루프
			System.out.println("while test");
			if(t==3)break;
		}
		
		
		
		
	}//main

}//class





