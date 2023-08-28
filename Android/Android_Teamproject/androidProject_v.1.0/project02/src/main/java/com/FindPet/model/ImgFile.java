package com.FindPet.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "img_file")
public class ImgFile {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long imgNum;
	private String imgOriginalName;
	private String imgSaveName;
	private String imgUrl;
	private String mainImg;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "find_id")
	private FindBoard findboard;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "story_id")
	private StoryBoard storyboard;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "missing_id")
	private MissingBoard missingboard;
	
	public void updateImgFile(String imgOriginalName, String imgSaveName, String imgUrl) {
		this.setImgOriginalName(imgOriginalName);
		this.setImgSaveName(imgSaveName);
		this.setImgUrl(imgUrl);
	}
}
