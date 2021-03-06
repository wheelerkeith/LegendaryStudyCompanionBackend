package com.revature.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.models.Resource;
import com.revature.models.User;
import com.revature.services.ResourceService;
import com.revature.services.UserService;

@Controller
@RequestMapping("/resource")
public class ResourceController {
	
	@Autowired
	private ResourceService resourceService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(method=RequestMethod.POST)
	@CrossOrigin(origins="*")
	@ResponseBody
	public ResponseEntity<String> addResource(@RequestBody Resource resource) {
		resourceService.addResource(resource);
		return new ResponseEntity<>("added resource", HttpStatus.OK);
	}
	
	
	@RequestMapping(method=RequestMethod.GET)
	@CrossOrigin(origins="*", allowedHeaders = "*")
	@ResponseBody
	public List<Resource> getAllResources(@RequestParam(name="q", required=false) String q, @RequestParam(name="filters", required=false) String filters, @RequestHeader("Authorization") String token){
		if (q != null) {
			int uId = Integer.parseInt(token.split(":")[0]);
			
			String[] filterArr = null;
			
			System.out.println(filters);
			
			if (!"null".equals(filters) && !"".equals(filters)) {
				filterArr = filters.split(":");
				if (filterArr[0] == null) {
					filterArr = null;
				}
			}
			
			User u = userService.getUserById(uId);
			
			if (u == null) {
				return new ArrayList<>();
			}
			
			return resourceService.getResourceList(q, 10, u, filterArr);
		}
		return resourceService.getAllResources();
	}

}
