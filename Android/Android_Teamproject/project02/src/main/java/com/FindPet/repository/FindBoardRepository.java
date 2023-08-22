package com.FindPet.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.FindPet.model.FindBoard;

public interface FindBoardRepository extends JpaRepository<FindBoard, Long> {
	
	
	/* Find  Search - 2023-08-18 */
	Page<FindBoard> findAllByPetcategory(Pageable pageable, String petcategory);
	
	Page<FindBoard> findAllByBreedContaining(String word, Pageable pageable);
	
	Page<FindBoard> findAllByContentContaining(String word, Pageable pageable);
	
	Page<FindBoard> findAllByPetnameContaining(String word, Pageable pageable);
	
	Page<FindBoard> findAllByPetageContaining(String word, Pageable pageable);
	
	Page<FindBoard> findAllByPetgenderContaining(String word, Pageable pageable);
	
	Page<FindBoard> findAllByPetcharacterContaining(String word, Pageable pageable);
	
	Page<FindBoard> findAllByFindaddrContaining(String word, Pageable pageable);
	
	/* -----------------------------------------------------------------------------*/

}
