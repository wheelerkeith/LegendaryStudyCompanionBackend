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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.models.Blacklist;
import com.revature.services.BlacklistService;

@Controller
@RequestMapping("/blacklist")
public class BlacklistController {
	
	@Autowired
	private BlacklistService blacklistService;
	
	// POST - add blacklist entry
	// looking for this header: "Content-Type", "application/x-www-form-urlencoded"
	// looking for this data: "resource=7&subject=4"
	@RequestMapping(method=RequestMethod.POST)
	@CrossOrigin
	@ResponseBody
	public ResponseEntity<String> addToBlacklist(@RequestParam("resource")int resourceId, @RequestParam("subject")int subjectId) {
		blacklistService.addToBlacklist(resourceId, subjectId);
		return new ResponseEntity<>("added following to blacklist: " + resourceId + ":" + subjectId, HttpStatus.CREATED);
	}
	
	// GET - get all blacklist entries
	@RequestMapping(method=RequestMethod.GET)
	@CrossOrigin
	@ResponseBody
	public List<Blacklist> getAllFromBlacklist() {
		return blacklistService.getAllFromBlacklist();
	}
	
	// GET - get blacklist by composite key
	// looking for get request like:
	// http://localhost:8080/LegendaryStudyCompanionBackend/blacklist/2&3
	@RequestMapping(method=RequestMethod.GET, value="/{resourceId}&{subjectId}")
	@CrossOrigin
	@ResponseBody
	public Blacklist getBlacklistByIds(@PathVariable("resourceId")int resourceId, @PathVariable("subjectId")int subjectId) {
		return blacklistService.getBlacklistByIds(resourceId, subjectId);
	}
	
	//PUT - update blacklist entry status
	// looking for PUT request with url like following:
	// http://localhost:8080/LegendaryStudyCompanionBackend/blacklist/2&3:denied
	@RequestMapping(method=RequestMethod.PUT, value="/{resourceId}&{subjectId}:{newStatus}")
	@CrossOrigin
	@ResponseBody
	public ResponseEntity<String> updateBlacklistEntry(@PathVariable("resourceId")int resourceId, @PathVariable("subjectId")int subjectId, @PathVariable("newStatus")String newStatus) {
		blacklistService.updateBlacklistEntry(resourceId, subjectId, newStatus);
		return new ResponseEntity<>("blacklist set to: " + newStatus, HttpStatus.OK);
	}
	
	// DELETE - delete blacklist entry
	@RequestMapping(method=RequestMethod.DELETE, value="/{resourceId}&{subjectId}")
	@CrossOrigin
	@ResponseBody
	public ResponseEntity<String> removeBlacklistEntry(@PathVariable("resourceId")int resourceId, @PathVariable("subjectId")int subjectId) {
		blacklistService.removeBlacklistEntry(resourceId, subjectId);
		return new ResponseEntity<>("blacklist removed", HttpStatus.OK);
	}

}
