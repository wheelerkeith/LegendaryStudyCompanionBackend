package com.revature.controllers;

import java.util.List;

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

import com.revature.models.User;
import com.revature.services.UserService;

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
}
