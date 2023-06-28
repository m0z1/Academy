package com.day08;

import java.util.ArrayList;
import java.util.List;

class Shape{
	public void draw() {
		System.out.println("Shape");
	}
}
class Circle extends Shape{
	public void draw() {
		System.out.println("Circle");
	}
}
class Triangle extends Shape{
	public void draw() {
		System.out.println("Triangle");
	}
}

public class ShapeTest {
	public static void main(String[] args) {
		//ArrayList<Shape> list = new ArrayList<>();
		List<Shape> list = new ArrayList<>();
		list.add(new Circle());
		list.add(new Triangle());
		list.add(new Shape());
		//출력문    
		for(Shape s   : list) {
			s.draw();
		}
		//Circle
		//Triangle
		//Shape

	}

}
