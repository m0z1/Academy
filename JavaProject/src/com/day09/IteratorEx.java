package com.day09;

import java.util.Iterator;
import java.util.Vector;

public class IteratorEx {
	public static void main(String[] args) {
		Vector<Integer> vc = new Vector<Integer>();
		vc.add(5);
		vc.add(-1);
		vc.add(new Integer(4));
		for(Integer i : vc) {
			System.out.println(i);
		}
		vc.add(2,100);
		System.out.println("===");
		for(Integer i : vc) {
			System.out.println(i);
		}
		System.out.println("===");
	   Iterator<Integer> it = 	vc.iterator();
	   while(it.hasNext()) {
		   System.out.println(it.next());
	   }

	}

}
