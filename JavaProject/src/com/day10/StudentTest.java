package com.day10;

import java.util.HashSet;

//p446 5번문제  <===p357설명
//100:홍길동, 200:강감찬, 300:이순신, 400:정약용
class Student{
	private String id;
	private String name;
	public Student(String id, String name) {
		this.id = id;
		this.name =name;
	}
	@Override
	public String toString() {
			return id +" : "+ name;
	}
	@Override
	public int hashCode() {
			return Integer.parseInt(id);
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Student) {
			Student std = (Student)obj;
			if(this.id == std.id) {
				return true;
			}else {
				return false;
			}
		}
		return false;
	}
	
}
public class StudentTest {
	public static void main(String[] args) {
		HashSet<Student> set = new HashSet<Student>( );
		
		set.add(new Student("100", "홍길동"));
		set.add(new Student("200", "강감찬"));
		set.add(new Student("300", "이순신"));
		set.add(new Student("400", "정약용"));
		set.add(new Student("100", "송중기"));
		
		System.out.println(set);

	}

}
