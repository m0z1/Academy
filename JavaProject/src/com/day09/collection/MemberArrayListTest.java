package com.day09.collection;
//p411
public class MemberArrayListTest {

	public static void main(String[] args) {
		MemberArrayList memberArrayList 
		      = new MemberArrayList();
		
		Member memberLee 
		= new Member(1001, "이지원");
		Member memberSon 
		= new Member(1002, "손민국");
		Member memberPark 
		= new Member(1003, "박서훤");
		Member memberHong
		= new Member(1004, "홍길동");
		
		memberArrayList.addMember(memberLee);
		memberArrayList.addMember(memberSon);
		memberArrayList.addMember(memberPark);
		memberArrayList.addMember(memberHong);
		
		memberArrayList.showAllMember();
		boolean flag =memberArrayList
		    .removeMember(memberHong.getMemberId());
		if(flag== true) {
			System.out.println("삭제 성공");
		}else {
			System.out.println(memberHong.getMemberId() + "삭제 실패");
		}
		memberArrayList.showAllMember();
	}

}






