package com.example.androidproject.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class FindBoard {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long find_id;
	private String breed;
	private String content;
	@CreationTimestamp
	private Date regdate;
	private String petname;
	private String petage;
	private String petgender;
	private String petcharacter;
	private String petcategory;
	
	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;
	
	@OneToMany(mappedBy = "findboard")
	private List<Image> image;
	
	@OneToMany(mappedBy = "findboard")
	private List<Comment> comment;
	
	
	
}
