package com.team01.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.team01.model.Book;
import com.team01.repository.BookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {
	
private final BookRepository bookRepository;
	
	public List<Book> list(){
	
		return bookRepository.findAll();
	}
	

	public List<Book> bookFieldList(Long fieldNum){
		
		return bookRepository.findByField_fieldNum(fieldNum);
	}
	
	
	public void book_insert(Book book) {
		bookRepository.save(book);
	}
	
	public Book book_view(Long book_num) {
		return bookRepository.findById(book_num).get();
	}

}