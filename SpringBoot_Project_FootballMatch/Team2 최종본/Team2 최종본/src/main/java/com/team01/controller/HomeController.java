package com.team01.controller;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.team01.model.Field;
import com.team01.service.FieldService;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class HomeController {
	
	
	private final FieldService fieldService;
	
	/*
	 * @GetMapping("/") public String home(Model model) {
	 * 
	 * Date date = new Date(); SimpleDateFormat format = new SimpleDateFormat("M");
	 * SimpleDateFormat format2 = new SimpleDateFormat("dd"); SimpleDateFormat
	 * format3 = new SimpleDateFormat("E"); List<String> dArr = new ArrayList<>();
	 * List<String> dArr2 = new ArrayList<>(); List<String> dArr3 = new
	 * ArrayList<>();
	 * 
	 * for(int i = 0 ; i < 15 ; i++) { Date date2 = new
	 * Date(date.getTime()+60*60*24*1000*i); dArr.add(format.format(date2));
	 * dArr2.add(format2.format(date2)); dArr3.add(format3.format(date2)); }
	 * model.addAttribute("dArr", dArr); model.addAttribute("dArr2", dArr2);
	 * model.addAttribute("dArr3", dArr3); model.addAttribute("list",
	 * fieldService.list()); return "home"; }
	 */
	
	@GetMapping("/")
	public String home(Model model, @PageableDefault(size = 2) Pageable pageable,
			@RequestParam(required = false, defaultValue = "") String field,
			@RequestParam(required = false, defaultValue = "") String word) {
		Page<Field> fieldlist = fieldService.listPage(pageable, field, word);
		model.addAttribute("list", fieldlist);
		
		return "home";
	}
	
}
