package com.day04;

public class Sachik2 {
	int a;
	int b;
	public Sachik2() {
		
	}
	public Sachik2(int a, int b) {
		this.a = a;
		this.b = b;
	}
	
	public void sum() {
		System.out.println("더하기 : " + (a+b));
	}
	public void sum(int num1, int num2) {
		System.out.println("덧셈  : "+(num1+num2));
	}
	public void sub() {
		System.out.println("빼기 : " + (a-b));	
	}
	public void mul() {
		System.out.println("곱하기 : " + (a*b));
	}
	public void divide() {
		System.out.println("나누기 : " + (a/b));
	}
	public int div() {
		return a/b;
	}
	
	public static void main(String[] args) {
		Sachik2 sa1 = new Sachik2(20,10);
		sa1.sum();  //더하기 : 30
		sa1.sub();  //빼기 : 10
		sa1.mul();   //곱하기 :200
		sa1.divide(); // 나누기 : 2
		 Sachik2 sa2 = new Sachik2(56, 8);
		 sa2.sum();
		 System.out.println("나누기 : "+ sa2.div());  // 나누기 :7
		 Sachik2 sa3 = new Sachik2();
		 sa3.sum(5,7);
		
		
	}

}




