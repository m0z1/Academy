package com.team01.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team01.model.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

	List<Book> findByTeam_TeamId(Long teamId);

	List<Book> findByField_fieldNum(Long fieldNum);

}
