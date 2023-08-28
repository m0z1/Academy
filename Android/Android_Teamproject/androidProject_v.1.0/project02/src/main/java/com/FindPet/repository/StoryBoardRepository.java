package com.FindPet.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.FindPet.model.StoryBoard;

public interface StoryBoardRepository extends JpaRepository<StoryBoard, Long>{
	Page<StoryBoard> findAllByTitleContaining(String word, Pageable pageable);
	Page<StoryBoard> findAllByContentContaining(String word, Pageable pageable);
}
