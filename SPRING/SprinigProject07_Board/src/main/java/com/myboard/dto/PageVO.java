package com.myboard.dto;

import lombok.Getter;
import lombok.Setter;

@Getter  @Setter
public class PageVO {
	private int totPage;
	private int blockPage;
	private int startPage;
	private int endPage;
	private int currentPage;
	private String field;
	private String word;
	
	public PageVO(int count, int currentPage, int pageSize) {  //count =36
		totPage = count/pageSize + (count%pageSize ==0 ? 0: 1); //  8
		blockPage = 3;   // [����] 456[����]
		startPage =  ((currentPage-1)/blockPage)*blockPage+1;  // currentPage : 7 
		endPage = startPage+blockPage-1;     //startPage =7  endPage=9(���� ������)
		if(endPage >  totPage )  endPage = totPage; // totPage ���������� ������
		
		setCurrentPage(currentPage);
		setEndPage(endPage);
		setStartPage(startPage);
		setBlockPage(blockPage);
		setTotPage(totPage);
	}
	

}
