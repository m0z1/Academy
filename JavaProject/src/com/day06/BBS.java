package com.day06;

public class BBS {
	private String name, date, title, content;
	private  static int no;
	public BBS(String name, String date, String title, String content) {
		this.name = name;
		this.date = date;
		this.title = title;
		this.content = content;
	}
	public void print() {
		System.out.println("번호 : " + ++BBS.no);
		System.out.println("이름 : " + name);
		System.out.println("작성일 : " + date);
		System.out.println("제목 : " + title);
		System.out.println("내용 : " + content);
		System.out.println();
	}

	public static void main(String[] args) {
		BBS b1 = new BBS("홍길동", "2021","제목1","내용1");
		BBS b2 = new BBS("홍길동2"," 2022","제목2","내용2");
		BBS b3 = new BBS("홍길동3", "2020","제목3","내용3");
		BBS b4 = new BBS("홍길동4", "2023","제목4","내용4");
		b1.print();
		b2.print();
		b3.print();
		b4.print();

	}

}
