package com.team01.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.team01.model.User;
import com.team01.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
		
   private final UserRepository userRepository;
     
   public List<User> list(){
	   return userRepository.findAll();
   }
   

   
}
