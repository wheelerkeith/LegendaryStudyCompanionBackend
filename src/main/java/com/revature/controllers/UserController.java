package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
	
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	@ResponseBody
	public User getUserById(@PathVariable("id")int id) {
		return userService.getUserById(id);
	}
	
	// looking to receive JSON like this:
	// {"userName":"gracet","password":"987","email":"gracet@mail.com","fullName":"Grace Trueman","role":1}
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> addUser(@RequestBody User u) {
		userService.addUser(u);
		return new ResponseEntity<>("added user "+u.getFullName(),HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/{id}")
	@ResponseBody
	public ResponseEntity<User> updateUser(@PathVariable("id")int id, @RequestBody User user) {
		user.setUserId(id);
		userService.updateUser(user);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
	@ResponseBody
	public ResponseEntity<User> deleteUser(@PathVariable("id")int id) {
		User u = new User();
		u.setUserId(id);
		userService.removeUser(u);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
