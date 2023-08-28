package com.FindPet.repository;



import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.FindPet.model.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{

	Member findByUsername(String username);

	@Query(value = "select count(*) from member where username = ?1", nativeQuery = true)
	int findByUser(String username);

	@Modifying
	@Transactional
	@Query(value = "delete from member where username = ?1", nativeQuery = true)
	void deleteByUsername(String username);

	@Query(value = "select count(*) from member where nickname = ?1", nativeQuery = true)
	int findByNickname(String nickname);

	@Query(value = "select password from member where username = ?1", nativeQuery = true)
	String login(String username);

	



	
	

}
