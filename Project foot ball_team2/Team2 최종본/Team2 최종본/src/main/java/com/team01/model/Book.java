package com.team01.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "project_book")
public class Book {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_num")
	private Long bookNum;
	@Column(name = "book_date")
	private String bookDate;
	@Column(name = "book_start")
	private int bookStart;
	@Column(name = "book_end")
	private int bookEnd;
	@ManyToOne
	@JoinColumn(name = "field_num")
	private Field field;
	
	@OneToOne
	@JoinColumn(name = "team_id")
	private Team team;
	
	@OneToOne(cascade = CascadeType.REMOVE)
	private Match match;
	
}
