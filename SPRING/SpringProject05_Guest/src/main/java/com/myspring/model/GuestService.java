package com.myspring.model;

import java.util.HashMap;
import java.util.List;

public interface GuestService {
	//추가
	public void guestInsert(Guest guest);
	//전체보기(검색 포함)
	public List<Guest> guestList(HashMap<String, String> hm);
	//개수(검색 포함)
	public int guestCount(HashMap<String, String> hm);

}
