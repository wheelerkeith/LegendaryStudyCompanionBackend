package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.models.Resource;
import com.revature.services.ResourceService;

@Controller
@RequestMapping("/resource")
public class ResourceController {
	
	@Autowired
	private ResourceService resourceService;
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> addResource(@RequestBody Resource resource) {
		resourceService.addResource(resource);
		return new ResponseEntity<>("added resource", HttpStatus.OK);
	}
	
	
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public List<Resource> getAllResources(){
		return resourceService.getAllResources();
	}
	
	

}
