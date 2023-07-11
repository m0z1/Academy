package com.example.demo01.service;

import java.util.List;

import com.example.demo01.dto.BoardDTO;
import com.example.demo01.dto.MemberDTO;

public interface BoardService {
	//추가하기
	public void insert(BoardDTO board);
	//전체보기
	public List<BoardDTO> list();
	//상세보기
	public BoardDTO findByNum(int num);
	//수정
	public void update(BoardDTO board);
	//삭제
	public void delete(int num);
	//개수
	public int getCount();
	////////////
	// 로그인체크
	public MemberDTO loginCheck(String id);

}
