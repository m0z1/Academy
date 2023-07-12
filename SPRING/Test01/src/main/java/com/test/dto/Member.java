package com.test.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Member {
	private String id;
	private String pass;
	private String name;
	private String addr;
	private Date regdate;
}
