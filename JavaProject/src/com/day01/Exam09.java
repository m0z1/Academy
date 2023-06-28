package com.day01;

public class Exam09 {
	public static void main(String[] args) {
		int n1 = 5;
		int n2 =++n1;
		System.out.println("n1 : " + n1); //6
		System.out.println("n2 : " + n2); //6
		
		int n3 = n1++;
		System.out.println("n1 : " + n1); //7
		System.out.println("n3 : " + n3); //6
		System.out.println("=======");
		
		int num1 = 7;
		int num2 = --num1;
		System.out.println("num1 : " + num1); //6
		System.out.println("num2 : " + num2); //6
		
		int num3 = num1--;
		System.out.println("num1 : " + num1); //5
		System.out.println("num3 : " + num3); //6
		
		int a =50 ,  b= 3;
		int c;
		a++;  // a=51
		++b; //b =4
		c=a++ + ++b; //  a=51    b=5   c=56    a=52
		System.out.println("a : " + a);   //  52
		System.out.println("b : " + b);  // 5
		System.out.println("c : " + c);  //56
		
		int aa = 10, bb=3;
		int cc;
		--aa;  //aa = 9
		bb++; //bb=4
		cc = aa-- + ++bb;  //aa=9 bb=5  cc=14  aa=8
		System.out.println("aa : " + aa); //8
		System.out.println("bb : " + bb); //5
		System.out.println("cc : " + cc);  //14
		
		int su =1;
		su = su + 1;
		su += 1;   su+=2;  // su = su+2
		su++;
			
	}

}






