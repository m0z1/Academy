package com.FindPet.controller;

import java.awt.print.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.FindPet.model.MissingBoard;
import com.FindPet.service.MissingBoardService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/missingBoard/*")
@RestController
public class MissingBoardController {
	private final MissingBoardService missingBoardService; // 2023-08-18 add
	
	
	/* Missing  Search & List - 2023-08-18 */
	
	@GetMapping("list")
	public Page<MissingBoard> Missing_List(Pageable pageable){
		return missingBoardService.Missing_list(pageable);
	}
	
	@GetMapping("findDog")
	public void findDog(@PageableDefault(size = 4)Pageable pageable,
			@RequestParam(required = false, defaultValue = "") String field,
			@RequestParam(required = false, defaultValue = "") String word) {
		
		missingBoardService.dog_find_list(pageable, field, word);
	}
	
	
	@GetMapping("findCat")
	public void findCat(@PageableDefault(size = 4)Pageable pageable,
			@RequestParam(required = false, defaultValue = "") String field,
			@RequestParam(required = false, defaultValue = "") String word) {
		
		missingBoardService.cat_find_list(pageable, field, word);
	}
	
	@GetMapping("findEtc")
	public void findEtc(@PageableDefault(size = 4)Pageable pageable,
			@RequestParam(required = false, defaultValue = "") String field,
			@RequestParam(required = false, defaultValue = "") String word) {
		
		missingBoardService.etc_find_list(pageable, field, word);
	}
	
	/* -----------------------------------------------------------------------------*/

	
	
}
