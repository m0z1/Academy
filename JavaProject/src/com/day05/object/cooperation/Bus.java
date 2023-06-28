package com.day05.object.cooperation;

public class Bus {
	int busNumber; //버스번호
	int passengerCount; // 승객수
	int money; //  버스 수입
	//생성자
	public Bus(int busNumber) {
		this.busNumber = busNumber;
	}
	//탑승
	public void take(int money) {
		this.money += money; //버스 수입증가
		passengerCount++; //승객 수 증가
		
	}
	//버스정보출력
	public void showInfo() {
		System.out.println(busNumber +
				" 번 의 승객은 " + passengerCount +
				" 명 이고 , 수입은 "+money +"입니다.");
	}
	

}






