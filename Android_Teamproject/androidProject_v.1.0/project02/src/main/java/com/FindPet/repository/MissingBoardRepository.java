package com.FindPet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.FindPet.model.MissingBoard;

public interface MissingBoardRepository extends JpaRepository<MissingBoard, Long> {

	List<MissingBoard> findByPetcategoryOrderByMissingIdDesc(String petCategory);

	List<MissingBoard> findByBreedContaining(String word);

	List<MissingBoard> findByContentContaining(String word);

	List<MissingBoard> findByPetnameContaining(String word);

	List<MissingBoard> findByPetageContaining(String word);

	List<MissingBoard> findByPetgenderContaining(String word);

	List<MissingBoard> findByPetcharacterContaining(String word);

	List<MissingBoard> findByMissingaddrContaining(String word);
	
	List<MissingBoard> findAllByOrderByMissingIdDesc();
}
