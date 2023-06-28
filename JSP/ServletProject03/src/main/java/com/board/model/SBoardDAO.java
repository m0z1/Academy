package com.board.model;

import java.util.ArrayList;

public interface SBoardDAO {
	//추가
	public void boardInsert(BoardDTO board);
	//전체보기
	public ArrayList<BoardDTO> boardList();
	public ArrayList<BoardDTO> boardList(String field, String word);
	//상세보기
	public BoardDTO findByNum(int num);
	//수정
	public void boardUpdate(BoardDTO board);
	//삭제
	public void boardDelete(int num);
	//게시글 수
	public int boardCount(String field, String word);
  ////// comment
	//추가
	public void commentInsert(CommentDTO comment);
	//전체보기
	public ArrayList<CommentDTO> commentList(int bnum);
	//개수
	public int commentCount(int bnum);
}








