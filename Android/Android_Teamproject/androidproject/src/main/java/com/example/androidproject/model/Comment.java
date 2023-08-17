package com.example.androidproject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Comment {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long comment_id;
	private String content;
	
	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;
	
	@ManyToOne
	@JoinColumn(name = "find_id")
	private FindBoard findboard;
	
	@ManyToOne
	@JoinColumn(name = "story_id")
	private StoryBoard storyboard;
	
	@ManyToOne
	@JoinColumn(name = "missing_id")
	private MissingBoard missingboard;
}
