package com.day05.object;

public class ArrayEx {
	private int[] arr;
	public ArrayEx(int[] arr) {
		this.arr = arr;
	}
	//배열 합계
	private void  sum() {
		int hap =0;
		for(int i =0; i<arr.length;i++) {
			hap += arr[i];
		}
		System.out.println("합계 : " + hap);
		arr[0] = 100;
	}
	//배열 최대값
	private int max() {
		int mx = arr[0];
		for(int i =1; i<arr.length;i++) {
			if(mx < arr[i]) {
				mx = arr[i];
			}
		} //for
		return mx;
	}
	
	//배열 최소값
	private int min() {
		int mn = arr[0];
		for(int i=1; i<arr.length;i++) {
			if(mn > arr[i]) {
				mn = arr[i];
			}
		}//for
		return mn;
	}

	public static void main(String[] args) {
		int[] arr = {10,5,3,36,45,9};
		ArrayEx object = new ArrayEx(arr);
		object.sum();
		int m = object.max();
		System.out.println("최대값 : " + m);
		System.out.println("최소값 : " +object.min());
		for(int i=0; i<arr.length;i++) {
			System.out.println(arr[i]);
		}


	}

}





