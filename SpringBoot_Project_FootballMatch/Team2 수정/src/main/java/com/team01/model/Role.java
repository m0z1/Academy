package com.team01.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

	ROLE_ADMIN("ROLE_ADMIN", "시스템 관리자"),
    ROLE_MANAGER("ROLE_MANAGER", "구장 관리자"),
    ROLE_USER("ROLE_USER", "일반회원");
	
	private final String key;
	private final String value;

}