package com.day04;

import java.util.Scanner;

class Triangle{
	double bottom ;
	double height;
	public Triangle(double bottom, double height) { //10.2,  17.3
		this.bottom = bottom;
	    this.height = height;
	}
	public double  getArea() {
		return bottom*height/2;
	}
	public void setBottom(double bottom) { //7.5
		this.bottom =bottom;
	}
	public void setHeight(double height) {  //11.2
		this.height = height;
	}
}

public class TriangleMain {
	public static void main(String[] args) {
//		Triangle tr1 = new Triangle(10.2, 17.3);
//		System.out.println("삼각형의 넓이 : " + tr1.getArea());
//		tr1.setBottom(7.5);
//		tr1.setHeight(11.2);
//		System.out.println("삼각형의 넓이 : " + tr1.getArea());
		
		//밑변과 높이를 입력받아 삼각형의 넓이 구하기
		Scanner sc = new Scanner(System.in);
		System.out.println("밑변, 높이 입력>>");
		double  b = sc.nextDouble();
		double h = sc.nextDouble();
		//getArea() 사용해서 삼각형 넓이 출력
		//Triangle tr2 = new Triangle(b, h);
		Triangle tr2;
		tr2= new Triangle(b, h);
		System.out.println("삼각형의 넓이  tr2: " + tr2.getArea());

		
		
		
	}

}
