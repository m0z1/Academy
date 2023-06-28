package com.day07.shape;

public class Shape {
	// x, y
   private int x;
   private int y;
  // public Shape() {}
   public Shape(int x, int y) {
	   this.x = x;
	   this.y = y;
   }

   public void print() {
   	System.out.println("좌표(x,y)=("+x +"," + y+")");
   }
}
