package com.test.myapp01;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.dto.Board;
import com.test.dto.Page;
import com.test.service.TestBoardService;


@Controller
public class HomeController {
	
	
	@Autowired
	private TestBoardService tboardService;
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	

	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	
	@GetMapping("insert")
	public String insert() {
		return "boardInsert";
	}
	
	@PostMapping("insert")
	public String insert(Board board) {
		tboardService.insert(board);
		return "redirect:list";
	}
	
	
	@GetMapping("list")
	public String list(Model model, String pageNum,
			@RequestParam(name = "field", defaultValue = "") String field,
			@RequestParam(name = "word", defaultValue = "")String word) {
		
		int currentPage = pageNum==null ? 1: Integer.parseInt(pageNum);
		int  pageSize = 5;
			
		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("field", field);
		hm.put("word", word);
		hm.put("pageStart", (currentPage-1)*pageSize);
		hm.put("pageSize", pageSize);
			
		List<Board> boards = tboardService.list(hm);
		int count = tboardService.count(hm);
		Page page = new Page(count, currentPage, pageSize);
		page.setField(field);
		page.setWord(word);
		
		model.addAttribute("rowNo", count-((currentPage-1)*pageSize));
		model.addAttribute("boards", boards);
		model.addAttribute("count", count);
		model.addAttribute("p", page);
	
		return "boardList";
	}
	@GetMapping("view/{num}")
	public String view(@PathVariable int num, Model model) {
		Board board = tboardService.findByNum(num);
		model.addAttribute("board", board);
		return "view";
	}
	
	@DeleteMapping("delete/{num}")
	@ResponseBody
	public String delete(@PathVariable int num) {
		tboardService.delete(num);
		return "redirect:/list";
	}
	
	@GetMapping("update/{num}")
	public String update(@PathVariable int num , Model model) {
		Board board = tboardService.findByNum(num);
		model.addAttribute("board",board);
		return "update";
	}
	
	@PutMapping("update")
	@ResponseBody
	public String update(@RequestBody Board board) {
		tboardService.update(board);
		return "success";
	}
	
	
	
	
}
