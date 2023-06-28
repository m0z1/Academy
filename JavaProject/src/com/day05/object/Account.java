package com.day05.object;

public class Account {
	private String name;
	private int money;
	public Account(String name , int money) {
		this.name  =name;
		this.money = money;
	}
	//입금 = 잔액 + 입금할 돈
	public void inputMoney(int money) {
		this.money += money;
	}
	//출금 = 잔액 - 출금할 돈
	public void outputMoney(int money) {
		if(this.money  <  money) {
			System.out.println(name + "님 잔액부족");
			return; //종료
		}
		this.money -= money;
	}
	public void getMoneyPrint() {
		System.out.println(name +" 잔액 " + money);
	}
	public String  getBalance() {
		return   name + "님 의 잔액 " + money +"원 입니다";
	}
	//getter
	//name  접근
	public String getName() {
		return name;
    }
	//money  접근
	public int getMoney() {
		return money;
	}
	

}










