package com.springsecurity.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableOAuth2Sso
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception
	{
		http
		.antMatcher("/user")	
		.authorizeRequests()
		.antMatchers("/","/index.html","/home.html","/cities/*")
		.permitAll()
		.anyRequest()
		.authenticated()
		.and().csrf().disable();
		
	}
}