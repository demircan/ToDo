package com.iyzico.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.iyzico.api.IUserAPI;
import com.iyzico.domain.User;

	@Service
	public class SecurityService {
	    
		@Autowired
	    private AuthenticationManager authenticationManager;

	    @Autowired
	    private UserDetailsService userDetailsService;
	    
	    @Autowired
	    private IUserAPI userAPI;

	    
	    public User activeUser() {
	    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String username =  auth.getName();
			Optional<User> user = userAPI.getUserByName(username);

			if(user.isPresent()){
				return user.get();
			}
	        return null;
	    }

	    
	    public void autologin(String username, String password) {
	        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
	        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, AuthorityUtils.createAuthorityList("USER"));

	        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

	        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
	            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
	        }
	    }
	}
	
