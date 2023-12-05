package com.team01.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "project_team")
public class Team implements Serializable {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "team_id")
	private Long teamId;
	@Column(name = "team_name")
	private String teamName;
	@Column(name = "team_num")
	private int teamSize;
	@Column(name = "team_category")
	private String teamCategory;
	@Column(name = "team_level")
	private String teamLevel;
	@Column(name = "team_memo")
	private String teamMemo;
	@ManyToOne
	private Match match;
	@OneToOne
	private Book book;
	@OneToMany
	private List<Comment> comment;
	
	@OneToMany(mappedBy = "team")
	@JsonIgnoreProperties("team")
	private List<User> user = new ArrayList<User>();
	
	private Long teamManager;
	
}
