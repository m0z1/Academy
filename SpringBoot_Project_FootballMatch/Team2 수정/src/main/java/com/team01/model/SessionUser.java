package com.team01.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class SessionUser implements Serializable {

    SessionUser() {}

    public SessionUser(User user) {
    	this.id = user.getUserId();
    	this.userId = user.getUsername();
        this.nickName = user.getName();
        this.role = user.getRole();
        this.email = user.getEmail();
    }

    private Long id;
    private String userId;
    private String nickName;
    private Role role;
    private String email;

}