package com.day11;

public class ThreadGugu01  extends Thread{
	private int dan;
	public ThreadGugu01(int dan) {
		this.dan = dan;
	}
	@Override
	public void run() {
		for(int i =1; i<10;i++) {
			System.out.println(dan+"*" +i+"="+dan*i);
			try {
				Thread.sleep(1000);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		ThreadGugu01 tg1 = new ThreadGugu01(5);
		ThreadGugu01 tg2 = new ThreadGugu01(7);
		ThreadGugu01 tg3 = new ThreadGugu01(9);
		tg1.start();
		tg2.start();
		tg3.start();
		
		

	}

}
