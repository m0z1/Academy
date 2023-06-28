package com.day07;

public class Child  extends Father{
	public Child() {
		System.out.println("Child  생성자");
	}
	public void childMethod() {
		System.out.println("childMethod");
	}
	@Override
	public void method() {
		System.out.println("child 가 method 수정함 ");
	}

}
