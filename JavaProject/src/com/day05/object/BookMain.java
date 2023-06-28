package com.day05.object;

public class BookMain {


	public static void main(String[] args) {
		Book b1 = new Book("어린왕자","생텍쥐페리");
		Book b2 = new Book("춘향전");
		System.out.println("책제목 : " +b1.getTitle());
		System.out.println("저자 : " +b1.getAuthor());
		System.out.println("책제목 : " +b2.getTitle());
		System.out.println("저자 : " +b2.getAuthor());
		b2.setTitle("춘향춘향전");
		System.out.println("책제목 : " +b2.getTitle());

	}

}





