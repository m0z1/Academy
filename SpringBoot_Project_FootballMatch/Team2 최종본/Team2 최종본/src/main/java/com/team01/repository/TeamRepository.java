package com.team01.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.team01.model.Team;

public interface TeamRepository extends JpaRepository<Team, Long>{

	Page<Team> findByteamNameContaining(String word, Pageable pageable);

	Page<Team> findByteamCategoryContaining(String word, Pageable pageable);

}
