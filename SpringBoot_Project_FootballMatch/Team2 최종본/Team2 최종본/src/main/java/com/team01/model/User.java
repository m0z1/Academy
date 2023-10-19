package com.team01.model;

import java.io.Serializable;
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

import lombok.Data;

@Data
@Entity
@Table(name = "project_user")
public class User {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userId;
	private String username;
	private String password;
	private String name;
	private String tel;
	private String admin;
	private String memo;
	
	@Column(name = "user_address")
	private String userAddress;
	@Column(name = "user_detail_address")
	private String userDetailAddress;
	@Column(name = "user_extra_address")
	private String userExtraAddress;
	@Column(name = "user_zipcode")
	private String userZipcode;
	@Column(name = "user_sigungu")
	private String userSigungu;
	// 0727추가
	
	@ManyToOne
	@JoinColumn(name = "team_id")
	private Team team;
	@OneToMany
	private List<Board> board;
	@OneToMany
	private List<Comment> comment;
	@OneToMany
	private List<Field> field;
	
}
