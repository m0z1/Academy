package com.day04;

public class Saram {
  private String name;
  private String addr;
  private String tel;
  public Saram() {
	  
  }
  public Saram(String name, 
		  String addr, String tel) {
	  this.name  = name;
	  this.addr = addr;
	  this.tel = tel;
  }
  
  public void showInfo() {
	  System.out.println(name +" 주소 : "+ addr);
	  System.out.println(name +" 전화 : "+ tel);
  }
  //setter
  public void setName(String name) {
	 this.name = name; 
  }
  public void setAddr(String addr) {
	  this.addr = addr;
  }
  public void setTel(String tel) {
	  this.tel = tel;
  }
  

}








