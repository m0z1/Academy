package com.FindPet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.FindPet.model.FindBoard;

public interface FindBoardRepository extends JpaRepository<FindBoard, Long> {
	
	/* Find  Search - 2023-08-18 */
	List<FindBoard> findByPetcategoryOrderByFindIdDesc(String petCategory);
	
	List<FindBoard> findByBreedContaining(String word);
	
	List<FindBoard> findByContentContaining(String word);
	
	List<FindBoard> findByPetnameContaining(String word);
	
	List<FindBoard> findByPetageContaining(String word);
	
	List<FindBoard> findByPetgenderContaining(String word);
	
	List<FindBoard> findByPetcharacterContaining(String word);
	
	List<FindBoard> findByFindaddrContaining(String word);
	
	List<FindBoard> findAllByOrderByFindIdDesc();
}
