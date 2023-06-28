package com.day01;

public class Exam01 {
	public static void main(String[] arg) {
		//자바 출력문
		/*
		 자바 여러 줄 
		주석
		*/
//		여기는 설명글입니다.
//		 나이 20
//		 이름 홍길동
//		 주소 (addr) 부산
		System.out.println("Hello");
		System.out.println("안녕");
		int  age =   30;
		String  name  = "홍길동";
		System.out.println("나이= "+age); // 나이 = 20
		System.out.println("이름 = "+name); 	//이름 = 홍길동
		// 주소 (addr) 부산   : 주소 = 부산
		// Ctrl Shift +/- 폰트 조절
		String addr = "부산";
		 addr = "서울";
		System.out.println("주소 = "+addr);
		// 주소를 서울로 수정
		// 숫자 a  에 15 할당
		int a = 15;
		int b = 8;
		 System.out.println(a+b);       //23		
		   // a+b = 23
		 System.out.println("a+b="+ a+b); //문자+15(문자)+8==>(문자)
		 System.out.println("a+b="+ (a+b));
		 
		 System.out.println("a-b="+ (a-b));
		 System.out.println("a*b="+ a*b);
		 System.out.println("a/b="+ a/b);  // 15/8 = 1(몫)
		 System.out.println("a%b="+ a%b); //나머지
		//반지름  r 을 5 로 지정하고 원 넓이 구하기 (반*반*3.14)
		 int r = 5;
		 System.out.println(r*r*3.14);
		 final double  PI = 3.14;
		// PI= 5.24;  final 이므로 오류 발생
		 System.out.println(r*r*PI);
		 
		 double d1 = 3.56;
		 float d2 = 3.56f;
		 /*
		  *  int : 4바이트  (1바이트 -->8비트)
		  *  double(실수 ) : 8바이트
		  *  float(실수) : 4바이트
		  */
		int num;  //선언
		num = 100; //할당
		int su = 200;  //선언&할당
		int sum = num + su;
		System.out.println(sum);
		// 100 + 200 = 300
		System.out.println(num + "+"+su+"="+sum);
		long n = 10000000000L;  //8바이트
		char ch = 'A';  //  문자하나(2바이트- 홑따옴표)
		
		float f = 5.0f;
		int num1 = 10;
		f =  num1;  //  float(4바이트) =  int(4바이트) : 형변환(자동형변환)
		System.out.println("f = " + f);  //10.0
		
		num1 =(int)f;  //   int(4바이트)  =   float(4바이트) :형변환(명시적형변환)
		System.out.println("num1 = " +num1);  
		
		
	}
}










