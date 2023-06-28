package com.day07.shape;

public class ShapeManager {
	private Shape[] arr = new Shape[10];
	private int cnt;
   public void inputData(int num) {
	    System.out.println("도형입력");
		System.out.println("x 좌표 >>");
		int x =ShapeMain.sc.nextInt();
		System.out.println("y 좌표 >>");
		int y =ShapeMain.sc.nextInt();
		if(num==1) {//원
			System.out.println(" 반지름 >>");
			int r =ShapeMain.sc.nextInt();
			arr[cnt++] = new Circle(x,y,r);
		}else if(num==2) {//사각형
			System.out.println("가로 폭 >>");
			int w =ShapeMain.sc.nextInt();
			System.out.println("세로 높이>>");
			int  h =ShapeMain.sc.nextInt();
			arr[cnt++] = new Square(x,y,w,h);
		}
   }
 public void viewData() {
	 for(int i = 0; i<cnt;i++) {
		 arr[i].print();
		 System.out.println(arr[i]);
	 }
	   
   }
}



