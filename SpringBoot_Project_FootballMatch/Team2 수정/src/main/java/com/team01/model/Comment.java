package com.team01.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "project_comment")
public class Comment {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "comment_num")
	private int commentNum;
	@Column(name = "comment_comment")
	private String commentComment;
	@CreationTimestamp
	@Column(name = "comment_regdate")
	private Date commentRegdate;
	
	@ManyToOne
	@JoinColumn(name = "team_id")
	private Team team;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	@ManyToOne
	@JoinColumn(name = "board_id")
	private Board board;
	
}
