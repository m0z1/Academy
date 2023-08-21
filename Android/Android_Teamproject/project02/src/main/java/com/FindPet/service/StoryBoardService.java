package com.FindPet.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
		
		Page<StoryBoard> story_list = storyBoardRepository.findAll(pageable);
		
		return story_list;
	}
	
	/* ------------------------Story Search(title, content) - 2023-08-18 --------------------------*/
	public Page<StoryBoard> title_Story_list(Pageable pageable, String word){
		
		Page<StoryBoard> title_list = storyBoardRepository.findAllByTitleContaining(word, pageable);
		
		return title_list;
		
	}
	
	public Page<StoryBoard> content_Story_list(Pageable pageable, String word){
		
		Page<StoryBoard> content_list = storyBoardRepository.findAllByContentContaining(word, pageable);
		
		return content_list;
		
	}
	
	/* -----------------------------------------------------------------------------*/
}
