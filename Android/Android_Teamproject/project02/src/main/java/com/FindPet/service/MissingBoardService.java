package com.FindPet.service;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.FindPet.model.MissingBoard;
import com.FindPet.repository.MissingBoardRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MissingBoardService {

	private final MissingBoardRepository missingBoardRepository;  // 2023-08-18 add 
	
	
	/* Missing List - 2023-08-18 */
	public List<MissingBoard> Missing_list(){
		List<MissingBoard> missing_list = missingBoardRepository.findAll();
	
		return missing_list;
		
	}
	
	
	/* Missing Search - 2023-08-18 */
	public Page<MissingBoard> dog_find_list(Pageable pageable, String field,String word){
		
		Page<MissingBoard> dog_lists = missingBoardRepository.findAllByPetcategory(pageable, "강아지");
			
		if(field.equals("breed")) {
			dog_lists = missingBoardRepository.findAllByBreedContaining(word, pageable);
		} else if(field.equals("content")) {
			dog_lists = missingBoardRepository.findAllByContentContaining(word, pageable);
		}else if(field.equals("petname")) {
			dog_lists = missingBoardRepository.findAllByPetnameContaining(word, pageable);
		}else if(field.equals("petgender")) {
			dog_lists = missingBoardRepository.findAllByPetgenderContaining(word, pageable);
		}else if(field.equals("petcharacter")) {
			dog_lists= missingBoardRepository.findAllByPetcharacterContaining(word, pageable);
		}else if(field.equals("findaddr")) {
			dog_lists = missingBoardRepository.findAllByMissingaddrContaining(word, pageable);
		}
		return dog_lists;
		
	}
	
	public Page<MissingBoard> cat_find_list(Pageable pageable, String field, String word){
		Page<MissingBoard> cat_lists = missingBoardRepository.findAllByPetcategory(pageable, "고양이");
		
		if(field.equals("breed")) {
			cat_lists = missingBoardRepository.findAllByBreedContaining(word, pageable);
		} else if(field.equals("content")) {
			cat_lists = missingBoardRepository.findAllByContentContaining(word, pageable);
		}else if(field.equals("petname")) {
			cat_lists = missingBoardRepository.findAllByPetnameContaining(word, pageable);
		}else if(field.equals("petgender")) {
			cat_lists = missingBoardRepository.findAllByPetgenderContaining(word, pageable);
		}else if(field.equals("petcharacter")) {
			cat_lists= missingBoardRepository.findAllByPetcharacterContaining(word, pageable);
		}else if(field.equals("findaddr")) {
			cat_lists = missingBoardRepository.findAllByMissingaddrContaining(word, pageable);
		}
		return cat_lists;
	
	}
	
	
	public Page<MissingBoard> etc_find_list(Pageable pageable, String field,String word){
		Page<MissingBoard> etc_lists = missingBoardRepository.findAllByPetcategory(pageable, "기타");
		if(field.equals("breed")) {
			etc_lists = missingBoardRepository.findAllByBreedContaining(word, pageable);
		} else if(field.equals("content")) {
			etc_lists = missingBoardRepository.findAllByContentContaining(word, pageable);
		}else if(field.equals("petname")) {
			etc_lists = missingBoardRepository.findAllByPetnameContaining(word, pageable);
		}else if(field.equals("petgender")) {
			etc_lists = missingBoardRepository.findAllByPetgenderContaining(word, pageable);
		}else if(field.equals("petcharacter")) {
			etc_lists= missingBoardRepository.findAllByPetcharacterContaining(word, pageable);
		}else if(field.equals("findaddr")) {
			etc_lists = missingBoardRepository.findAllByMissingaddrContaining(word, pageable);
		}
		return etc_lists;
		
	}

	/* -----------------------------------------------------------------------------*/
	
	
	
	
}
