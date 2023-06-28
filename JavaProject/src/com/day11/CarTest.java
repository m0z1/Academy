package com.day11;

import java.util.HashMap;

//p447 6번 연습문제  ==> HashMap 사용해서 
class Car{
	String name;
	public Car() {}
	public Car(String name ) {
		this.name = name;
	}
}
class CarFactory{
	private static CarFactory instance ;
	HashMap<String, Car> carMap =new HashMap<>();
	public static CarFactory getInstance() {
		if(instance == null) {
			instance = new CarFactory();
		}
		return instance;
	}
	public Car  createCar(String name) {
		if(carMap.containsKey(name)) {
			return carMap.get(name);
		}
		Car car = new Car();
		carMap.put(name, car);
		return car;
	}
	
}
public class CarTest {
	public static void main(String[] args) {
		CarFactory factory = CarFactory.getInstance( );
		//CarFactory factory = new CarFactory();
		Car sonata1 = factory.createCar("연수차");
		Car sonata2 = factory.createCar("연수차");
		System.out.println(sonata1 == sonata2); // true
		Car avante1 = factory.createCar("승연차");
		Car avante2 = factory.createCar("승연차");
		System.out.println(avante1 == avante2); // true
		System.out.println(sonata1 == avante1); // false

	}

}
