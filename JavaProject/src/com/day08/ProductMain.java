package com.day08;

import java.util.ArrayList;

//TV  Computer  Audio
class Product{
	protected int price;  //가격
	protected int bonus;  // 가격의 10%
	 public Product(int price) {
		 this.price = price;
		 bonus = price/10;
	 }
}
class TV extends Product{
	public TV(int price) {
		super(price);
		
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "TV";
	}

	
}
class Computer extends Product{
	public Computer(int price) {
		super(price);
	}
	@Override
	public String toString() {
		return "컴퓨터";
	}

}
class Audio extends Product{
	public Audio(int price) {
		super(price);
	}
	@Override
	public String toString() {
		return "오디오";
	}

}
class Buyer{
	private int money;
	private int point;
	//크기가 10인 배열 (arr)생성
	//Product[] arr = new Product[10];
	// ArrayList ==> alist  
	ArrayList<Product> alist = new ArrayList<>();
	//int cnt;

	public Buyer(int money) {
		this.money = money;
	}
	
	public void buy(Product p) { //물품 구매
		//arr[cnt++] = p;
		alist.add(p);
		money-= p.price;
		point+= p.bonus;
	}
	public void show() { // 구매 내역 출력
		int sum = 0;
//		for(int i=0; i<alist.size();i++) {
//			sum += alist.get(i).price;
//			System.out.println(alist.get(i));
//		}
		//for-each
		for( Product p   : alist) {
			sum += p.price;
			System.out.println(p);
		}
//		for(int i=0; i<cnt;i++) {
//			sum += arr[i].price;
//			System.out.println(arr[i]);
//		}
		System.out.println("구매 총액 : " + sum);
		System.out.println("잔액 : " + money);
	}
}
public class ProductMain {
	public static void main(String[] args) {
		Buyer b = new Buyer(1000);
		TV tv = new TV(200);
		Computer com = new Computer(150);
		Audio audio = new Audio(70);
		b.buy(tv);
		b.buy(com);
		b.show();
		System.out.println("===");
		b.buy(audio);
		b.show();
		System.out.println("===");
		Buyer b1 = new Buyer(500);
		b1.buy(com);
		b1.show();
		
		

	}

}






