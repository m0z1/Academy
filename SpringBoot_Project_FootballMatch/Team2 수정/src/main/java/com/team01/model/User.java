package com.team01.model;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Builder
@Table(name = "project_user")
public class User {
	
	public User() {}

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userId;
	private String username;
	private String password;
	private String name;
	private String email;
	private String tel;
	//private String admin;
	@Enumerated(EnumType.STRING)
    private Role role;
	private String memo;
	
	private String provider;    // oauth2를 이용할 경우 어떤 플랫폼을 이용하는지
    private String providerId;  // oauth2를 이용할 경우 아이디값
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "team_id")
	private Team team;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Board> board;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Comment> comment;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Field> field;
	
	
	public User update(String name, String tel) {
        this.name = name;
        this.tel = tel;

        return this;
    }
	
	public Role getRoleKey() {
        return this.role;
    }


}
