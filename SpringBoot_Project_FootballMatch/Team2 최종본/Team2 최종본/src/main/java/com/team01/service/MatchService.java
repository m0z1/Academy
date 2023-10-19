package com.team01.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.team01.model.Match;
import com.team01.repository.MatchRepositorty;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MatchService {

	private final MatchRepositorty matchRepository;
	
	public void insert(Match match) {
		matchRepository.save(match);
	
	}
	
	public List<Match> list(){
		return matchRepository.findAll();
	}
	
	public void update(Match match) {
		Match m = matchRepository.findById(match.getMatchNum()).get();
		m.setMatchAway1(match.getMatchAway1());
		m.setMatchAway2(match.getMatchAway2());
		m.setMatchHome1(match.getMatchHome1());
	}
	
	public Match view(Long matchNum) {
		return matchRepository.findById(matchNum).get();
	}
	
	public Match view_booknum(Long matchNum) {
		return matchRepository.findByBook_BookNum(matchNum).get();
	}

	public List<Match> match_list(Long userId) {
		return matchRepository.findByUser_UserId(userId);
	}
	
	public Page<Match> pagelist(Pageable pageable) {
	      Page<Match> list = matchRepository.findAll(pageable);
	      return list;
	   }
	
	
}
