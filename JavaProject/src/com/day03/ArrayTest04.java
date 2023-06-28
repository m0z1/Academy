package com.day03;

public class ArrayTest04 {

	public static void main(String[] args) {
		// Math.random()   0 에서1사이  double  형 난수발생
		// 0에서 50사이의 정수형 난수를 발생
	//	System.out.println((int)(Math.random()*50));
	//	int r = (int)(Math.random()*50);
	//	System.out.println(r);
		/*
		 *   크기가 10인 배열을 만들고
		 *   0에서 50 사이 난수를 발생하여
		 *   0이 아닌 값을 배열에 넣으세요
		 *   중복허용
		 */
		int[] arr = new int[10];
//		for(int i =0 ;i <arr.length;i++) {      // 1     2    3     4     5     6    7
//			int r = (int)(Math.random()*50);//10  42  46  37  29  0    18
//			System.out.println(r);
//			if(r==0) {
//				i--;
//				continue;
//			}
//			arr[i] = r;                                         //10     42    46   37   29        18  37 10 40
//		}
		int n = 0;
		while(n<arr.length) {
			int r = (int)(Math.random()*50) ;
			if(r==0) continue;
			//arr[n] = r;
			//n++;
			arr[n++] = r;
		}
		//데이터 출력
		for(int i = 0; i<arr.length;i++) {
			System.out.println("arr["+i +"] = " + arr[i]);
	    }
		System.out.println();
		for(int i : arr) {
			System.out.println(i);
		}
	}

}





