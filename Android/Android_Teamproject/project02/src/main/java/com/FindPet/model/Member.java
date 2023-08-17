package com.FindPet.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "member")
public class Member {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private Long memberId;
	private String username;
	private String password;
	private String name;
	private String tel;
	private String admin;
	
	@Column(name = "member_address")
	private String memberAddress;
	@Column(name = "member_detail_address")
	private String memberDetailAddress;
	@Column(name = "member_extra_address")
	private String memberExtraAddress;
	@Column(name = "member_zipcode")
	private String memberZipcode;
	@Column(name = "member_sigungu")
	private String memberSigungu;
}
