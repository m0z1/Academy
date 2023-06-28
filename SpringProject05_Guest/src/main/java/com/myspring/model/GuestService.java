package com.myspring.model;

import java.util.HashMap;
import java.util.List;

public interface GuestService {
	//추가
	public void guestInsert(Guest guest);
	
	public List<Guest> guestList(HashMap<String, String> hm); // 전체보기(검색포함)
	public int guestCount(HashMap<String, String> hm); //개수(검색포함)
	
}
