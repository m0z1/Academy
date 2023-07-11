package com.mysecurity.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.myspring.dto.CustomUser;
import com.myspring.dto.MemberDTO;
import com.myspring.mapper.MemberMapper;

public class CustomUserDetailsService  
                 implements UserDetailsService{
	@Autowired
	private MemberMapper mapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("loadUserByUsername");
	     MemberDTO member = 	mapper.read(username);
	     System.out.println("member : " + member);
	     CustomUser user = new  CustomUser(member);
	     System.out.println("user : " + user);
		return member==null?null : user;
	}

}
