package com.team01.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "project_images")
public class ImgFile {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long imgNum;
	private String imgOriginalName;
	private String imgSaveName;
	private String imgUrl;
	private String mainImg;
	
	@ManyToOne
	@JoinColumn(name = "field_num")
	private Field field;
	@ManyToOne
	@JoinColumn(name = "team_id")
	private Team team;
	
	public void updateImgFile(String imgOriginalName, String imgSaveName, String imgUrl) {
		
		this.setImgOriginalName(imgOriginalName);
		this.setImgSaveName(imgSaveName);
		this.setImgUrl(imgUrl);
	}
}
