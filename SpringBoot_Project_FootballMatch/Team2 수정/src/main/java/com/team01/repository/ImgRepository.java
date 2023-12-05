package com.team01.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team01.model.ImgFile;

import java.util.List;

public interface ImgRepository extends JpaRepository<ImgFile, Long> {

    List<ImgFile> findByImgNumOrderByImgNumAsc(Long imgnum);

    ImgFile findByImgNumAndMainImg(Long imgnum, String mainimg);


	List<ImgFile> findByField_FieldNumOrderByImgNumAsc(Long fieldNum);

}