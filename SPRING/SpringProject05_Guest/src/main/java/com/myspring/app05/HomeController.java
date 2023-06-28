package com.myspring.app05;

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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.myspring.model.Guest;
import com.myspring.model.GuestService;

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
	@PostMapping("gInsert")
	public String insert(@ModelAttribute Guest guest,HttpServletRequest request) {
		guest.setIpaddr(request.getRemoteAddr());
		service.guestInsert(guest);
		return "redirect:gList";
	}
	@GetMapping("gList")
	public String list(@RequestParam( value = "field",  required = false )  String field, 
			@RequestParam(value = "word", required = false)  String word, Model model) {
		HashMap<String, String> hm = new HashMap<>();
		hm.put("field",field );
		hm.put("word",word );
		List<Guest> arr   =service.guestList(hm);
		int count = service.guestCount(hm);
		model.addAttribute("guests", arr);
		model.addAttribute("count", count);
		return "list";   //list.jsp
	}
	
}


