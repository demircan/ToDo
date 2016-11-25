package com.iyzico.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Configuration
@EnableWebSecurity
@Service
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests().
		antMatchers("/login-page","/register-page","/create-user","/css/**","/fonts/**","/font-awesome/**","/js/**","/lib/**","/**/favicon.ico").permitAll().
		anyRequest().authenticated().
		and().formLogin().  //login configuration
		loginPage("/login-page").
		loginProcessingUrl("/appLogin").
		usernameParameter("app_username").
		passwordParameter("app_password").
		defaultSuccessUrl("/task-list").
		and().logout().    //logout configuration
		logoutUrl("/appLogout"). 
		logoutSuccessUrl("/login-page").
		and().
		exceptionHandling().accessDeniedPage("/403").
		and().
        csrf().disable();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.userDetailsService(userDetailsService)
		.passwordEncoder(new BCryptPasswordEncoder());
	}
}


