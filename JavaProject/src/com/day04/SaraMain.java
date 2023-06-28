package com.day04;

public class SaraMain {
	public static void main(String[] args) {
		Saram s1 = new Saram();
		//s1.name="홍길동";
		s1.setName("홍길동");
		//s1.addr ="부산";
		s1.setAddr("부산");
		//s1.tel = "010-1111-2222";
		s1.setTel("010-1111-2222");
		s1.showInfo();  // 홍길동의 주소 : 부산
		                                // 홍길도의 전화번호 : 010-1111-2222
		
		Saram s2 = new Saram("이순신", "서울","010-2222-3333");
		s2.showInfo();
		//이순신의 주소 : 서울
		//이순신의 전화번호 : 010-2222-3333

	}

}
