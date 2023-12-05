package com.team01.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team01.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByEmail(String email);
	Optional<User> findByTel(String tel);
	User findByUsername(String username);

}
