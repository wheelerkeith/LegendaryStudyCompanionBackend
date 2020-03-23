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

import com.revature.models.StudySet;
import com.revature.services.StudySetService;

@Controller
@RequestMapping("/studyset")
public class StudySetController {
	
	@Autowired
	private StudySetService studySetService;
	
	// POST - add a studyset (/studyset)
	// loking to recieve JSON like the followig:
	// {"name":"Intro to Karate","user":[{"userId":4}]}
	@RequestMapping(method=RequestMethod.POST)
	@CrossOrigin(origins="*")
	@ResponseBody
	public ResponseEntity<String> addStudySet(@RequestBody StudySet studySet) {
		studySetService.addStudySet(studySet);
		return new ResponseEntity<>("added studyset: " + studySet.getName(), HttpStatus.CREATED);
	}
	
	// GET - get all study sets (/studyset)
	@RequestMapping(method=RequestMethod.GET)
	@CrossOrigin(origins="*")
	@ResponseBody
	public List<StudySet> getAllStudySets() {
		return studySetService.getAllStudySets();
	}
	
	// GET - get studyset by id (/studyset/id)
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	@CrossOrigin(origins="*")
	@ResponseBody
	public StudySet getStudySetById(@PathVariable("id")int id) {
		return studySetService.getStudySetById(id);
	}
	
	// PUT - update studyset name (/studyset/id:name)
	// looking for URL like:
	// localhost:8080/LegendaryStudyCompanionBackend/studyset/5:Advanced DODA
	@RequestMapping(method=RequestMethod.PUT, value="/{id}:{name}")
	@CrossOrigin(origins="*")
	@ResponseBody
	public ResponseEntity<String> updateStudySet(@PathVariable("id")int id, @PathVariable("name")String name) {
		studySetService.updateStudySet(id, name);
		return new ResponseEntity<>("", HttpStatus.OK);
	}
	
	// DELETE - delete studySet (/studyset/id)
	@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
	@CrossOrigin(origins="*")
	@ResponseBody
	public ResponseEntity<String> removeStudySet(@PathVariable("id")int id) {
		studySetService.removeStudySet(id);
		return new ResponseEntity<>("Removed studyset: " + id, HttpStatus.OK);
	}
	
	
}
