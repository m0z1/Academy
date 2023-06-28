package com.day09.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MemberArrayList {
	private ArrayList<Member>  arrayList = new ArrayList<>();
  public void addMember(Member member) {
	  arrayList.add(member);
  }
  //보기
  public void showAllMember() {
	  for(Member m : arrayList) {
		  System.out.println(m);
	  }
	  System.out.println();
  }
  //삭제
  public boolean removeMember(int memberId) {
	  Iterator<Member> it = arrayList.iterator();
	  while(it.hasNext()) {
		  Member m = it.next();
		  if(m.getMemberId() == memberId) {
			  it.remove();
			  return true;
		  }
	  }

//	  for(Member m : arrayList) {
//		  System.out.println(arrayList.size());
//		  System.out.println(memberId); 
//		  if(m.getMemberId() == memberId) {
//			  arrayList.remove(m);//ConcurrentModificationException 발생
//			
//		  }
//		  
//	  }

	  ///////////////////
//	  for(int i=0; i<arrayList.size();i++) {
//		  Member member = arrayList.get(i);
//		  if(member.getMemberId() == memberId) {
//			  arrayList.remove(i);
//			  return true;
//		  }
//	  }
	  return false;
	  
  }
}




