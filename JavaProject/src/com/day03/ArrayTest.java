package com.day03;

public class ArrayTest {

	public static void main(String[] args) {
		//5개의 정수 선언
		// a = 1, b=2, c=3, d=4, e=5
		//크기가 5인 int 형 변수를 표현 ==>배열
		int[] arr = new  int[6];  //크기가 6  ==>index 0 1 2 3 4 5
		arr[4] =100;
		arr[1]=30;
		arr[5]=5;
		for(int i=0; i<arr.length;i++) {
			System.out.println(arr[i]);
		}
		//System.out.println(arr[6]);  오류발생
		System.out.println("배열 길이 : " + arr.length);
		
		//정수형인 크기가 5인 tmp 배열 선언
		int[] tmp = new int[5];
	//	int tmp[] = new int[5];
		tmp[0] = 1;
		tmp[1] = 2;
		tmp[2] = 3;
		tmp[3] = 4;
		tmp[4] = 5;
		for(int i=0 ; i<tmp.length;i++) {
			tmp[i] = i+1;
			System.out.print(tmp[i]+"\t");
		}
		
//		int[] arr2 = new int[3];
//		arr2[0] = 100;
//		arr2[1] = 200;
//		arr2[2]=300;
		System.out.println("\n======");
		int[] arr2 = {100,200,300};
		for(int i  : arr2) {
			System.out.println(i);
		}
		
		//arr2  값의 합계출력
		int sum = 0;
		for(int i = 0 ;i <arr2.length;i++) {
			sum +=arr2[i];   //arr2[0] + arr2[1] + arr2[2]
		}
		System.out.println("\n배열합계 : " + sum);
		
		String[] str = {"one","two","three","four"};
		for(int i=0; i<str.length;i++) {
			System.out.println(str[i]);
		}
		String[] str2 = new String[4];
		str2[0] = "딸기0";
		str2[1] = "딸기1";
		str2[2] = "딸기2";
		for(int i=0; i<str2.length;i++) {
			System.out.println(str2[i]);
		}
		str2[3] = "딸기3";
		System.out.println(str2[3]);
	//	str2[4] = "딸기4";
	//	System.out.println(str2[4]); 오류발생
		System.out.println("====");
		//for-each
		for(String s : str2) {
			System.out.println(s);
		}
		 

	}

}





