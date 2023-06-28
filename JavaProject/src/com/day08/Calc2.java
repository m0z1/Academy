package com.day08;

public   interface Calc2 {
	//인터페이스에 선언된 변수는  final (final 생략가능)
	// 메소드는  추상으로 만들어짐 (abstract   생략가능)
	int  value =5;
	public   int add(int a, int b);
	public   int sub(int a, int b);
	public   double average(int[] a);

}
