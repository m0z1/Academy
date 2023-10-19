package com.example.androidphone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.androidphone.model.StoryBoard;

public interface StoryBoardRepository extends JpaRepository<StoryBoard, Long> {

}
