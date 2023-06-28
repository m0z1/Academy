package com.myspring.model;

import java.util.List;

public interface MPersonDAO {

	public void per_insert(Person3 person);

	public List<Person3> per_list();

	public Person3 per_view(String id);

	public void per_update(Person3 person);

	public void per_delete(String id);

	public int per_count();

}
