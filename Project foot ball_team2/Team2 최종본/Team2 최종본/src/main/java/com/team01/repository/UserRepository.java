package com.team01.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team01.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByUsername(String username);

}
