package com.day08.absShape;

public class AbsSquare 
     extends AbsShape {

	@Override
	public void draw() {
		System.out.println("사각형그리기");
		
	}

	@Override
	public void move() {
		System.out.println("사각형이동하기");
		
	}

}
