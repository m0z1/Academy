package com.day08;

public class HeapSort  implements Sort{

	@Override
	public void ascending(int[] arr) {
		System.out.println("HeapSort   ascending");
		
	}

	@Override
	public void descending(int[] arr) {
		System.out.println("HeapSort   descending");
		
	}
	@Override
	public void description() {
		// TODO Auto-generated method stub
		Sort.super.description();
		System.out.println("HeapSort 정렬 알고리즘");
	}

}
