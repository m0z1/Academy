package com.FindPet.controller;

import java.awt.print.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.FindPet.model.FindBoard;
import com.FindPet.service.FindBoardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/findBoard/*")
public class FindBoardController {
	
	private final FindBoardService findBoardService; // 2023-08-18 add
	
	
	/* Find Search & List - 2023-08-18 */
	
	@GetMapping("list")
	public Page<FindBoard> Find_list(Pageable pageable){
		return findBoardService.Find_list(pageable);
	}
	
	@GetMapping("findDog")
	public void findDog(@PageableDefault(size = 4)Pageable pageable,
			@RequestParam(required = false, defaultValue = "") String field,
			@RequestParam(required = false, defaultValue = "") String word) {
		
		findBoardService.dog_find_list(pageable, field, word);
	}
	
	
	@GetMapping("findCat")
	public void findCat(@PageableDefault(size = 4)Pageable pageable,
			@RequestParam(required = false, defaultValue = "") String field,
			@RequestParam(required = false, defaultValue = "") String word) {
		
		findBoardService.cat_find_list(pageable, field, word);
	}
	
	@GetMapping("findEtc")
	public void findEtc(@PageableDefault(size = 4)Pageable pageable,
			@RequestParam(required = false, defaultValue = "") String field,
			@RequestParam(required = false, defaultValue = "") String word) {
		
		findBoardService.etc_find_list(pageable, field, word);
	}
	
	/* -----------------------------------------------------------------------------*/


}
