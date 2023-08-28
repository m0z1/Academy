package com.example.androidphone.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
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
public class StoryBoard {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "story_id")
	private Long storyId;
	private String title;
	private String content;
	@CreationTimestamp
	private Date regdate;
	
	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;
	
	@OneToMany(mappedBy = "storyboard")
	private List<ImgFile> imgFileList;
	
	@OneToMany(mappedBy = "storyboard")
	private List<Comment> comment;

}
