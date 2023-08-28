package com.FindPet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.FindPet.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	// 목격 게시판
	// 전체보기
	@Query(value = "select * from comment where find_id=?1", nativeQuery = true)
	public List<Comment> findAllByFind_id(@Param(value = "find_id") Long find_id);

	// 개수
	@Query(value = "select count(*) from comment where find_id=?1", nativeQuery = true)
	public Integer countAllByFind_id(@Param(value = "find_id") Long find_id);

	// 실종 게시판
	// 전체보기
	@Query(value = "select * from comment where missing_id=?1", nativeQuery = true)
	public List<Comment> findAllByMissing_id(@Param(value = "missing_id") Long missing_id);

	// 개수
	@Query(value = "select count(*) from comment where missing_id=?1", nativeQuery = true)
	public Integer countAllByMissing_id(@Param(value = "missing_id") Long missing_id);

	// 스토리 게시판
	// 전체보기
	@Query(value = "select * from comment where story_id=?1", nativeQuery = true)
	public List<Comment> findAllByStory_id(@Param(value = "story_id") Long story_id);

	// 개수
	@Query(value = "select count(*) from comment where story_id=?1", nativeQuery = true)
	public Integer countAllByStory_id(@Param(value = "story_id") Long story_id);
}
