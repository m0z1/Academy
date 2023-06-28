package com.day04;
//p173

class Person1{
	String name;
	int age;
	public Person1() {
		this("이름없음", 1);
	}
	public Person1(String name, int age) {
		this.name = name;
		this.age = age;
	}
	public Person1 returnItSelf() {
		return this;
	}
}
public class CallAnotherConst {
	public static void main(String[] args) {
		Person1 noName = new Person1();
		System.out.println(noName.name); //이름없음
		System.out.println(noName.age);  // 1
		Person1   p = noName.returnItSelf();
		System.out.println("p :  " + p);
		System.out.println(noName);
	}

}






