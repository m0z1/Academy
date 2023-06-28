package com.addr.model;

import java.util.ArrayList;

public interface SAddrDAO {
	public void addrInsert(AddrDTO addr);  //추가
	public ArrayList<AddrDTO> addrList();//전체보기(검색없음)
	public AddrDTO addrDetail(int num);//상세보기
	public void addrUpdate(AddrDTO addr);//수정
	public void addrDelete(int num);	//삭제
	//검색
	public ArrayList<AddrDTO> addrSearchList(String field, String word);
	public int addrCount();//개수
	//우편번호검색
	public ArrayList<ZipDTO> addrZipRead(String dong);
	/////////////////
	//file  추가
	public void fileInsert(FileDTO file) ;
	//file  리스트
	public ArrayList<FileDTO> fileList();
	
	
}








