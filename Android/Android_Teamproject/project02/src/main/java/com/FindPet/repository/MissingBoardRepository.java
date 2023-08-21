package com.FindPet.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.FindPet.model.MissingBoard;

public interface MissingBoardRepository extends JpaRepository<MissingBoard, Long> {
	
	
	
	/* Missing  Search - 2023-08-18 */
	
	Page<MissingBoard> findAllByPetcategory(Pageable pageable, String petcategory);
	
	Page<MissingBoard> findAllByBreedContaining(String word, Pageable pageable);
	
	Page<MissingBoard> findAllByContentContaining(String word, Pageable pageable);
	
	Page<MissingBoard> findAllByPetnameContaining(String word, Pageable pageable);
	
	Page<MissingBoard> findAllByPetageContaining(String word, Pageable pageable);
	
	Page<MissingBoard> findAllByPetgenderContaining(String word, Pageable pageable);
	
	Page<MissingBoard> findAllByPetcharacterContaining(String word, Pageable pageable);
	
	Page<MissingBoard> findAllByMissingaddrContaining(String word, Pageable pageable);
	
	/* -----------------------------------------------------------------------------*/
	
	

}
