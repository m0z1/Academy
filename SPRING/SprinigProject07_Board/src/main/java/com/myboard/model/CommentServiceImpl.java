package com.myboard.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myboard.dto.CommentDTO;
import com.myboard.mapper.BoardMapper;
import com.myboard.mapper.CommentMapper;

@Service
public class CommentServiceImpl  implements CommentService{
  @Autowired
  private CommentMapper cMapper;
  @Autowired
  private BoardMapper bMapper;
  
    @Transactional
	@Override
	public void insert(CommentDTO comment) {
		bMapper.replyCnt(comment.getBnum(), 1);    //replyCnt 증가
		cMapper.insert(comment); //댓글추가
	}

	@Override
	public List<CommentDTO> getList(int bnum) {
		return cMapper.getList(bnum);
	}

	 @Transactional
	@Override
	public void delete(int cnum) {
		 CommentDTO comment = cMapper.read(cnum);
		bMapper.replyCnt(comment.getBnum(), -1);
		cMapper.delete(cnum);
		
	}

	@Override
	public int getReplyCount(int bnum) {
		return cMapper.getReplyCount(bnum);
	}

}
