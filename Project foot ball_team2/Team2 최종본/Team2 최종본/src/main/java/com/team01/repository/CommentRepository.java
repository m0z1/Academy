package com.team01.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.team01.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{
	
	@Modifying
	@Query(value = "insert into project_comment1(comment_comment, comment_regdate) values(?1, now())", nativeQuery = true)
	public void insert(Comment comment);
	
	public List<Comment> findByTeam_TeamId(Long teamId);


}
