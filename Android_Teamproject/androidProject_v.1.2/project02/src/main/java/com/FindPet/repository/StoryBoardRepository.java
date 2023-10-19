package com.FindPet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.FindPet.model.StoryBoard;

public interface StoryBoardRepository extends JpaRepository<StoryBoard, Long>{

	List<StoryBoard> findAllByOrderByStoryIdDesc();

}
