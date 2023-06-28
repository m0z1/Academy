package com.myspring.model;

import java.util.List;

import com.myspring.vo.Person4;

public interface PersonSevice {
	//추가
	public void insert(Person4 person);
	//전체보기
	public List<Person4> listAll();
	public List<Person4> list(String field, String word);
	//상세보기
	public Person4 view(String id);
	//수정
	public void update(Person4 person);
	//삭제
	public void delete(String id);
	//개수
	public int countAll();
	public int count(String field, String word);

}
