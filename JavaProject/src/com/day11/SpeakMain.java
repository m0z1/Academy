package com.day11;
class Saram{
	private String name;
	public Saram(String name) {
		this.name =name;
	}
	public void say() {
		for(int i = 1; i<6 ;i++) {
			System.out.println(name +" 이 "+ i +" 번째 말한다. ");
		}
	}
}

public class SpeakMain {
	public static void main(String[] args) {
		Saram s1 = new Saram("홍길동");
		Saram s2 = new Saram("이순신");
		Saram s3 = new Saram("강감찬");
		s1.say();
		s2.say();
		s3.say();

	}

}
