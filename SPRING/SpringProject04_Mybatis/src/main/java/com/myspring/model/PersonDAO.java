package com.myspring.model;

import java.util.HashMap;
import java.util.List;

import com.myspring.vo.Person4;

public interface PersonDAO {
	//추가
	public void dao_insert(String mid, Person4 person);
	//전체보기
		public List<Person4> dao_listAll(String mid);
		public List<Person4> dao_list(String mid,HashMap<String, String>hm);
		//상세보기
		public Person4 dao_view(String mid,String id);
		//수정
		public void dao_update(String mid,Person4 person);
		//삭제
		public void dao_delete(String mid,String id);
		//개수
		public int dao_countAll(String mid);
		public int dao_count(String mid,HashMap<String, String>hm);

}
