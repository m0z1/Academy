package com.day05.object;

public class Drink {
	private String name;
	private int price, count;
	

	public Drink(String name, int price, int count) {
		this.name = name;
		this.price = price;
		this.count = count;
	}
	public void printDrink() {
		System.out.println(name +"\t" +price +"\t" + count +"\t");
	}
	public int getTotal() {
		return count *price;
	}

	public static void main(String[] args) {
		Drink[] arr = new Drink[50];
		
//		Drink d1 = new Drink("coffee", 3000, 3);
//		Drink d2 = new Drink("녹차", 3500, 5);
//		Drink d3 = new Drink("카페라떼", 5000, 7);
//		arr[0] = d1;  //		arr[1] = d2;  //		arr[2] = d3;
		arr[0] = new Drink("coffee", 3000, 3);
		arr[1] = new Drink("녹차", 3500, 5);
		arr[2] = new  Drink("카페라떼", 5000, 7);
		int sum = 0;
		for(int i=0;i<arr.length;i++) {
			if(arr[i] ==null) break;
			arr[i].printDrink();
			sum += arr[i].getTotal();
		}
		
		System.out.println("총 판매액 : " +sum);

	}

}




