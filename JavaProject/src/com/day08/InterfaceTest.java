package com.day08;
//도형의 넓이 둘레 구하기
interface ShapeArea{
	double area() ;
	 double circum() ;
}
class Rectangle implements ShapeArea{
	private int x;
	private int y;
	public Rectangle(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public double area() {
		return x*y;
	}
	@Override
	public double circum() {
		return (x+y)*2;
	}
}
class SCircle  implements ShapeArea{
	private int r;
	public SCircle(int r) {
		this.r = r;
	}

	public double area() {
			return r*r*Math.PI;
	}

	public double circum() {
			return 2*r*Math.PI;
	}
	
}
public class InterfaceTest {
	public static void main(String[] args) {
		ShapeArea rec = new Rectangle(5,7);
		ShapeArea circle = new SCircle(5);
		System.out.println("사각형 넓이 : " +rec.area() );
		System.out.println("사각형 둘레 : " +rec.circum() );
		System.out.println("원 넓이 : " + circle.area());
		System.out.println("원 둘레 : " + circle.circum());

	}

}
