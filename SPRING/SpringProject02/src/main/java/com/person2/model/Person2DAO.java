package com.person2.model;

import java.util.List;

public interface Person2DAO {
	//�߰�
	public void person_insert(Person2 person);
	//��ü����
	public List<Person2> person_list();
	//�󼼺���
	public Person2 person_view(String id);
	//����
	public void person_update(Person2 person);
	//����
	public void person_delete(String id);
	//����
	public int person_count();

}
