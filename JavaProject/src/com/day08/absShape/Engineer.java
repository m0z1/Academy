package com.day08.absShape;
//p278  4번문제
//오류 발생 ===> 오류 수정하기
class   Employee{
	public String name;
	public String grade;
//	public Employee() {
//		
//	}
	public Employee(String name) {
		this.name = name;
	}
}
public class Engineer  extends  Employee{
	private String skillset;
	public Engineer() {
		super("홍길동");
	}
	public Engineer(String name) {
		super(name);
	}
	public String getSkillset() { 	//getter
		return skillset;
	}
	public void setSkillset(String skillset) { 	// setter
		this.skillset = skillset;
	}
	

}
