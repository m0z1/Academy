package com.team01.config.auth;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.team01.model.SessionUser;
import com.team01.model.User;
import com.team01.repository.UserRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class PrincipalDetail implements UserDetailsService{

	private final UserRepository userRepository;
	private final HttpSession httpSession;
	
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("loadUserByUsername L " + username);
		User user = userRepository.findByUsername(username);
		SessionUser sessionUser = new SessionUser(user);
		httpSession.setAttribute("user", sessionUser);
		if(user == null) return null;
		
		PrincipalUser puser = new PrincipalUser(user);
		return puser;
	}

}
