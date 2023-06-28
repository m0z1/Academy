package com.day04;
/*
 *  한 계좌는 이름(name) ,잔액(money)
 *  입금하다(inputMoney),
 *  출금하다(outMoney),
 *  잔액확인하다(getMoney)
 */
public class Bank {
	String name;  //이름
	int money;  //잔액
	public Bank() {
		
	}
	public Bank(String name) {
		this.name  = name;
	}
	public Bank(String name, int money) {
		this.name  = name;
		this.money = money;
	}
	//입금
	public void inputMoney(int money) {
		this.money += money;//잔액 = 잔액+입금할 돈
	}
	//출금
	public void outMoney(int money) {
		if( this.money  < money) {
			System.out.println(name +" 잔액 부족");
			return;
		}
		this.money -= money;
		
//		if( this.money  < money) {
//			System.out.println(name +" 잔액 부족");
//		}else {
//			this.money -= money;
//		}
	}
	//잔액확인
	public void getMoney() {
		System.out.println(name +"잔액 " +  money);
	}
	public static void main(String[] args) {
		Bank b1 = new Bank();
		b1.name="홍길동";
		b1.getMoney();  // 홍길동 잔액 0
		b1.inputMoney(5000);
		b1.getMoney();
		b1.outMoney(7000); //홍길동 잔액부족
		b1.getMoney(); //  홍길동잔액 5000
		
		//b2 객체 생성
		//이름 : 강감찬
		// 4000 입금
	  //3000 출금
		//잔액확인 
		Bank b2 = new Bank();
		b2.name = "강감찬";
		b2.inputMoney(4000);
		b2.outMoney(3000);
		b2.getMoney();
		
		Bank b3 = new Bank("이순신");
		b3.getMoney();
		Bank b4 = new Bank("홍길순" , 5000);
		b4.getMoney();
	}

}







