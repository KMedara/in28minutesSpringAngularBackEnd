package com.in28minutes.rest.basic.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfigurationBasicAuth extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
			.csrf().disable() //disable csrf token, would force put and posts to have csrf token, we'll be using JWT
			.authorizeRequests()
			.antMatchers(HttpMethod.OPTIONS,"/**").permitAll() //permit all options requests without authentication, authenticate all else
				.anyRequest().authenticated()
				.and()
			//.formLogin().and()
			.httpBasic();
	}

}
