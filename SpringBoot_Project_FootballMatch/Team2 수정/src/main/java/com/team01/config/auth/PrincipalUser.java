package com.team01.config.auth;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.team01.model.Role;
import com.team01.model.User;

import lombok.Getter;

@Getter
public class PrincipalUser implements UserDetails, OAuth2User{

	private User user;
	private Map<String, Object> attributes;
	
	// 일반 로그인
	public PrincipalUser(User user) {
		System.out.println("PrincipalUser 일반");
		this.user = user;
	}
	
	// OAuth2 로그인
	public PrincipalUser(User user, Map<String, Object> attributes){
		System.out.println("PrincipalUser OAuth2");
        this.user = user;
        this.attributes = attributes;
    } 

	public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole().toString();
            }
        });
        return collect;
    }
	

	public String getEmail() {
		return user.getEmail();
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public Map<String, Object> getAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return user.getEmail();
	}

}
