package com.jqueryAddress;

import java.util.ArrayList;

public interface JAddressDAO {
	//추가
	public void insert(AddressVO avo);
	//전체보기
	public ArrayList<AddressVO> list();
	//상세보기
	public AddressVO findByNum(int num);
	//수정
	public void update(AddressVO avo);
	//삭제
	public void delete(int num);
	//개수
	public int getCount();
	//검색개수
	public int getCount(String field, String word);
	//검색전체보기
	public ArrayList<AddressVO> searchList(String field, String word);
	//우편번호 검색
	public ArrayList<ZipCodeVO> getZipcode(String dong);
	

}





