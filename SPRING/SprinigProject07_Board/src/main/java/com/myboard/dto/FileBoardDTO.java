package com.myboard.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter  @Setter
public class FileBoardDTO {
	//��ȣ, ����, ����, ����, �̹���
	private int num;
	private String title;
	private String writer;
	private String content;
	private  MultipartFile   upload;
	private String fileimage;
}
