package com.myspring.model;

import java.util.HashMap;
import java.util.List;

public interface GuestDAO {
	//추가
	public void dao_guestInsert(String mid, Guest guest);
	//전체보기(검색포함)
	public List<Guest> dao_guestList(String mid, HashMap<String, String> hm);
	//개수(검색포함)
	public int dao_guestCount(String mid, HashMap<String, String> hm);

}
