package com.day10;

import java.util.Stack;

public class StackTest2 {

	public static void main(String[] args) {
		Stack<String> stack = new Stack<>();
		stack.push("AA");
		stack.push("BB");
		stack.push("CC");
		System.out.println(stack.pop());
		System.out.println(stack.pop());

	}

}
