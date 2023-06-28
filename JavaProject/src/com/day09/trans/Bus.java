package com.day09.trans;

public class Bus  extends Trans{
	private int busNumber;
	public Bus(int busNumber) {
		this.busNumber = busNumber;
	}
	@Override
	public void showInfo() {
		System.out.println(this  + " ==>  번호 : "+busNumber);
		super.showInfo();
	}
	@Override
	public String toString() {
		return  "버스";
	}

}
