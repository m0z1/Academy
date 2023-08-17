package com.example.androidproject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Member {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long member_id;
	private String username;
	private String password;
	private String name;
	private String memberZipcode;
	private String memberDetailAddress;
	private String memberExtraAddress;
	private String memberSigungu;
}
