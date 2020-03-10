package com.revature.services;

import java.util.List;

import com.revature.daos.UserDao;
import com.revature.daos.UserDaoImpl;
import com.revature.models.Resource;
import com.revature.models.User;

public class UserService {
	
	private static UserDao ud = new UserDaoImpl();
	
	
	// add new user
	
	public int addUser(User u) {
		return ud.addUser(u);
	}
	
	
	// validate user with log in info
	
	public User validateUser(User u) {
		
		User currentUser = ud.validateUser(u);
		
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
		
		User userToReturn = ud.getUserById(id);
		
		userToReturn.setPassword("");
		
		return userToReturn;
	}
	
	
	// get all users
	
	public List<User> getAllUsers() {
		
		List<User> userList = ud.getAllUsers();
		
		for(User user: userList) {
			user.setPassword("");
		}
		
		
		return userList;
	}
	
	
	// update user information
	
	public int updateUser(User u) {
		return ud.updateUser(u);
	}
	
	
	// find resource in liked list
	public int findResourceInLikedList(User u, Resource r) {
		
		int found = 0;
		
		for (Resource r0 : u.getResourceList()) {
			if (r0 == r) {
				found = 1;
			}			
		}
		
		return found;
	}
	
	// add resource to resource list
	
	public int addResourceToList(User u, Resource r) {
		
		u.getResourceList().add(r);
		
		return ud.updateUser(u);
	}
	
	
	// remove user
	
	public int removeUser(User u) {
		return ud.removeUser(u);
	}

}