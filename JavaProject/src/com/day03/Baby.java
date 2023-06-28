package com.day03;

public class Baby {
	//속성, 행위
	String name;
	int age;
	
	//웃는다 
	public void smile() {
		System.out.println(name  +" 웃는다");
	}
	//운다
	public void cry() {
		
	}
	public static void main(String[] args) {
		Baby  b1  =new Baby();
		b1.name = "홍길동";
		b1.age = 10;
		System.out.println(b1.name);
		System.out.println(b1.age);
		b1.smile();
		System.out.println(b1);
		//노란색의 yourcar
		Car yourcar = new Car();
		yourcar.carColor="노랑";
		System.out.println(yourcar.carColor);
		
	}
	

}






