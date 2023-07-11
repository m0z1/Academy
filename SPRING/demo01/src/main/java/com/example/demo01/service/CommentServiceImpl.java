package com.example.demo01.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo01.dao.CommentMapper;
import com.example.demo01.dto.CommentDTO;

@Service
public class CommentServiceImpl  implements CommentService{
  @Autowired
    private CommentMapper cmapper;
	@Override
	public void insert(CommentDTO comment) {
		cmapper.insert(comment);
		
	}

	@Override
	public List<CommentDTO> getList(int bnum) {
		return cmapper.getList(bnum);
	}

	@Override
	public void delete(int cnum) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getReplyCount(int bnum) {
		// TODO Auto-generated method stub
		return 0;
	}

}
