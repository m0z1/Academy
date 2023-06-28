package com.day10;
//p441
public class MemberHashMapTest {

	public static void main(String[] args) {
		MemberHashMap  memberHashMap  = new MemberHashMap();
		
		Member memberLee =new Member(1001, "이지원");
		Member memberSon =new Member(1002, "손인국");
		Member memberPark =new Member(1003, "박서원");
		Member memberHong =new Member(1004, "홍길동");
		
		memberHashMap.addMember(memberLee);
		memberHashMap.addMember(memberSon);
		memberHashMap.addMember(memberPark);
		memberHashMap.addMember(memberHong);
		
		memberHashMap.showAllMember();
	//	boolean flag=memberHashMap.removeMember(1004);
		Member remem = memberHashMap.removeMember(1004);
         if(remem == null) {
        	 System.out.println("삭제실패");
         }else {
        	 System.out.println(remem.getMemberId() + " 삭제성공");
         }
         memberHashMap.showAllMember();
	}

}
