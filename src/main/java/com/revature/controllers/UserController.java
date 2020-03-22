package com.revature.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Resource;
import com.revature.models.User;
import com.revature.services.UserService;
import com.revature.services.ResourceService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	// POST - add user. expects to receive data in JSON (/user)
	// looking to receive JSON like this:
	// {"userName":"gracet","password":"987","email":"gracet@mail.com","fullName":"Grace Trueman","role":1}
	@RequestMapping(method=RequestMethod.POST)
	@CrossOrigin
	@ResponseBody
	public ResponseEntity<String> addUser(@RequestBody User u) {
		userService.addUser(u);
		return new ResponseEntity<>("added user "+u.getFullName(),HttpStatus.CREATED);
	}
	
	// POST - update user saved resources. expects to recieve update user in JSON (/user/id/resources)
	@RequestMapping(method=RequestMethod.POST, value="/{id}/resources")
	@CrossOrigin
	@ResponseBody
	public ResponseEntity<User> updateUserLikedResources(@PathVariable("id")int id, @RequestBody Resource resource) {
		
		// Pull user and resource info from the database
		User u = userService.getUserById(id);
		
		// Update the user list with the resource
		int success = userService.addResourceToList(u, resource);
		if (success != 0) {
			return new ResponseEntity<>(HttpStatus.OK);			
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// GET - get all users (/user)
	// get all usrs
	@RequestMapping(method=RequestMethod.GET)
	@CrossOrigin
	@ResponseBody
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
	// GET - get user by id (/user/id)
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	@CrossOrigin
	@ResponseBody
	public User getUserById(@PathVariable("id")int id) {
		return userService.getUserById(id);
	}
	
	// GET - get user saved resources by user id (/user/id/resources)
	@RequestMapping(method=RequestMethod.GET, value="/{id}/resources")
	@CrossOrigin
	@ResponseBody
	public Set<Resource> getUserLikedResourcesById(@PathVariable("id")int id) {
		User u = userService.getUserById(id);
		if (u != null) {			
			return u.getResourceList();
		}
		return new HashSet<Resource>();
	}

	// PUT - update user. expects to recieve update user in JSON (/user/id)
	@RequestMapping(method=RequestMethod.PUT, value="/{id}")
	@CrossOrigin
	@ResponseBody
	public ResponseEntity<User> updateUser(@PathVariable("id")int id, @RequestBody User user) {
		user.setUserId(id);
		userService.updateUser(user);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	// DELETE - delete user from db by id (/user/id)
	@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
	@CrossOrigin
	@ResponseBody
	public ResponseEntity<User> deleteUser(@PathVariable("id")int id) {
		userService.removeUser(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	// DELETE - delete user liked resource from db by id (/user/id/resources)
		@RequestMapping(method=RequestMethod.DELETE, value="/{id}/resources")
		@CrossOrigin
		@ResponseBody
		public ResponseEntity<User> deleteUserLikedResources(@PathVariable("id")int id, @RequestBody Resource resource) {
			
			// Pull user and resource info from the database
			User u = userService.getUserById(id);
			
			// Update the user list with the resource
			int success = userService.deleteFromResourceList(u, resource);
			if (success != 0) {
				return new ResponseEntity<>(HttpStatus.OK);			
			}
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
}
