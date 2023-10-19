package com.example.androidphone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.androidphone.model.Member;


public interface MemberRepository extends JpaRepository<Member, Long>{

	// 아이디로 유저 조회
	Member findByUsername(String username);
	
}
