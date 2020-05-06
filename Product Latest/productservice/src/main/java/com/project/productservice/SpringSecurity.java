package com.project.productservice;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SpringSecurity extends WebSecurityConfigurerAdapter 
{
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable().authorizeRequests()
//				.anyRequest().authenticated()
//				.and().httpBasic();
		http
		.httpBasic()
		.and()
		.authorizeRequests()
		.antMatchers(HttpMethod.GET).hasRole("USER")
		.antMatchers(HttpMethod.POST).hasRole("USER")
		.antMatchers(HttpMethod.DELETE).hasRole("USER")
		.antMatchers(HttpMethod.PUT).hasRole("USER")
		.and()
		.csrf().disable()
		.formLogin().disable();

		
	}
	@Override
	 protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user").password("{noop}password").roles("USER");
	}

}
