package com.team01.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "project_board")
public class Board {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "board_num")
	private Long boardNum;
	@Column(name = "board_title")
	private String boardTitle;
	@CreationTimestamp
	@Column(name = "board_regdate")
	private Date boardRegdate;
	@Column(name = "board_content")
	private String boardContent;
	@Column(name = "board_category")
	private String boardCategory;
	@Column(name = "board_where")
	private String boardWhere;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@OneToMany(mappedBy = "board")
	private List<Comment> comment;
	
	
}
