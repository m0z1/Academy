package com.day06;

public class StringTest03 {

	public static void main(String[] args) {
		String tmp = "1 0 15 23 6 21 17 10 11 12 13 14";
		//tmp 길이
		System.out.println(tmp.length());
		//tmp 마지막 숫자(문자)  ==>  4
		System.out.println(tmp.charAt(tmp.length()-1));
		//tmp 마지막 숫자  ==>  14
		String[] arr = tmp.split(" ");
		System.out.println("배열 길이 : " + arr.length);
		System.out.println(arr[arr.length-1]);
	    for(int i = 0; i<arr.length;i++) {
	    	System.out.print(arr[i]+"\t");
	    }
	    System.out.println("");
	    for( String s  : arr) {
	    	System.out.print(s+"\t");
	    }
	    //배열의 수 합계
	    int sum = 0;
	    for(int i = 0; i<arr.length;i++) {
	    	sum += Integer.parseInt(arr[i]);  //int = String (String ==>int)
	    }
	    System.out.println("\nsum :" + sum);
	    System.out.println("sum 을 16진수로 표현 : "
	            +Integer.toHexString(sum).toUpperCase());
 
	    String hex = "";
	    String bin="";
	    String oct = "";
	    for(int i =0 ; i<arr.length;i++) {
	    	int v = Integer.parseInt(arr[i]);
	    	 hex += Integer.toHexString(v)+" ";
	    	 bin += Integer.toBinaryString(v) +"  ";
	    	 oct += Integer.toOctalString(v)+"  ";
	    }
	    //16진수
	    System.out.println("16진수  :  "+hex);
	    System.out.println("2진수  :  "+bin);
	    System.out.println("8진수  :  "+oct);
	   
	    
	}

}









