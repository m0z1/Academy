package com.day10;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class MemberHashMap {
	//private HashMap<Integer, Member>  hashMap  
	 //                                  = new HashMap<>();
	private  HashMap<Integer, Member>  hashMap ;
	public MemberHashMap() {
		hashMap = new HashMap<>();
	}
	public void addMember(Member mebmer) {
		hashMap.put(mebmer.getMemberId(), mebmer);
	}
	public void showAllMember() {
	//	System.out.println(hashMap);
	//	System.out.println(hashMap.values());
		Set<Integer> set = 	hashMap.keySet();
		Iterator<Integer> it = set.iterator();
		while(it.hasNext()) {
			int key = it.next();
			Member m =  hashMap.get(key);
			System.out.println(m);
		}
	}
	//삭제
  public Member  removeMember(int memberId) {
	  if(hashMap.containsKey(memberId)) {
		  Member remem =  hashMap.remove(memberId);
		  return remem;
	  }
	  return null;
  }
	///////
//	public boolean removeMember(int memberId) {
//		if(hashMap.containsKey(memberId)) {
//			hashMap.remove(memberId);
//			return true;
//		}
//		return false;
//	}

}







