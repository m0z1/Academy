package com.FindPet.service;

import java.awt.print.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.FindPet.model.MissingBoard;
import com.FindPet.repository.MissingBoardRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MissingBoardService {

	private final MissingBoardRepository missingBoardRepository;  // 2023-08-18 add 
	
	
	/* Missing List - 2023-08-18 */
	public Page<MissingBoard> Missing_list(Pageable pageable){
		Page<MissingBoard> missing_list = missingBoardRepository.findByMissingBoardWhere(pageable, "MissingBoard");
	
		return missing_list;
		
	}
	
	
	/* Missing Search - 2023-08-18 */
	public Page<MissingBoard> dog_find_list(Pageable pageable, String field,String word){
		
		Page<MissingBoard> dog_lists = missingBoardRepository.findAllByPetcategory(pageable, "강아지");
			
		
		if(field.equals("breed")) {
			dog_lists = missingBoardRepository.findByBreed(word, pageable);
		} else if(field.equals("content")) {
			dog_lists = missingBoardRepository.findByContentContaining(word, pageable);
		}else if(field.equals("petname")) {
			dog_lists = missingBoardRepository.findByPetname(word, pageable);
		}else if(field.equals("petgender")) {
			dog_lists = missingBoardRepository.findByPetgender(word, pageable);
		}else if(field.equals("petcharacter")) {
			dog_lists= missingBoardRepository.findByPetcharacter(word, pageable);
		}else if(field.equals("findaddr")) {
			dog_lists = missingBoardRepository.findByMissingaddr(word, pageable);
		}
		return dog_lists;
		
	}
	
	public Page<MissingBoard> cat_find_list(Pageable pageable, String field, String word){
		Page<MissingBoard> cat_lists = missingBoardRepository.findAllByPetcategory(pageable, "고양이");
		
		if(field.equals("breed")) {
			cat_lists = missingBoardRepository.findByBreed(word, pageable);
		} else if(field.equals("content")) {
			cat_lists = missingBoardRepository.findByContentContaining(word, pageable);
		}else if(field.equals("petname")) {
			cat_lists = missingBoardRepository.findByPetname(word, pageable);
		}else if(field.equals("petgender")) {
			cat_lists = missingBoardRepository.findByPetgender(word, pageable);
		}else if(field.equals("petcharacter")) {
			cat_lists= missingBoardRepository.findByPetcharacter(word, pageable);
		}else if(field.equals("findaddr")) {
			cat_lists = missingBoardRepository.findByMissingaddr(word, pageable);
		}
		return cat_lists;
	
	}
	
	
	public Page<MissingBoard> etc_find_list(Pageable pageable, String field,String word){
		Page<MissingBoard> etc_lists = missingBoardRepository.findAllByPetcategory(pageable, "기타");
		
		if(field.equals("breed")) {
			etc_lists = missingBoardRepository.findByBreed(word, pageable);
		} else if(field.equals("content")) {
			etc_lists = missingBoardRepository.findByContentContaining(word, pageable);
		}else if(field.equals("petname")) {
			etc_lists = missingBoardRepository.findByPetname(word, pageable);
		}else if(field.equals("petgender")) {
			etc_lists = missingBoardRepository.findByPetgender(word, pageable);
		}else if(field.equals("petcharacter")) {
			etc_lists= missingBoardRepository.findByPetcharacter(word, pageable);
		}else if(field.equals("findaddr")) {
			etc_lists = missingBoardRepository.findByMissingaddr(word, pageable);
		}
		return etc_lists;
		
	}

	/* -----------------------------------------------------------------------------*/
	
	
	
	
}
