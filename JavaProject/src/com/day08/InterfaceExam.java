package com.day08;
interface PhoneInterface{
	final int TIMEOUT = 10000;
	void sendCall();
	void receiveCall();
	default void printLogo() {//default  메소드는 버전 1.8부터 가능
		System.out.println("*** Phone ***");
	}
}
interface MobileInterface extends PhoneInterface{
	void sendSMS();
	void  receiveSMS();
	
}
interface MP3Interface extends PhoneInterface{
	void play();
	void stop();
}
class PDA{
	public int calculate(int x, int y) {
		return x*y;
	}
}

public class InterfaceExam extends PDA
     implements MobileInterface, MP3Interface{

	public static void main(String[] args) {
		InterfaceExam ex = new InterfaceExam();
		System.out.println("3*5 = " +ex.calculate(3,5));
		ex.printLogo();
		ex.sendCall();
		ex.receiveCall();
		ex.receiveSMS();
		ex.sendSMS();
		ex.play();
		ex.stop();
	}

	@Override
	public void sendCall() {
		 // TIMEOUT = 20000; 오류발생 ,  final 이므로 
		System.out.println("sendCall");
	}
	@Override
	public void receiveCall() {
		System.out.println("receiveCall");
	}

	@Override
	public void sendSMS() {
		System.out.println("sendSMS");
	}

	@Override
	public void receiveSMS() {
		System.out.println("receiveSMS");
	}

	@Override
	public void play() {
		System.out.println("play");
		
	}

	@Override
	public void stop() {
		System.out.println("stop");
		
	}

}




