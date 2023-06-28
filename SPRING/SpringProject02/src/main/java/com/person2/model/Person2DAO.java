package com.person2.model;

import java.util.List;

public interface Person2DAO {
	//추가
	public void person_insert(Person2 person);
	//전체보기
	public List<Person2> person_list();
	//상세보기
	public Person2 person_view(String id);
	//수정
	public void person_update(Person2 person);
	//삭제
	public void person_delete(String id);
	//개수
	public int person_count();

}
