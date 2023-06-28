package com.day07.inheritance;

class Animal{
	public void move() {
		System.out.println("동물이 움직입니다.");
	}
}
class Human extends Animal{
	@Override
	public void move() {
		System.out.println("사람이 두 발로 걷습니다.");
	}
}
class Tiger extends Animal{
	@Override
	public void move() {
		System.out.println("호랑이가  네 발로 뜁니다.");
	}
}
class Eagle extends Animal{
	@Override
	public void move() {
		System.out.println("독수리가 하늘을 납니다.");
	}
}
public class AnimalTest {
	public void moveAnimal(Animal animal) {
		animal.move();
		if(animal instanceof Human) {
			System.out.println("Human");
		}
		if(animal instanceof Animal) {
			System.out.println("Animal");
		}
	}

	public static void main(String[] args) {
		AnimalTest at = new AnimalTest();
		at.moveAnimal(new Human()); // 사람이 두 발로 걷습니다.
		at.moveAnimal(new Tiger() ); //호랑이가  네 발로 뜁니다
		at.moveAnimal(new  Eagle()); //독수리가 하늘을 납니다
		
		
	}

}
