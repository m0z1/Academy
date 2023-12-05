package com.team01.controller;


import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.core.ResolvableType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.team01.config.auth.PrincipalUser;
import com.team01.model.Field;
import com.team01.model.SessionUser;
import com.team01.model.User;
import com.team01.service.FieldService;
import com.team01.service.UserService;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class HomeController {
	
	
	private final FieldService fieldService;
	private final UserService userService;
	private final HttpSession httpSession;

	
	@GetMapping("/")
	public String home(Model model, @PageableDefault(size = 10) Pageable pageable,
			@RequestParam(required = false, defaultValue = "") String field,
			@RequestParam(required = false, defaultValue = "") String word,
			@AuthenticationPrincipal PrincipalUser principalUser,
			@AuthenticationPrincipal DefaultOAuth2User defaultOAuth2User) {
		
		System.out.println(">>> Home");
        
		Page<Field> fieldlist = fieldService.listPage(pageable, field, word);
		model.addAttribute("list", fieldlist);
		
		return "home";
	}
    
    @GetMapping("/login/{oauth2}")
    public String loginGoogle(@PathVariable String oauth2
    		, HttpServletResponse httpServletResponse
    		, Model model) {
    	
    	System.out.println("================");
    	System.out.println("  " + oauth2 + " login  ");
    	System.out.println("================");
    	
    	return "redirect:/oauth2/authorization/" + oauth2;
    }
	
}
