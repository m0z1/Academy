package com.team01.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.team01.model.Wait;
import com.team01.repository.WaitRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WaitService {

	private final WaitRepository waitRepository;

	public void insert(Wait wait) {
		waitRepository.save(wait);
	}

	public List<Wait> waitlist(Long teamId){
		return waitRepository.findByTeamId(teamId);
	}
}
