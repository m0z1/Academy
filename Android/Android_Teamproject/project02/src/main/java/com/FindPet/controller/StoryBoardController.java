package com.FindPet.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.FindPet.model.StoryBoard;
import com.FindPet.service.StoryBoardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/storyBoard/*")
public class StoryBoardController {
	
	private final StoryBoardService storyBoardService; // 2023-08-18 add
	
	
	/* Story  Search & List - 2023-08-18 */
	
	@GetMapping("list")
	public Page<StoryBoard> Story_list(Pageable pageable){
		return storyBoardService.Story_list(pageable);
		
	}
	
	@GetMapping("findTitle")
	public void findTitle(@PageableDefault( size = 4) Pageable pageable,
			@RequestParam(required = false, defaultValue = "") String word) {
		
		storyBoardService.title_Story_list(pageable, word);
		
	}
	
	
	@GetMapping("findContent")
	public void findContent(@PageableDefault(size = 4) Pageable pageable,
			@RequestParam(required = false, defaultValue = "") String word) {
		
		storyBoardService.content_Story_list(pageable, word);
	}
	
	/* -----------------------------------------------------------------------------*/

}
