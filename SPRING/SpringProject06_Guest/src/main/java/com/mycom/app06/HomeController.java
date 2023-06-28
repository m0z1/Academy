package com.mycom.app06;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guest.model.GuestListVO;
import com.guest.model.GuestService;
import com.guest.model.GuestVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	private GuestService service;
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
				
		return "insert";
	}
	@PostMapping("insert")
	@ResponseBody
	public String insert(@RequestBody GuestVO guest, HttpServletRequest request) {
		guest.setIpaddr(request.getRemoteAddr());
		service.guestInsert(guest);
		return "success";
	}
	@GetMapping("list")
	@ResponseBody
	public GuestListVO list(@RequestParam(name = "field")  String field,
			  @RequestParam(name = "word")  String word) {
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("field", field);
		hm.put("word", word);
		List<GuestVO>  arr = service.guestList(hm);
		int count = service.guestCount(hm);
		GuestListVO listvo = new GuestListVO(arr, count);
		return listvo;
	}
	@DeleteMapping("delete/{num}")
	@ResponseBody
	public String delete(@PathVariable int num) {
		service.guestDelete(num);
		return "success";
	}
	
//	@PostMapping("delete")
//	@ResponseBody
//	public String delete(int num) {
//		service.guestDelete(num);
//		return "success";
//	}
	
	//상세보기
	@GetMapping("view")
	@ResponseBody
	public GuestVO view(int num) {
	   // guest =	service.findByNum(num);
		//return guest;
		return  service.findByNum(num);
	}
	
	
	
	
	
	
}
