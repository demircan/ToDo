package com.iyzico.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;



@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//	        http
		//	            .authorizeRequests()
		//	                .antMatchers("/**").permitAll()
		//	                .anyRequest().authenticated()
		//	                .and()
		//	            .formLogin()
		//	                .loginPage("/login")
		//	                .permitAll()
		//	                .and()
		//	            .logout()
		//	                .permitAll();

		http.authorizeRequests().
		antMatchers("/loginPage","/css/**","/fonts/**","/font-awesome/**","/js/**","/lib/**","/**/favicon.ico").permitAll().
		anyRequest().authenticated().
		and().formLogin().  //login configuration
		loginPage("/loginPage").
		loginProcessingUrl("/appLogin").
		usernameParameter("app_username").
		passwordParameter("app_password").
		defaultSuccessUrl("/greeting").	
		and().logout().    //logout configuration
		logoutUrl("/appLogout"). 
		logoutSuccessUrl("/app/login");
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.userDetailsService(userDetailsService)
		.passwordEncoder(new BCryptPasswordEncoder());
	}
}


