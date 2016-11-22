package com.iyzico.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.iyzico.domain.CurrentUser;

public class CurrentUserControllerAdvice {

	@ModelAttribute("currentUser")
    public CurrentUser getCurrentUser(Authentication authentication) {
        return (authentication == null) ? null : (CurrentUser) authentication.getPrincipal();
    }
	
}
