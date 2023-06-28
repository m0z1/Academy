package com.day09;
class Point{
	//private int x;
	//private int y;
	protected int x, y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public void showPoint() {
		System.out.println("("+x+", "+y+")");
	}
	
}
class  ColorPoint extends Point{
	private String color;
	public ColorPoint(int x, int y, String color) {
		super(x,y);
		this.color =color;
	}
	@Override
	public void showPoint() {
		//System.out.println("("+super.x+", "+super.y+")"+color);
		super.showPoint();
		System.out.println(color);
	}
	
	@Override
	public String toString() {
		return color +"색의 ("+ super.x +"," + super.y+")";
	}
}
public class ColorPointEx {

	public static void main(String[] args) {
		 Point p  = new Point(3,4);
	      p.showPoint() ; //(3,4)
	      ColorPoint cp =  new ColorPoint(2,5,"red");
	      cp.showPoint() ; //(2,5) red
	   	      
	      Point p1 = new ColorPoint(7,9,"yellow");
	      p1.showPoint() ; //(7,9) yellow
	      System.out.println(p1);  //yellow 색의 (7,9)

	}

}
