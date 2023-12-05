package com.team01.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "project_match")
public class Match implements Serializable {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "match_num")
	private Long matchNum;
	private String matchHome1;
	private String matchAway1;
	private String matchAway2;
	@ColumnDefault("0")
	public int flag; // 0725
	
	@Column(name = "fight")
	private String fight;
	
	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "book_num")
	private Book book;
	
	@OneToMany
	private List<Team> team;
	
	@OneToOne
	private User user;
}
