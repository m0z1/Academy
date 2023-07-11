package com.myspring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myspring.dto.BoardDTO;
import com.myspring.mapper.BoardMapper;

@Service
public class BoardService {
	@Autowired
	private BoardMapper bmapper;
	//�߰�
	public void insert(BoardDTO board) {
		bmapper.insert(board);
	}
	
	//��ü����
	public List<BoardDTO> list(){
		return bmapper.list();
		
	}
	//�󼼺���
	public BoardDTO view(int num) {
		return bmapper.view(num);
	}
	

}
