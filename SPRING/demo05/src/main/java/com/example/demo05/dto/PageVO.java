package com.example.demo05.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PageVO {
	private int totPage;
	private int blockPage;
	private int  startPage;
	private int endPage;
	private int currentPage;
	
	public PageVO(int count,int pageSize,int currentPage) {  
		totPage =count/pageSize + (count%pageSize ==0 ? 0 : 1);   //37   한화면 5   ==> 총 페이지 수 8
		blockPage = 3; // [이전]  456 [다음]                  [이전] 7 8 9[다음]
		startPage =  ((currentPage-1)/blockPage)*blockPage+1;   
		endPage=startPage +blockPage-1 ;  //계산상 마지막 페이지
		if(endPage >  totPage)  endPage = totPage ; //  totPage가  실제 마지막 페이지
		
		setBlockPage(blockPage);
		setStartPage(startPage);
		setEndPage(endPage);
		setCurrentPage(currentPage);
		setTotPage(totPage);
	}

}




