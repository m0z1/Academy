package com.day11;
// Thread 상속받아 구현하기
class Saram1 extends Thread{
	private String name;
	public Saram1(String name) {
		this.name =name;
	}
	@Override
	public void run() {
		for(int i = 1; i<6 ;i++) {
			System.out.println(name +" 이 "+ i +" 번째 말한다. ");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

public class ThreadSpeak01 {

	public static void main(String[] args) {
		Saram1 s1 = new Saram1("홍길동");
		Saram1 s2 = new Saram1("이순신");
		Saram1 s3= new Saram1("강감찬");
		s1.start();
		s2.start();
		s3.start();

	}

}
