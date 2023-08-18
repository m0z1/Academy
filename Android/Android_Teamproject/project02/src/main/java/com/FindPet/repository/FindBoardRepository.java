package com.FindPet.repository;

import java.awt.print.Pageable;

import org.springframework.data.domain.Page;

import com.FindPet.model.FindBoard;

public interface FindBoardRepository {
	
	
	
	/* Find List - 2023-08-18 */
	Page<FindBoard> findByFindBoardWhere(Pageable pageable, String string);

	/* Find  Search - 2023-08-18 */
	Page<FindBoard> findAllByPetcategory(Pageable pageable, String petcategory);
	
	Page<FindBoard> findByBreed(String word, Pageable pageable);
	
	Page<FindBoard> findByContentContaining(String word, Pageable pageable);
	
	Page<FindBoard> findByPetname(String word, Pageable pageable);
	
	Page<FindBoard> findByPetage(String word, Pageable pageable);
	
	Page<FindBoard> findByPetgender(String word, Pageable pageable);
	
	Page<FindBoard> findByPetcharacter(String word, Pageable pageable);
	
	Page<FindBoard> findByFindaddr(String word, Pageable pageable);
	
	/* -----------------------------------------------------------------------------*/

}
