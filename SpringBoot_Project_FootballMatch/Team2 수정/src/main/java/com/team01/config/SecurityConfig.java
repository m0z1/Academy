package com.team01.config;


import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.security.ConditionalOnDefaultWebSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.team01.model.Role;
import com.team01.service.OpenApiOAuth2UserService;
import com.team01.service.UserService;

import lombok.RequiredArgsConstructor;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
	private final OpenApiOAuth2UserService openApiOAuth2UserService;
	private final UserService userService;
	
	@Bean
    AuthenticationManager authenticationManager(
    AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
	
	
//    @Bean 
//    public BCryptPasswordEncoder encodePwd() { 
//  	  return new  BCryptPasswordEncoder(); 
//    }
	 
	
	@Bean
//	@ConditionalOnDefaultWebSecurity
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.csrf().disable();
		http.authorizeRequests()
		.antMatchers("/","/member/login","/member/join").permitAll()
		.antMatchers("/member/*","/member/user_info/*").authenticated()
		.antMatchers("/manager/*").hasAnyRole("MANAGER","ADMIN")
		.antMatchers("/admin/*").hasRole("ADMIN")
		.and()
		.formLogin().loginPage("/member/login").defaultSuccessUrl("/")
		.and()
		.logout().logoutUrl("/logout").logoutSuccessUrl("/").invalidateHttpSession(true)
		.and()
		.exceptionHandling().accessDeniedPage("/")
		.and()
		.oauth2Login()
		.loginPage("/member/login").defaultSuccessUrl("/")
		.userInfoEndpoint()
		.userService(openApiOAuth2UserService);
		
		return http.build();
	}

	
}
