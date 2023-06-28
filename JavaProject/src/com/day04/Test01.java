package com.day04;

public class Test01 {

	public static void main(String[] args) {
		// 로또 1~45까지 6개
		//난수를 발생시켜 6개의 수를 배열에 넣어 출력하시오
		//단, 중복된 숫자가 배열에 들어가면 안됨
		//배열 출력(로또 번호 출력)
	  //int r = 	(int)(Math.random() *45)  +1 ;
		int[] lotto = new int[6]; // 0 1 2 3 4 5
		int n =0;
		while(n <  lotto.length) {                                //n= 0    1                2
			int r = 	(int)(Math.random() *45)  +1;  // r=10  15  15      13
			lotto[n] = r;  //  배열 = 10   15    13
			for(int i =0 ; i< n; i++) {
				if(lotto[i] == r) {   //중복 값 발생   10==10  10==15
					n--;     //n=0       1
					break;
				}
			}
			n++;  //n=1  2    2     3
		}
		
		//for-each
		for(int i   : lotto) {
			System.out.print(i +"\t");
		}
	}
}



