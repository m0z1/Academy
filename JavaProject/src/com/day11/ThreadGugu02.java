package com.day11;

public class ThreadGugu02 implements Runnable{
	private int dan;
	public ThreadGugu02(int dan) {
		this.dan = dan;
		this.dan =dan;
	}
	@Override
	public void run() {
		for(int i =1; i<10;i++) {
			System.out.println(dan+"*" +i+"="+dan*i);
		}
	}


	public static void main(String[] args) {
		ThreadGugu02 th1 = new ThreadGugu02(1);
		ThreadGugu02 th2 = new ThreadGugu02(2);
		ThreadGugu02 th3 = new ThreadGugu02(3);
		Thread t1 = new Thread(th1);
		t1.start();
		Thread t2 = new Thread(th2);
		t2.start();
		Thread t3 = new Thread(th3);
		t3.start();

	}

	
}






