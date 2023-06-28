package com.myspring.app07;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.apache.ibatis.annotations.Delete;
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

import com.myboard.dto.BoardDTO;
import com.myboard.model.BoardService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Autowired
	private BoardService bserivce;
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "boardInsert";
	}
	
	@PostMapping("insert")
	public String insert(BoardDTO board) {
		bserivce.insert(board);
		return "redirect:list";
	}
	@GetMapping("list")
	public String list(Model model, String pageNum,
			@RequestParam(name = "field", defaultValue = "") String field,
			@RequestParam(name = "word", defaultValue = "")String word) {
			
		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("field", field);
		hm.put("word", word);
			
		List<BoardDTO> boards = bserivce.findAll(hm);
		int count = bserivce.getCount(hm);
		
		model.addAttribute("boards", boards);
		model.addAttribute("count", count);

		
		return "boardList";
	}
	//상세보기
	@GetMapping("view/{num}")
	public String view(@PathVariable int num,Model model) {
		BoardDTO board = bserivce.findByNum(num);
		model.addAttribute("board", board);
		return "view"; //view.jsp
	}
	//삭제
	@DeleteMapping("delete/{num}")
	@ResponseBody
	public int delete(@PathVariable int num) {
		bserivce.delete(num);
		return num;
	}
	
	@GetMapping("delete/{num}")
	public String delete1(@PathVariable int num) {
		bserivce.delete(num);
		return "redirect:/list";
	}
	//수정폼
	@GetMapping("update/{num}")
	public String update(@PathVariable int num,Model model) {
		BoardDTO board = bserivce.findByNum(num);
		model.addAttribute("board", board);
		return "update"; //update.jsp
	}
	//수정
	@PutMapping("update")
	@ResponseBody
	public String update(@RequestBody BoardDTO board) {
		bserivce.update(board);
		return "success";
	}
	
	
	
	
	
	
}
