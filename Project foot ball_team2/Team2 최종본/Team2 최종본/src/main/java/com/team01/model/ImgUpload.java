package com.team01.model;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ImgUpload {
	
	private Long imgNum;
	
	private Long fieldNum;
	
	private Long teamId;
	
	private String imgOriginalName;
	private String imgSaveName;
	private Long imgSize;
	
	@Builder
	public ImgUpload(String imgOriginalName, String imgSaveName, Long imgSize) {
		this.imgOriginalName = imgOriginalName;
		this.imgSaveName = imgSaveName;
		this.imgSize = imgSize;
	}
	
	public void setField(Long fieldNum) {
		this.fieldNum = fieldNum;
	}
}
