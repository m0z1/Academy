package com.day04;

public class Person {
	// 이름(name) 주소(addr) 전화번호(tel)
	String name;  //멤버변수
	String addr;
	String tel;
	
	//study() , 	//play()
	public void study() {
		System.out.println(name + " 공부한다.");
	}
	public void play() {
		System.out.println(name + " 운동한다.");
	}
	
	// p1 객체 생성해서
	//  이름 : 홍길동
	// 전화 : 010 - 1111 -2222
	
	// 홍길동은 공부한다.
	// 홍길동 운동한다.
    // 부산에 사는 홍길동 전화번호는  010 - 1111 -2222
	public static void main(String[] args) {
		Person p1 = new Person();
		p1.name="홍길동";
		p1.tel =" 010 - 1111 -2222";
		p1.study();
		p1.play();
		p1.addr="부산";
		System.out.println(p1.addr +" 에 사는 " 
		       +p1.name +" 전화번호는  "+p1.tel);
		
		Person p2 = new Person();
		p2.name="김자바";
		p2.study();
		
		
	}
}







