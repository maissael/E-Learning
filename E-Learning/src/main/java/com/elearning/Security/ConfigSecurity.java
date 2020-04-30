package com.elearning.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.elearning.DAO.UserService;

@Configuration
@EnableWebSecurity
public class ConfigSecurity extends WebSecurityConfigurerAdapter{
	@Autowired
	private UserService userService;

	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/index","/").permitAll()
		.antMatchers("/signup").permitAll()
		.antMatchers("/formation/save").permitAll()
		.antMatchers("/formation/add").permitAll()
		.antMatchers("/formation/**").permitAll()
		.antMatchers("/formation/delete/{id}").permitAll()
		.antMatchers("/formation/update/{id}").permitAll()
		.antMatchers("/formations").permitAll()
		.antMatchers("/element/save").permitAll()
		.antMatchers("/js/**","/css/**","/img/**","/scss/**","/vendor/**").permitAll()
		.antMatchers("/register").permitAll()
//		.antMatchers("/formation/**").hasRole("FORMATEUR")
		.anyRequest().authenticated()
		.and()
		.formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/homepage")
		.and()
		.logout().logoutUrl("/logout").logoutSuccessUrl("/index")
		.and()
		.oauth2Login().loginPage("/login").defaultSuccessUrl("/index");
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
