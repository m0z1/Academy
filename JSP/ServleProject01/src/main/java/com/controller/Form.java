package com.controller;

public class Form {
	private String name;
	private int age;
	private String gender;
	private String job;
	private String[] hobby;
	//생성자
	public Form() {
		
	}
	public Form(String name, int age, String gender, String job, String[] hobby) {
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.job = job;
		this.hobby = hobby;
	}
	//getter ,setter
	public void setName(String name) {
		this.name = name;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public void setHobby(String[] hobby) {
		this.hobby = hobby;
	}
	public String getName() {
		return name;
	}
	public int getAge() {
		return age;
	}
	public String getGender() {
		return gender;
	}
	public String getJob() {
		return job;
	}
	public String[] getHobby() {
		return hobby;
	}
	
	
	


	
	

}
