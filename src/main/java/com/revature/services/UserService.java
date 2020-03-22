package com.revature.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
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
	private static ResourceService resourceService = (ResourceService) ac.getBean("resourceService");
	
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
		
		if (userToReturn == null) {
			return null;
		}
		
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
		
		if (u == null) {
			return 0;
		}
		
		Resource resCheck = resourceService.getResourceByUrl(r.getUrl());
		
		// If the resource isn't in the database it needs to be created as a new resource
		if (resCheck != null) {
			r = resCheck;
		} else {
			r.setResourceId(resourceService.addResource(r));
		}
		
		Set<Resource> likedResources = u.getResourceList();
		
		if (likedResources.contains(r)) {
			return 1;
		}
		
		likedResources.add(r);
		
		u.setResourceList(likedResources);
		
		return userDao.updateUser(u);
	}
	
	//deleteFromUserResourceList
	public int deleteFromResourceList(User u, Resource r) {
		if (u == null || r == null) {
			return 0;
		}
		
		Resource resCheck = resourceService.getResourceByUrl(r.getUrl());
		
		// If the resource isn't in the database it will return null
		if (resCheck == null) {
			return 0;
		} 
		
		r = resCheck;
		
		Set<Resource> likedResources = u.getResourceList();
		
		likedResources.remove(r);
		
		u.setResourceList(likedResources);
		
		System.out.println(u);
		
		return userDao.updateUser(u);
	}
	
	// remove user
	public int removeUser(int id) {
		return userDao.removeUser(id);
	}

}
