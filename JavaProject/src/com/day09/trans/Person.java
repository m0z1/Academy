package com.day09.trans;

import java.util.ArrayList;

public class Person {
	private String studentName;
	private int money;
	//private Trans[] arr = new Trans[10];
	ArrayList<Trans> alist = new ArrayList<>();  
	//private int cnt;
	public Person(String name,  int money) {
		studentName= name;
		this.money = money;
	}
	public void take(Trans trans, int unit) {
		trans.take(unit);
		this.money -= unit;
		//arr[cnt++] = trans;
		alist.add(trans);
	}
	public void showInfo() {
//		for(Trans t : arr) {
//			if(t==null) break;
//			System.out.println(t);
//		}
		
//		for(int i =0;i<alist.size();i++) {
//			System.out.println(alist.get(i));
//		}
		for(Trans t : alist) {
			System.out.println(t);
		}
		
		System.out.println(studentName + " 잔액 :" + money);
	}

}
