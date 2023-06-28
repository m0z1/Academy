package com.day10;

public class Member {
  private  int memberId;
  private String  memberName;
	//생성자
 public Member(int memberId,  
		   String memberName) {
	 this.memberId = memberId;
	 this.memberName = memberName;
 }
 //getter
public int getMemberId() {
	return memberId;
}
public String getMemberName() {
	return memberName;
}
@Override
	public String toString() {
		return memberName +" 회원님의 아이디는 " 
		     + memberId +" 입니다.";
	}
 
}






