package com.revature;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.hibernate.Session;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.revature.daos.UserDao;
import com.revature.models.User;
import com.revature.services.UserService;

@RunWith(MockitoJUnitRunner.class)
public class DriverTest {
	
	@InjectMocks
	private UserService us;
	
	@Mock
	private UserDao ud;
	
	@Test
	public void testAddUser() {
		User user = new User("billybob@gmail.com", "1234", "billybob@gmail.com");
		
		assertEquals(0, ud.addUser(user));
	}
}
