package com.revature.services;

import java.util.List;

import com.revature.daos.SubjectDao;
import com.revature.daos.SubjectDaoImpl;
import com.revature.models.Subject;

public class SubjectService {
	
	private static SubjectDao sd = new SubjectDaoImpl();
	
	// add new subject
	public int addSubject(Subject s) {
		return sd.addSubject(s);
	}
	
	// get subject by id
	public Subject getSubjectById(int id) {
		return sd.getSubjectById(id);
	}
	
	// get all subjects
	public List<Subject> getAllSubjects() {
		return sd.getAllSubjects();
	}
	
	// update subject name
	public int updateSubject(Subject s) {
		return sd.updateSubject(s);
	}
	
	// remove subject
	public int removeSubject(Subject s) {
		return sd.removeSubject(s);
	}
	

}
