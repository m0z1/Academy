package com.FindPet.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.FindPet.model.StoryBoard;

public interface StoryBoardRepository extends JpaRepository<StoryBoard, Long> {
	
	/* Story List - 2023-08-18 */
	
	/* ------------------------Story Search - 2023-08-18 --------------------------*/
	
	Page<StoryBoard> findAllByTitleContaining(String word, Pageable pageable);
	Page<StoryBoard> findAllByContentContaining(String word, Pageable pageable);
	
	/* -----------------------------------------------------------------------------*/

}
