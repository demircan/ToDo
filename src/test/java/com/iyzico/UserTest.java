package com.iyzico;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import com.iyzico.api.IUserAPI;
import com.iyzico.domain.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {
	
	@Autowired
	private IUserAPI userAPI;
	
	@Test
	public void testCreateUser(){
		User user = new User();
		user.setUsername("testName");
		user.setPasswordHash("test");
		userAPI.saveUser(user);
		
		Assert.assertNotNull(user.getId());
	}
}
