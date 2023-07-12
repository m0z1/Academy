package com.test.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Page {
	private int totPage;
	private int blockPage;
	private int startPage;
	private int endPage;
	private int currentPage;
	private String field;
	private String word;
	
	public Page(int count, int currentPage, int pageSize) {  
		totPage = count/pageSize + (count%pageSize ==0 ? 0: 1); 
		blockPage = 3;  
		startPage =  ((currentPage-1)/blockPage)*blockPage+1;  
		endPage = startPage+blockPage-1;     
		if(endPage >  totPage )  endPage = totPage; 
		
		setCurrentPage(currentPage);
		setEndPage(endPage);
		setStartPage(startPage);
		setBlockPage(blockPage);
		setTotPage(totPage);
	}
	

}
