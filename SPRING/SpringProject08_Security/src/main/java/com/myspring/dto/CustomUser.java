package com.myspring.dto;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CustomUser extends User{
	private MemberDTO member;
	
	public CustomUser(MemberDTO vo) {
		super(vo.getUserid(),
				vo.getUserpw(),
				vo.getAuthList().stream()
				.map(auth->new SimpleGrantedAuthority(auth.getAuth()))
				.collect(Collectors.toList())
				); //»ý¼ºÀÚ
		member = vo;
	}

	


}
