package com.team01.service;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.team01.model.Role;
import com.team01.model.SessionUser;
import com.team01.model.User;
import com.team01.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class OpenApiOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

	private final UserRepository userRepository;
	private final HttpSession httpSession;
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

		OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
		OAuth2User oAuth2User = delegate.loadUser(userRequest);
		
		// 서비스 구분
		
		String email;
		String name;
		String provider = userRequest.getClientRegistration().getRegistrationId();
		Map<String, Object> response = oAuth2User.getAttributes();
		
		
		
		if(provider.equals("naver")) {
			response = (Map<String, Object>) response.get("response");
			email = (String) response.get("email");
			name = (String) response.get("name");
			System.out.println(">>> Naver 로그인");
		} else if(provider.equals("google")) {
			email = (String) response.get("email");
			name = (String) response.get("name");
			System.out.println(">>> Google 로그인");
		} else {
			throw new OAuth2AuthenticationException("허용되지 않은 인증입니다.");
		}
		
		String userNameAttributeName = "email";
		
		User user;
		Optional<User> optionalUser = userRepository.findByEmail(email);
		
		if(optionalUser.isPresent()) {
			user = optionalUser.get();
		} else {
			user = new User();
			user.setName(name);
			user.setEmail(email);
			user.setRole(Role.ROLE_USER);
			user.setProvider(provider);
			user = userRepository.save(user);
		}
		
		SessionUser sessionUser = new SessionUser(user);
		httpSession.setAttribute("user", sessionUser);
		
		oAuth2User =  new DefaultOAuth2User(
				Collections.singleton(
						new SimpleGrantedAuthority(user.getRole().toString()))
				, response
				, userNameAttributeName);
		
		return oAuth2User;
	}

}
