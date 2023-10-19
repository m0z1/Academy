package com.team01.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.team01.model.User;
import com.team01.repository.UserRepository;


@Service
public class PrincipalDetail implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("loadUserByUsername");
		User user = userRepository.findByUsername(username);
		if(user == null) return null;
		
		PrincipalUser puser = new PrincipalUser(user);
		return puser;
	}

}
