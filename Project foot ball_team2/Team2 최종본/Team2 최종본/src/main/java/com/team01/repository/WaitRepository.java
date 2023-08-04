package com.team01.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team01.model.Wait;

public interface WaitRepository extends JpaRepository<Wait, Long>{

	List<Wait> findByTeamId(Long teamId);

	//public List<Wait> findbyTeam_TeamId(Long teamId);

	

}
