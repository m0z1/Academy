package com.day05.object.cooperation;

public class Subway {
	String lineNumber ; // 지하철 노선 번호
	int  passengerCount;  // 승객 수
	int money; // 수입
	//생성자
	public Subway(String lineNumber) {
		this.lineNumber = lineNumber;
	}
	public void take(int money) {
		this.money += money;
		passengerCount++;
		
	}
	public void showInfo() {
		System.out.println(lineNumber +
				" 의 승객은 " + passengerCount +
				" 명 이고 , 수입은 "+money +"입니다.");
	}

}
