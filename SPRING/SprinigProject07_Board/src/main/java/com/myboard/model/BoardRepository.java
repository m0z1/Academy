package com.myboard.model;

import java.util.HashMap;
import java.util.List;

import com.myboard.dto.BoardDTO;

public interface BoardRepository {
	//추가
		public void dao_insert(BoardDTO board);
		//전체보기
		public List<BoardDTO> dao_findAll(HashMap<String, Object> hm);
		//상세보기
		public BoardDTO dao_findByNum(int num);
		//수정
		public void dao_update(BoardDTO board);
		//삭제
		public void dao_delete(int num);
		//개수
		public int dao_getCount(HashMap<String, Object>hm);
		//조회수  증가
		public void dao_upReadCount(int num);
		//파일업로드
		//파일리스트

}
