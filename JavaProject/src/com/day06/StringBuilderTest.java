package com.day06;

public class StringBuilderTest {

	public static void main(String[] args) {
		String str = "Hi.. Glad to meet you!!";
		StringBuilder sb = new StringBuilder(str);
		System.out.println(sb);
		System.out.println(sb.reverse());
		System.out.println(sb);
		sb.append("java");
		System.out.println(sb);
		sb.insert(2, "   oracle   ");
		System.out.println(sb);
		sb.replace(1, 3, "database");
		System.out.println(sb);
		sb.delete(1, 4);
		System.out.println(sb);
		System.out.println(sb.charAt(2));

	}

}





