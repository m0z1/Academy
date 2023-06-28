package com.day05.object;

public class ArrayExam {
	//배열 합계
	private void  sum(int[] arr) {
		int hap =0;
		for(int i =0; i<arr.length;i++) {
			hap += arr[i];
		}
		System.out.println("합계 : " + hap);
		arr[0] = 100;
	}
	//배열 최대값
	private int max(int[] arr) {
		int mx = arr[0];
		for(int i =1; i<arr.length;i++) {
			if(mx < arr[i]) {
				mx = arr[i];
			}
		} //for
		return mx;
	}
	
	//배열 최소값
	private int min(int[] arr) {
		int mn = arr[0];
		for(int i=1; i<arr.length;i++) {
			if(mn > arr[i]) {
				mn = arr[i];
			}
		}//for
		return mn;
	}
	private void test(int num) {
		num = 60;
		System.out.println("test num : " + num);
	}

	public static void main(String[] args) {
		int[] arr = {10,5,3,36,45,9};
		ArrayExam object = new ArrayExam();
		object.sum(arr);
		int m = object.max(arr);
		System.out.println("최대값 : " + m);
		System.out.println("최소값 : " +object.min(arr));
		for(int i=0; i<arr.length;i++) {
			System.out.println(arr[i]);
		}
		int num = 5;
		object.test(num);
		System.out.println("main num : " + num);

	}

}





