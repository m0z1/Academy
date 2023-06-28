package com.day09.trans;

public class Trans {
	 private int passengerCount;
	  private int money;
	  public void take(int money) {
		  this.money += money;
		  this.passengerCount++;
		  
	  }
	  public void showInfo() {
		   System.out.println("승객 수 : " +passengerCount );
		   System.out.println("수입 : " +money );
		   System.out.println();
	   }

}
