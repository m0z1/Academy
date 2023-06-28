package com.day08;

class TV1{
	private int size;
	public TV1(int size) {
		this.size = size;
	}
	public int getSize() { 	//getter
		return size;
	}
}
class  ColorTV extends TV1{
	private int color;
	public ColorTV(int size, int color) {
		super(size);
		this.color = color;
	}
	public void print() {
		System.out.println(super.getSize() +" 인치 " + color +"컬러");
	}
}

class IPTV extends  ColorTV{
	private String ip;
	public IPTV(String ip, int size, int color) {
		super(size, color);
		this.ip = ip;
	}
	@Override
	public void print() {
		System.out.print("나의  TV 는 " + ip +"주소의 ");
		super.print();
	}
	
}
public class TVMain {
		public static void main(String[] args) {
		ColorTV mytv = new ColorTV(32, 1024);
		mytv.print();//32인치 1024 컬러
		
		ColorTV iptv = new IPTV("192.1.1.2", 32,2048);
		iptv.print(); // 나의 TV 는 192.1.1.2 주소의 32인치 2048 

	}

}
