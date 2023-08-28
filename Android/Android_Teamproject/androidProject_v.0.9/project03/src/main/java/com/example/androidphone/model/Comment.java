package com.example.androidphone.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Comment {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "comment_id")
	private Long commentId;
	private String content;
	
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)	//부모 테이블 삭제 시, 함께 삭제
	@JoinColumn(name = "member_id")
	@JsonIgnore
	private Member member;
	
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)	//부모 테이블 삭제 시, 함께 삭제
	@JoinColumn(name = "find_id")
	@JsonIgnore
	private FindBoard findboard;
	
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)	//부모 테이블 삭제 시, 함께 삭제
	@JoinColumn(name = "story_id")
	@JsonIgnore
	private StoryBoard storyboard;
	
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)	//부모 테이블 삭제 시, 함께 삭제
	@JoinColumn(name = "missing_id")
	@JsonIgnore
	private MissingBoard missingboard;
}
