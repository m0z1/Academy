package com.day05.object;

public class Student {
	private String name;
	private int kor, eng, math;
	private int rank=1;
	public Student(String name, int kor, int eng, int math) {
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
	}
	//getter
	public String getName() {
		return name;
	}
	public int getKor() {
		return kor;
	}
	public int getEng() {
		return eng;
	}
	public int getMath() {
		return math;
	}
	
  //총점 getTotal()
	public int getTotal() {
		return kor+eng+math;
	}
	//평균 getAvg()
	public double getAvg() {
	    return 	(kor+eng+math)/3.0;
	}
	//석차 rank 1   증가
	public void setRank() {
		this.rank++;
	}
	
	
	
	
	
	
	
	
	
	
}
