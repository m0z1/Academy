package com.test.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dto.Board;
import com.test.mapper.TestBoardMapper;

@Service
public class TestBoardService {
	@Autowired
	private TestBoardMapper tboardMapper;
	
	public void insert(Board board) {
		tboardMapper.insert(board);
	}
	
	public Board findByNum(int num) {
		return tboardMapper.findByNum(num);
	}
	
	public List<Board> list(HashMap<String, Object> hm){
		return tboardMapper.list(hm);
	}
	
	public int count(HashMap<String, Object> hm) {
		
		return tboardMapper.count(hm);
	}
	
	public int delete(int num) {
		return tboardMapper.delete(num);
	}
	
	public void update(Board board) {
		tboardMapper.update(board);
	}
	
}
