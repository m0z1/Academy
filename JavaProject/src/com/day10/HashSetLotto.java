package com.day10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HashSetLotto {

	public static void main(String[] args) {
		//로또번호 6개 출력 (1에서 45까지의 난수 6개 출력)
		Set<Integer> set = new HashSet<>();
		while(set.size()<6) {
			int num =(int)(Math.random()*45)+1;
			set.add(num);
		}
		System.out.println(set);
		 //오름차순으로==>  Collections 의 sort 이용
		//Set ==>List로 
		List<Integer> list = new ArrayList<>(set);
		Collections.sort(list);
		System.out.println("===정렬후===");
		System.out.println(list);
	}

}




