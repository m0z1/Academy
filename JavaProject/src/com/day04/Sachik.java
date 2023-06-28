package com.day04;

public class Sachik {
	//덧셈
	public void sum(int a, int b) {  // a = 20, b= 10
		System.out.println("덧셈 : " + (a+b));
	}
	
	//빨셈
	public void sub(int a, int b) { //a =30, b=10
		System.out.println("빨셈 : " + (a-b));
	}
	
	//곱셈
	public void mul(int i, int j) {
		System.out.println("곱셈 :" + i*j);
	}
	//나누기
	public void div(int i , int j ) {
		System.out.println("나누기 :" + i/j);
	}
	public int div2(int i , int j ) {
		int r = i/j;
		return r;
	}
	public static void main(String[] args) {
		Sachik s1 = new Sachik();
		int r=s1.div2(30,10);
		System.out.println("리턴값 : " + r);
		System.out.println(s1.div2(30,10));
		//System.out.println(s1.sum(10, 10)); 오류발생
		
		
		s1.sum(20,10);  // 20+10
		s1.sub(30,10);
		s1.mul(20,10);
		s1.div(20,10);
		
		//??
	}
	

}







