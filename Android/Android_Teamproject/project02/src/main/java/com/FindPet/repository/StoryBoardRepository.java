package com.FindPet.repository;

import java.awt.print.Pageable;

import org.springframework.data.domain.Page;

import com.FindPet.model.StoryBoard;

public interface StoryBoardRepository {
	
	/* Story List - 2023-08-18 */
	Page<StoryBoard> findByStoryBoardWhere(Pageable pageable, String string);
	
	/* ------------------------Story Search - 2023-08-18 --------------------------*/
	
	Page<StoryBoard> findByTitle(String word, Pageable pageable);
	Page<StoryBoard> findByContentContaining(String word, Pageable pageable);
	
	/* -----------------------------------------------------------------------------*/

}
