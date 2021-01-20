package com.kb.chess.server.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	PasswordEncoder bCryptPasswordEncoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
	 	  .userDetailsService(userDetailsService)
		  .passwordEncoder(bCryptPasswordEncoder);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
			.antMatchers("/matchmaking")
				.hasRole("USER")
			.antMatchers("/register", "/login").permitAll()
		.and()
			.formLogin()
				.loginProcessingUrl("/login")
				.usernameParameter("login")
				.passwordParameter("password")
				.successHandler(successHandler())
		.and()
			.csrf()
				.disable();
	}
	
	private AuthenticationSuccessHandler successHandler() {
	    return new AuthenticationSuccessHandler() {
	     
	    	@Override
	    	public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
	    		httpServletResponse.getWriter().append("OK");
	    		httpServletResponse.setStatus(200);
	    	}
	    };
	  }
}
