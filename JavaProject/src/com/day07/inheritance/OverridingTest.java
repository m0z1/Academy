package com.day07.inheritance;
//p252, 253
public class OverridingTest {
	public static void main(String[] args) {
		Customer customerLee 
		   = new Customer(10010,"이순신");
		customerLee.bonusPoint = 1000;
		
		VIPCustomer customerKim 
	     = new VIPCustomer(10020,
	    		 "김유신",12345);
	 customerKim.bonusPoint=10000;
	 
	 int price =10000;
	 System.out.println(customerLee.getCustomerName() 
			 +"님이 지불해야 하는 금액은 " +
		       customerLee.calPrice(price) +"원 입니다.");
  System.out.println(customerKim.getCustomerName() 
		  +"님이 지불해야 하는 금액은 " +
				 customerKim.calPrice(price) +"원 입니다.");
//253
  Customer vc = new VIPCustomer(10030,"나몰라", 2000);
  vc.bonusPoint=1000;
  System.out.println(vc.getCustomerName() +"님이 지불해야 하는 금액은 " +
	       vc.calPrice(price) +"원 입니다.");  
  System.out.println("==========");
	 System.out.println(customerLee.showCustomerInfo());
	 System.out.println(customerKim.showCustomerInfo());
	 System.out.println(vc.showCustomerInfo());
  
  
  
	 
	 
	 
	 
	}

}
