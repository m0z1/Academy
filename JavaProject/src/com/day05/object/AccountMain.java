package com.day05.object;

public class AccountMain {

	public static void main(String[] args) {
		Account ac1 = new Account("홍길동",6000);
		ac1.inputMoney(5000);
		ac1.outputMoney(17000);//잔액부족하면 출금 안되게
		ac1.getMoneyPrint(); //홍길동님의 잔액은 ???원 입니다.
		System.out.println(ac1.getBalance()); //홍길동님의 잔액은 ???원 입니다.
	   System.out.println(ac1.getName());
	   System.out.println(ac1.getMoney());
	}

}
