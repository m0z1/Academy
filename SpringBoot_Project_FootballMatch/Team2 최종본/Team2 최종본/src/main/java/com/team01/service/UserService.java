package com.team01.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.team01.model.Book;
import com.team01.model.Team;
import com.team01.model.User;
import com.team01.repository.BookRepository;
import com.team01.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final BookRepository bookRepository;
	private final BCryptPasswordEncoder encoder;
	
	public void join(User user) {
		String encPassword = encoder.encode(user.getPassword());
		user.setPassword(encPassword);
		if(user.getUsername().equals("Admin")) {
			user.setAdmin("ROLE_ADMIN");
		}else if(!user.getUsername().equals("Admin")){
			user.setAdmin("ROLE_USER");
		}
		userRepository.save(user);
	}
	
	
	public Page<User> member_list(Pageable pageable){
	      return userRepository.findAll(pageable);
	   }
	
	public User member_view(Long userId) {
		return userRepository.findById(userId).get();
	}
	
	public void member_update(User user) {
		
	}
	
	public User user_view(String username) {
		return userRepository.findByUsername(username);
	}
	
	@Transactional
	public void teamjoin(User user, Team team) {
		user.setTeam(team);
		userRepository.save(user);
		team.setTeamSize(team.getTeamSize()+1);
	}
	
	@Transactional
	public void teamout(User user, Team team) {
		user.setTeam(null);
		userRepository.save(user);
		team.setTeamSize(team.getTeamSize()-1);
	}
	
	public List<Book> booklist(Long teamId){ 	
		return bookRepository.findByTeam_TeamId(teamId);
	}
}
