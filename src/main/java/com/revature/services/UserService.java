package com.revature.services;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.revature.daos.UserDao;
import com.revature.models.Resource;
import com.revature.models.User;

@Service
public class UserService {
	
	private static ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
	private static UserDao userDao = (UserDao) ac.getBean("userDaoImpl");	
	
	public UserService() {
		super();
	}


	// add new user
	public int addUser(User u) {
		return userDao.addUser(u);
	}
	
	
	// validate user with log in info
	public User validateUser(User u) {
		User currentUser = userDao.validateUser(u);
		
		if (currentUser != null) {
			currentUser.setPassword("");
			return currentUser;
		} else {
			System.out.println("no user found");
			return null;
		}
	}

	
	// get user by id
	public User getUserById(int id) {
		User userToReturn = userDao.getUserById(id);
		
		userToReturn.setPassword("");
		
		return userToReturn;
	}
	
	
	// get all users
	public List<User> getAllUsers() {
		List<User> userList = userDao.getAllUsers();
		
		for(User user: userList) {
			user.setPassword("");
		}
		
		return userList;
	}
	
	
	// update user information
	public int updateUser(User u) {
		return userDao.updateUser(u);
	}
	
	
	// find resource in liked list
	public int findResourceInLikedList(User u, Resource r) {
		int found = 0;
		
		for (Resource r0 : u.getLikedResources()) {
			if (r0 == r) {
				found = 1;
			}			
		}
		
		return found;
	}
	
	// add resource to resource list
	public int addResourceToList(User u, Resource r) {
		u.getLikedResources().add(r);
		return userDao.updateUser(u);
	}
	
	// remove user
	public int removeUser(int id) {
		return userDao.removeUser(id);
	}

}
