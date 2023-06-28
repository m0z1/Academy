package com.day11;

public class LamdaThreadTest {

	public static void main(String[] args) {
		//Runnable runnable = new Runnable();//오류발생
		Runnable  runnable =  new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName());
			}
		};
		new Thread(runnable).start();
		//람다표현식
		Runnable runnable2 = ()->System.out.println(Thread.currentThread().getName());
		 Thread th2 = new Thread(runnable2);
		 th2.start();
		 
		 Runnable  runnable3 = ()->System.out.println(Thread.currentThread().getName());
		 new Thread(runnable3).start();
	}

}













