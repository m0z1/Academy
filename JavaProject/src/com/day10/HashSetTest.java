package com.day10;

import java.util.HashSet;
import java.util.Set;

public class HashSetTest {

	public static void main(String[] args) {
		Set<String> hashset = new HashSet<>();
//		hashset.add(new String("임정순"));
//		hashset.add(new String("임정순1"));
//		hashset.add(new String("임정순2"));
//		hashset.add(new String("임정순3"));
//		hashset.add(new String("임정순3"));
		hashset.add("임정순");
		hashset.add("임정순1");
		hashset.add("임정순1");
		hashset.add("임정순2");
		hashset.add("임정순2");
		System.out.println(hashset);

	}

}
