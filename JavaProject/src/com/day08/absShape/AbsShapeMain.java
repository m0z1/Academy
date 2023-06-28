package com.day08.absShape;

public class AbsShapeMain {
	public static void main(String[] args) {
		AbsCircle circle = new AbsCircle();
		circle.draw(); // 원그리기
		circle.print(); //도형출력하기
		circle.move();//원이동하기
		AbsShape square = new AbsSquare();
		square.move();//사각형이동하기
		square.print();//도형출력하기
		square.draw();//사각형그리기
		//AbsShape shape = new AbsShape();
		// 오류발생 추상클래스는 객체 생성 못함
		AbsShape as = new AbsCircle();
		as.draw();
		as.move();
		
	}

}
