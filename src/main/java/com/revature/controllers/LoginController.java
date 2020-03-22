package com.revature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.models.User;
import com.revature.services.UserService;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private UserService userService;
	
//	@Autowired
//	User Vuser;
	
	// POST
	// looking for JSON user object like:
	// {"userName":"lolap","password":"1234","email":""}
	// Header: "Content-Type", "application/json"
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<User> authenticateUser(@RequestBody User user) {
		User validUser = userService.validateUser(user);
		
		System.out.println(user);
		
		// followed example found in  org.springframework.http.ResponseEntity<String> documentation
		if(validUser != null && validUser.getUserId() != 0) {
			String token = validUser.getUserId() + ":" + validUser.getRole();
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("Authorization", token);
			return new ResponseEntity<>(responseHeaders, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

}
