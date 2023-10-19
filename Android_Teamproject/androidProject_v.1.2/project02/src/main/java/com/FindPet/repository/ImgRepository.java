package com.FindPet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.FindPet.model.ImgFile;

public interface ImgRepository extends JpaRepository<ImgFile, Long>{
	
	List<ImgFile> findByFindboard_findIdOrderByImgNumAsc(Long findid);

    ImgFile findByImgNumAndMainImg(Long imgnum, String mainimg);

	List<ImgFile> findByMissingboard_missingIdOrderByImgNumAsc(Long missingId);

	List<ImgFile> findByStoryboard_storyIdOrderByImgNumAsc(Long storyId);
}
