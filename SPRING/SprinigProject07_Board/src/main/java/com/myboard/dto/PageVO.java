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
		blockPage = 3;   // [이전] 456[다음]
		startPage =  ((currentPage-1)/blockPage)*blockPage+1;  // currentPage : 7 
		endPage = startPage+blockPage-1;     //startPage =7  endPage=9(계산상 마지막)
		if(endPage >  totPage )  endPage = totPage; // totPage 실제마지막 페이지
		
		setCurrentPage(currentPage);
		setEndPage(endPage);
		setStartPage(startPage);
		setBlockPage(blockPage);
		setTotPage(totPage);
	}
	

}
