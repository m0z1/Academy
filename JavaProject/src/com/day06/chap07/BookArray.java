package com.day06.chap07;
//p208
public class BookArray {

	public static void main(String[] args) {
		//Book 클래스형 객체배열 생성
		Book[] library = new Book[5];
		System.out.println(library); //주소값 출력
		for(int i = 0; i<library.length;i++) {
			System.out.println(library[i]); //배열 초기화 안됨 : null
		}
		System.out.println("----");
		//for-each
		for(Book b    : library) {
			System.out.println(b);
		}
		library[0] = new Book("태백산맥","조정래");
		library[1] = new Book("태백산맥1","조정래1");
		library[2] = new Book("태백산맥2","조정래2");
		library[3] = new Book("태백산맥3","조정래3");
		library[4] = new Book("태백산맥4","조정래4");
		System.out.println("----");
		for(Book b    : library) {
			System.out.println(b);
		}
		for(Book b    : library) {
			b.showInfo();
		}

	}

}







