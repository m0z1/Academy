package com.myspring.model;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuestServiceImpl implements GuestService {
	@Autowired
	private GuestDAO dao;

	@Override
	public void guestInsert(Guest guest) {
		dao.dao_guestInsert("insertGuest", guest);

	}

	@Override
	public List<Guest> guestList(HashMap<String, String> hm) {
		return dao.dao_guestList("listGuest", hm);
	}

	@Override
	public int guestCount(HashMap<String, String> hm) {
		return dao.dao_guestCount("countGuest", hm);
	}

}
