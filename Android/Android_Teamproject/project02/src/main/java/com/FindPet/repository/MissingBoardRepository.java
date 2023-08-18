package com.FindPet.repository;

import java.awt.print.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.FindPet.model.MissingBoard;

public interface MissingBoardRepository extends JpaRepository<MissingBoard, Long> {
	
	
	
	
	/* Missing  Search - 2023-08-18 */
	Page<MissingBoard> findByMissingBoardWhere(Pageable pageable, String string);
	
	Page<MissingBoard> findAllByPetcategory(Pageable pageable, String petcategory);
	
	Page<MissingBoard> findByBreed(String word, Pageable pageable);
	
	Page<MissingBoard> findByContentContaining(String word, Pageable pageable);
	
	Page<MissingBoard> findByPetname(String word, Pageable pageable);
	
	Page<MissingBoard> findByPetage(String word, Pageable pageable);
	
	Page<MissingBoard> findByPetgender(String word, Pageable pageable);
	
	Page<MissingBoard> findByPetcharacter(String word, Pageable pageable);
	
	Page<MissingBoard> findByMissingaddr(String word, Pageable pageable);
	
	/* -----------------------------------------------------------------------------*/
	
	

}
