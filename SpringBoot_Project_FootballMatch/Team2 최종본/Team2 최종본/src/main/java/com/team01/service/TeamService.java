package com.team01.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.team01.model.Team;
import com.team01.repository.TeamRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeamService {

	private final TeamRepository teamRepository;

	public void team_insert(Team team) {

		teamRepository.save(team);

	}

	public Page<Team> team_list(Pageable pageable, String field, String word) {
		Page<Team> lists = teamRepository.findAll(pageable);

		if (field.equals("team_name")) {
			lists = teamRepository.findByteamNameContaining(word, pageable);
		} else if (field.equals("team_category")) {
			lists = teamRepository.findByteamCategoryContaining(word, pageable);
		}

		return lists;
	}

	public Team team_view(Long team_id) {
		return teamRepository.findById(team_id).get();
	}

	public void team_delete(Long teamId) {
		teamRepository.deleteById(teamId);
	}

}
