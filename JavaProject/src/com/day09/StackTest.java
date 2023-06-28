package com.day09;

import java.util.ArrayList;

//p419
class MyStack{
	private ArrayList<String> arrayStack= new ArrayList<String>();
	public void push1(String data) {
		arrayStack.add(data);  // A B C
	}
	public String pop1() {
		int len = arrayStack.size();//2
		if(len==0) {
			System.out.println("스택이 비어 있음");
			return null;
		}
		return arrayStack.remove(len-1);//1
	}
}
public class StackTest {
	public static void main(String[] args) {
		MyStack stack = new MyStack();
		stack.push1("A");
		stack.push1("B");
		stack.push1("C");
		System.out.println(stack.pop1());
		System.out.println(stack.pop1());
		System.out.println(stack.pop1());
	}

}
