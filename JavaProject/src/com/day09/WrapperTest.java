package com.day09;
//p375
/*
 *  boolean == > Boolean
 *  int ===> Integer
 *  char ==>Character
 *  short ==> Short
 *  long ==> Long
 *  double ==>Double
 *  float==> Float
 *  byte==>Byte
 */
public class WrapperTest {
	int a;
	public void setValue(Integer i) {  // i(Integer) = 20(int);
		this.a = i;  // int  !=   Integer
	}
	public Integer returnValue() { 
		return 5;  // Integer(리턴형)  <-  5(int)
	}
	public static void main(String[] args) {
		int value =7;
		Integer val = new Integer(value);
		WrapperTest test = new WrapperTest();
		test.setValue(20);
        System.out.println(test.returnValue());
		
	}

}




