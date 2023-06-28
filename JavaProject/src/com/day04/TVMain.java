package com.day04;
class TV{
	String maker;
	int year;
	int inch ;
	public TV(String  maker, int  year,int  inch) {
		this.maker =maker;
		this.year =year;
		this.inch = inch;
	}
	public TV(String maker, int year) {
		this.maker =maker;
		this.year =year;
	}
	public void show() {
		if(inch ==0) {
			System.out.println(maker + 
					"에서 만든 " + year +"년도형  TV" );
		}else {
			System.out.println(maker + 
					"에서 만든 " + year +"년도형 " + inch +" 인치 TV");//LG에서 만든 2021 년도형 65인치  TV
		}
		
	}
	
}

public class TVMain {

	public static void main(String[] args) {
		 TV tv1 = new TV("LG", 2021, 65); 
		 tv1.show(); //LG에서 만든 2021 년도형 65인치  TV
		 
		 TV tv2 = new TV("삼성", 2020);
		 tv2.show(); //삼성에서 만든 2020 년도형  TV

	}

}
