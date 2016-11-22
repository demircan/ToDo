package com.iyzico.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.iyzico.api.impl.UserAPI;
import com.iyzico.domain.CurrentUser;

public class CurrentUserDetailsService implements UserDetailsService {
	
	private final UserAPI userAPI;
	
	 	@Autowired
	    public CurrentUserDetailsService(UserAPI userAPI) {
	        this.userAPI = userAPI;
	    }

	    @Override
	    public CurrentUser loadUserByUsername(String username) throws UsernameNotFoundException {

	    	User user = userAPI.getUserByName(name)
	                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with email=%s was not found", email)));
	        return new CurrentUser(user);
	    }
	
}
