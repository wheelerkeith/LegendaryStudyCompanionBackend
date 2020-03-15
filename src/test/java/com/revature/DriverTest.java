package com.revature;

import static org.junit.Assert.assertEquals;

import org.hibernate.Session;
import org.junit.Test;

import com.revature.models.User;
import com.revature.services.UserService;

public class DriverTest {
	
	private static UserService us = new UserService();
	
	
	@Test
	public void testSomething() {
		
		//need to update with new connection settings
//		User u = new User("bluefort", "pass123", "bfort@mail.com", "Blueford Fortinbras", 0);
//		int userId = us.addUser(u);
//		
//		u.setPassword("");
//		
//		System.out.println(u);
//		System.out.println(us.getUserById(userId));
//		
//		assertEquals(u, us.getUserById(userId));
	}

}
