package com.day05.object;

public class Gugudan {
	private int dan;
	private int tot;
	Gugudan(int dan){
		this.dan = dan;
	}
	public void print() {
		System.out.println(dan +" 단");
		for(int i = 1; i<10;i++) {
			System.out.println(dan + "*" + i +"="+ dan*i);
			tot += dan*i;
		}
		System.out.println();
	}
	public int getTot() {
		return tot;
	}

	public static void main(String[] args) {
		Gugudan g1= new Gugudan(3);
		g1.print(); // 3단출력
		
		Gugudan g2= new Gugudan(7);
		g2.print(); // 7단출력
		
		Gugudan g3= new Gugudan(9);
		g3.print(); // 9단출력
		
		Gugudan[] arr = new Gugudan[3];
		arr[0] = g1;
		arr[1] = g2;
		arr[2]= g3;
		int hap =0; 
		for(int i = 0; i<arr.length;i++) {
			hap += arr[i].getTot();
		}
		System.out.println("합 : " + hap);
		 
		
		System.out.println("구구단 총합 :"+(g1.getTot()+g2.getTot()+g3.getTot()));
		
		

	}

}
