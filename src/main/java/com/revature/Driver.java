package com.revature;

import org.hibernate.Session;

import com.revature.models.User;
import com.revature.services.UserService;
import com.revature.util.HibernateUtil;

public class Driver {
	
	private static UserService us = new UserService();

	public static void main(String[] args) {
		
//		Session s = HibernateUtil.getSession();
//		
//		s.close();
		
//		User u = new User("jortiz", "pass", "jortiz@mail.com", "Josue Ortiz", 1);
//		us.addUser(u);
//		User u2 = new User("jortiz2", "pass2", "jortiz2@mail.com", "Josue Ortiz", 1);
//		us.addUser(u2);
//		User u3 = new User("lolap", "1234", "lolap@mail.com", "Lola Palmer", 0);
//		us.addUser(u3);
//		
//		User u4 = new User();
//		u4.setUserName("jortiz2");
//		u4.setPassword("pass2");
//		
//		System.out.println("VALIDATING: " + us.validateUser(u4));
//		
//		System.out.println(("GET BY ID: " + us.getUserById(3)));
//		
//		System.out.println("LIST ALL USERS: " + us.getAllUsers());
//		
//		u2.setFullName("Name Updated");
//		
//		System.out.println("UPDATE USER: " + us.updateUser(u2) +" : " + u2);
//		
//		System.out.println("REMOVE USER:" + us.removeUser(u));
//		System.out.println("LIST ALL USERS: " + us.getAllUsers());
		
	}

}
