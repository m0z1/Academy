package com.FindPet.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.FindPet.model.StoryBoard;
import com.FindPet.repository.StoryBoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StoryBoardService {

	private final StoryBoardRepository storyBoardRepository;

	// 글 작성
	public StoryBoard insert(StoryBoard storyBoard) {
		return storyBoardRepository.save(storyBoard);
	}

	// 상세보기
	public StoryBoard view(Long storyId) {
		return storyBoardRepository.findById(storyId).get();
	}

	// 수정
	@Transactional
	public StoryBoard update(StoryBoard storyBoard) {
		StoryBoard sb = storyBoardRepository.findById(storyBoard.getStoryId()).get();
		sb.setContent(storyBoard.getContent());
		return sb;
	}

	// 삭제
	public void deleteById(Long storyId) {
		storyBoardRepository.deleteById(storyId);
	}

	// 전체보기
	public List<StoryBoard> story_list() {
		return storyBoardRepository.findAll();

	}

}
