package com.day09.trans;

public class Taxi  extends Trans{
	private int taxiNumber;
	public Taxi(int taxiNumber) {
		this.taxiNumber = taxiNumber;
	}
	@Override
	public void showInfo() {
		System.out.println(this + "==>번호 : "+taxiNumber);
		super.showInfo();
	}
	@Override
	public String toString() {
			return "택시";
	}

}
