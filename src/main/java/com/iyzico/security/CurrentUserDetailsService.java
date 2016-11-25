package com.iyzico.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.iyzico.api.IUserAPI;
import com.iyzico.domain.CurrentUser;
import com.iyzico.domain.User;

@Service
public class CurrentUserDetailsService implements UserDetailsService {
	
	private final IUserAPI userAPI;
	
	 	@Autowired
	    public CurrentUserDetailsService(IUserAPI userAPI) {
	        this.userAPI = userAPI;
	    }

	    @Override
	    public CurrentUser loadUserByUsername(String username) throws UsernameNotFoundException {

	    	Optional<User> user = userAPI.getUserByName(username);
	        if( user.isPresent()) {
				return new CurrentUser(user.get());
			}
			else
				throw new UsernameNotFoundException(String.format("User with username=%s was not found", username));
	    }
}
