package com.day08;

import java.util.Scanner;

public class SortTest {

	public static void main(String[] args) {
		System.out.println("정렬방식 선택");
		System.out.println("B : BubbleSort");
		System.out.println("H : HeapSort");
		System.out.println("Q : QuickSort");
		
		Scanner sc = new Scanner(System.in);
		String ch = sc.next();
		//
		Sort sort;
		if(ch.toUpperCase().equals("B")) {
			   sort = new BubbleSort();
		}else if(ch.toLowerCase().equals("h")) {
			  sort = new HeapSort();
		}else if(ch.equalsIgnoreCase("q")) {
			 sort = new QuickSort();
		}else {
			System.out.println("지원되지 않는 기능입니다.");
			return;
		}
//		if(ch.equals("B") || ch.equals("b")) {
//			   sort = new BubbleSort();
//		}else if(ch.equals("H") || ch.equals("h")) {
//			  sort = new HeapSort();
//		}else if(ch.equals("Q") || ch.equals("q")) {
//			 sort = new QuickSort();
//		}else {
//			System.out.println("지원되지 않는 기능입니다.");
//			return;
//		}
		int[] arr = new int[10];
		sort.ascending(arr);
		sort.descending(arr);
		sort.description();

	}

}
