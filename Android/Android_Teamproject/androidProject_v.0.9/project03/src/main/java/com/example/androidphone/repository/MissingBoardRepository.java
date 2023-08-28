package com.example.androidphone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.androidphone.model.MissingBoard;


public interface MissingBoardRepository extends JpaRepository<MissingBoard, Long>{

}
