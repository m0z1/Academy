package com.day09.trans;

public class SubWay  extends Trans{
	private String lineNumber;
	public SubWay(String lineNumber) {
		this.lineNumber = lineNumber;
	};
	@Override
	public void showInfo() {
		 System.out.println(this +"==> 노선 "+ lineNumber);// TODO Auto-generated method stub
		super.showInfo();
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "지하철";
	}

}
