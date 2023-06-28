package com.day08.absShape;

public   abstract class AbsShape {
	//도형그리기
	public abstract void draw();
	//도형출력하기
	public void print() {
		System.out.println("도형출력하기");
	}
	//도형이동하기
    public abstract void move();
}
