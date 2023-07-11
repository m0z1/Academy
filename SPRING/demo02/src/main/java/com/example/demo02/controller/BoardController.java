package com.example.demo02.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	@GetMapping("insert")
	@PreAuthorize("isAuthenticated()")
	public void insert() {
		
	}

}
