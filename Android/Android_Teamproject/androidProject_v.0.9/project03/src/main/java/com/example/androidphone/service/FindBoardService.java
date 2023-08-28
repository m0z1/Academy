package com.example.androidphone.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.androidphone.model.FindBoard;
import com.example.androidphone.repository.FindBoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FindBoardService {
	
	private final FindBoardRepository findBoardRepository;
	
	//글 작성
	public FindBoard insert(FindBoard findBoard) {
		return findBoardRepository.save(findBoard);
	}
	
	//상세보기
	public FindBoard view(Long findId) {
		return findBoardRepository.findById(findId).get();
	}
	
	//수정
	@Transactional
	public FindBoard update(FindBoard findBoard) {
		FindBoard fb = findBoardRepository.findById(findBoard.getFindId()).get();
		fb.setFindaddr(findBoard.getFindaddr());
		fb.setContent(findBoard.getContent());
		fb.setBreed(findBoard.getBreed());
		fb.setPetname(findBoard.getPetname());
		fb.setPetage(findBoard.getPetage());
		fb.setPetgender(findBoard.getPetgender());
		fb.setPetcharacter(findBoard.getPetcharacter());
		return fb;
	}
	
	//삭제
	public void deleteById(Long findId) {
		findBoardRepository.deleteById(findId);
	}
	
	//전체보기
		public List<FindBoard> find_list() {
			return findBoardRepository.findAll();
			
		}
	
		public List<FindBoard> dog_find_list(String petCategory) {

			List<FindBoard> dog_lists = findBoardRepository.findByPetcategory(petCategory);

			return dog_lists;

		}

		public List<FindBoard> cat_find_list(String petCategory) {
			List<FindBoard> cat_lists = findBoardRepository.findByPetcategory(petCategory);

			return cat_lists;
		}

		public List<FindBoard> Etc_find_list(String petCategory) {
			List<FindBoard> etc_lists = findBoardRepository.findByPetcategory(petCategory);

			return etc_lists;
		}
		
		
		public List<FindBoard> all_find(String word) {
				List<FindBoard> allfind = new ArrayList<>();
				 allfind.addAll(findBoardRepository.findByBreedContaining(word));
				 allfind.addAll(findBoardRepository.findByContentContaining(word));
				 allfind.addAll(findBoardRepository.findByFindaddrContaining(word));
				 allfind.addAll(findBoardRepository.findByPetageContaining(word));
				 allfind.addAll(findBoardRepository.findByPetcharacterContaining(word));
				 allfind.addAll(findBoardRepository.findByPetgenderContaining(word));
				 allfind.addAll(findBoardRepository.findByPetnameContaining(word));
			
	return allfind;
			/*
			 * -----------------------------------------------------------------------------
			 */

		}

}
