package com.day05.object;

public class ArrayTest {

	public static void main(String[] args) {
		String[] str = {"one","two","three","four"};
		for(int i = 0; i<str.length; i++) {
			System.out.println(str[i]);
		}
		System.out.println();
		//for-each
		for(String s : str) {
			System.out.println(s);
		}
		//2차원배열
		int[][] tmp = new int[3][4];
		tmp[2][3]=100;
		for(int i=0; i<tmp.length;i++) {
			for(int j=0; j< tmp[i].length;j++) {
				System.out.print(tmp[i][j]+"\t");
			}
			System.out.println();
		}
		int[][] a = {{1,2,3}, {11,22,33}};
		for(int i=0; i<a.length;i++) {
			for(int j=0; j< a[i].length;j++) {
				System.out.print(a[i][j]+"\t");
			}
			System.out.println();
		}
		System.out.println(a[1][1]);
  }

}





