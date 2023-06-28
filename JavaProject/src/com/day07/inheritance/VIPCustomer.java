package com.day07.inheritance;
//p236
public class VIPCustomer
    extends Customer{
	private int agentID;
	double saleRate; //할인율
	
	public VIPCustomer(int customerID,
			String customerName,
			int agentID) {
		super(customerID,customerName);
		this.agentID = agentID;
		saleRate = 0.1;
		super.customerGrade ="VIP";
		super.bonusRate = 0.05;
		
	}
	@Override  //  가격 계산
	public int calPrice(int price) {
		bonusPoint += price*bonusRate;
		return price - (int)(price*saleRate);
	}
}
