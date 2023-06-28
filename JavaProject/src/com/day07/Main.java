package com.day07;

public class Main {

	public static void main(String[] args) {
		Father f = new Father();
		f.fatherMethod();
		System.out.println("====");
		Child c = new Child();
		c.childMethod();
		c.fatherMethod();
		c.grandFatehrMethod();
		f.grandFatehrMethod();
		System.out.println("====");
		c.method();
		f.method();
		System.out.println(c.value);
		System.out.println("====");
		GrandFather gf = new GrandFather();
		gf.grandFatehrMethod();
	//	gf.childMethod();  오류발생
		System.out.println("====");
		Father f1;  //선언
		Child c1 = new Child();
		f1 = c1;   // f1 Father 형 , Father = Child
		f1.fatherMethod();
	//	f1.childMethod();//오류발생
		f1.grandFatehrMethod();
		//========================
		Father f2 = new Child();  //f2 Father 형  Child 의 오버라이딩 된 메소드 접근 가능
		f2.method();
		
		
		
		
		
		
		
		
		
		
		

	}

}






