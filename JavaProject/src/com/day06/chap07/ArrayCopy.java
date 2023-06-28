package com.day06.chap07;
//p212
public class ArrayCopy {

	public static void main(String[] args) {
		int[] array1 = {10,20,30,40,50};
		int[] array2 = {1,2,3,4,5};
		System.arraycopy(array1, 0, array2, 1, 4);
		for(int i = 0; i<array2.length;i++) {
			System.out.println(array2[i]);
		}
	//p213
		Book[] bookArray1 = new Book[3];
		Book[] bookArray2 = new Book[3];
		bookArray1[0]= new Book("태백산맥","조정래");
		bookArray1[1]= new Book("태백산맥1","조정래1");
		bookArray1[2]= new Book("태백산맥2","조정래2");
		for(int i = 0; i<bookArray1.length;i++) {
			bookArray1[i].showInfo();
		}
		//bookArray1  을 bookArray2에 복사
		System.arraycopy(bookArray1, 0, bookArray2, 0,3);
		for(int i = 0; i<bookArray2.length;i++) {
			bookArray2[i].showInfo();
		}
		System.out.println("=== p214====");
		bookArray1[0].setBookName("나목");
		bookArray1[0].setAuthor("박완서");
		System.out.println("===bookArray1 ===");
		for(int i = 0; i<bookArray1.length;i++) {
			bookArray1[i].showInfo();
		}
		System.out.println("===bookArray2 ===");
		for(int i = 0; i<bookArray2.length;i++) {
			bookArray2[i].showInfo();
		}
		System.out.println();
		for(Book b : bookArray2) {
			b.showInfo();
		}
	}

}





