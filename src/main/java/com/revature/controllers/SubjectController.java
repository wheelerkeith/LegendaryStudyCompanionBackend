package com.revature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.services.SubjectService;

@Controller
public class SubjectController {
	
	@Autowired
	private static SubjectService subjectService;
	
	// POST - not be needed
	
	// GET - get subject by id (/subject/id)
	
	// GET - get all subjects (/subject)
	
	// PUT - update subject name (/subject/id:newName)
	
	// DELETE - delete a subject (/subject/id)
	

}
