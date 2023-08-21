package com.FindPet.service;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.FindPet.model.FindBoard;
import com.FindPet.repository.FindBoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FindBoardService {
	
	private final FindBoardRepository findBoardRepository; // 2023-08-18 add 
	
	
  /* Find List */
	public List<FindBoard> Find_list() {
		return findBoardRepository.findAll();
	}
	
/* Find Insert - 2023-08-21 */
	public FindBoard insert(FindBoard findboard) {
		
		return findBoardRepository.save(findboard);
	}
	
	
	
	/* Find Search - 2023-08-18 */
	public Page<FindBoard> dog_find_list(Pageable pageable, String field,String word){
		
		Page<FindBoard> dog_lists = findBoardRepository.findAllByPetcategory(pageable, "강아지");
			
		
		if(field.equals("breed")) {
			dog_lists = findBoardRepository.findAllByBreedContaining(word, pageable);
		} else if(field.equals("content")) {
			dog_lists = findBoardRepository.findAllByContentContaining(word, pageable);
		}else if(field.equals("petname")) {
			dog_lists = findBoardRepository.findAllByPetnameContaining(word, pageable);
		}else if(field.equals("petgender")) {
			dog_lists = findBoardRepository.findAllByPetgenderContaining(word, pageable);
		}else if(field.equals("petcharacter")) {
			dog_lists= findBoardRepository.findAllByPetcharacterContaining(word, pageable);
		}else if(field.equals("findaddr")) {
			dog_lists = findBoardRepository.findAllByFindaddrContaining(word, pageable);
		}
		return dog_lists;
		
	}
	
	public Page<FindBoard> cat_find_list(Pageable pageable, String field, String word){
		Page<FindBoard> cat_lists = findBoardRepository.findAllByPetcategory(pageable, "고양이");
		
		if(field.equals("breed")) {
			cat_lists = findBoardRepository.findAllByBreedContaining(word, pageable);
		} else if(field.equals("content")) {
			cat_lists = findBoardRepository.findAllByContentContaining(word, pageable);
		}else if(field.equals("petname")) {
			cat_lists = findBoardRepository.findAllByPetnameContaining(word, pageable);
		}else if(field.equals("petgender")) {
			cat_lists = findBoardRepository.findAllByPetgenderContaining(word, pageable);
		}else if(field.equals("petcharacter")) {
			cat_lists= findBoardRepository.findAllByPetcharacterContaining(word, pageable);
		}else if(field.equals("findaddr")) {
			cat_lists = findBoardRepository.findAllByFindaddrContaining(word, pageable);
		}
		return cat_lists;
	
	}
	
	
	public Page<FindBoard> etc_find_list(Pageable pageable, String field,String word){
		Page<FindBoard> etc_lists = findBoardRepository.findAllByPetcategory(pageable, "기타");
		
		
		if(field.equals("breed")) {
			etc_lists = findBoardRepository.findAllByBreedContaining(word, pageable);
		} else if(field.equals("content")) {
			etc_lists = findBoardRepository.findAllByContentContaining(word, pageable);
		}else if(field.equals("petname")) {
			etc_lists = findBoardRepository.findAllByPetnameContaining(word, pageable);
		}else if(field.equals("petgender")) {
			etc_lists = findBoardRepository.findAllByPetgenderContaining(word, pageable);
		}else if(field.equals("petcharacter")) {
			etc_lists= findBoardRepository.findAllByPetcharacterContaining(word, pageable);
		}else if(field.equals("findaddr")) {
			etc_lists = findBoardRepository.findAllByFindaddrContaining(word, pageable);
		}
		return etc_lists;
		
	}

	/* -----------------------------------------------------------------------------*/
	

}
