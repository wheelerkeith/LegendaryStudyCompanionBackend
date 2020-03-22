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

import com.revature.models.Subject;
import com.revature.services.SubjectService;

@Controller
@RequestMapping("/subject")
public class SubjectController {
	
	@Autowired
	SubjectService subjectService;
	
	//POST - add subject
	// looking for form input like:
	// FORM: "Content-Type", "application/x-www-form-urlencoded"
	// FORM DATA: "name=Python"
	@RequestMapping(method=RequestMethod.POST)
	@CrossOrigin
	@ResponseBody
	public ResponseEntity<String> addSubject(@RequestParam("name")String name) {
		subjectService.addSubjectByName(name);
		return new ResponseEntity<> ("added subject: " + name, HttpStatus.OK);
		
	}
	
	// GET - get subject by name - this one does not allow periods in the name
	@RequestMapping(method=RequestMethod.GET, value="/{name}")
	@CrossOrigin
	@ResponseBody
	public Subject getSubjectByName(@PathVariable("name")String name) {
		return subjectService.getSubjectByName(name);
	}
	
	// GET - get subject by name
	@RequestMapping(method=RequestMethod.GET, value="/name")
	@CrossOrigin
	@ResponseBody
	public Subject getSubjectByNameObject(@RequestBody Subject subject) {
		return subjectService.getSubjectByName(subject.getName());
	}
	
	// GET - get all subjects
	@RequestMapping(method=RequestMethod.GET)
	@CrossOrigin
	@ResponseBody
	public List<Subject> getAllSubjects() {
		return subjectService.getAllSubjects();
	}
	
	// PUT - update subject name. Used object input here because some names can have periods (like: .NET)
	@RequestMapping(method=RequestMethod.PUT)
	@CrossOrigin
	@ResponseBody
	public ResponseEntity<String> updateSubject(@RequestBody Subject subject) {
		subjectService.updateSubject(subject);
		return new ResponseEntity<> ("updated subject to: " + subject.getName(), HttpStatus.OK);
	}
	
	// DELETE - remove the subject from db
	@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
	@CrossOrigin
	@ResponseBody
	public ResponseEntity<String> removeSubject(@PathVariable("id")int id) {
		subjectService.removeSubject(id);
		return new ResponseEntity<> ("removed subject: " + id, HttpStatus.OK);
	}

}
