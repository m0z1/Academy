package com.day03;

public class ArrayTest05 {

	public static void main(String[] args) {
		int[][] tmp = new int[3][4]; //3행 4열
		tmp[0][0] = 100;
		tmp[0][1]=  200;
		tmp[2][2] = 300;
		for(int i = 0; i<tmp.length; i++) {
				for(int j = 0 ; j<tmp[i].length   ;j++) {
					System.out.print(tmp[i][j]+"\t");
			}
				System.out.println();
		}
		System.out.println();
//		 1          2           3 
//		 11      22        33
//		 111   222     333
//		 1111 2222 3333
		int[][] a = {{1,2,3},{11,22,33},{111,222,333},{1111,2222,3333}};
		for(int i=0; i<a.length;i++) {
			for(int j=0; j<a[i].length;j++) {
				System.out.print(a[i][j]+"\t");
			}
			System.out.println();
		}
		
		
		

	}

}







