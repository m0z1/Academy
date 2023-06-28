package com.day03;

public class Car {
	String carName; //차종
	String carColor; //차색깔
	int speed; //속도 
	
	//속도 올리다
	public void speedUp() {
		
	}
  //속도 내리다
	public void speedDown() {
		
	}
	//멈춤
	public void stop() {
		
	}
	public static void main(String[] args) {
		//mycar
		Car mycar = new Car();
		mycar.carColor="red";
		System.out.println(mycar.carColor);
		
		Baby b2 = new Baby();
		b2.name="이순신";
		System.out.println(b2.name);
		b2.smile();
		
		
	}
	
	
	
	
	
	
	
	
	

}
