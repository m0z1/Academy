package com.team01.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.team01.model.Field;

public interface FieldRepository extends JpaRepository<Field, Long>{

	List<Field> findByUser_UserIdOrderByFieldNumDesc(Long userId);

	Page<Field> findByFieldNameContaining(String word, Pageable pageable);

	Page<Field> findByFieldSigunguContaining(String word, Pageable pageable);

}
