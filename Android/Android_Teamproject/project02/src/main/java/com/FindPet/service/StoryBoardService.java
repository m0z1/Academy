package com.FindPet.service;

import java.awt.print.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.FindPet.model.StoryBoard;
import com.FindPet.repository.FindBoardRepository;
import com.FindPet.repository.StoryBoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StoryBoardService {
	
	private final StoryBoardRepository storyBoardRepository; // 2023-08-18 add
	
	/* Story List - 2023-08-18 */
	
	public Page<StoryBoard> Story_list(Pageable pageable){
		
		Page<StoryBoard> story_list = storyBoardRepository.findByStoryBoardWhere(pageable, "StoryBoard");
		
		return story_list;
	}
	
	/* ------------------------Story Search(title, content) - 2023-08-18 --------------------------*/
	public Page<StoryBoard> title_Story_list(Pageable pageable, String word){
		
		Page<StoryBoard> title_list = storyBoardRepository.findByTitle(word, pageable);
		
		return title_list;
		
	}
	
	public Page<StoryBoard> content_Story_list(Pageable pageable, String word){
		
		Page<StoryBoard> content_list = storyBoardRepository.findByContentContaining(word, pageable);
		
		return content_list;
		
	}
	
	/* -----------------------------------------------------------------------------*/
}
