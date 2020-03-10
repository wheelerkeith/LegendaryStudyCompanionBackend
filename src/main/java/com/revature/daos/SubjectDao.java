package com.revature.daos;

import java.util.List;

import com.revature.models.Subject;

public interface SubjectDao {
	
	// add new subject
	public int addSubject(Subject s);
	
	// get subject by id
	public Subject getSubjectById(int id);
	
	// get all subjects
	public List<Subject> getAllSubjects();
	
	// update subject name
	public int updateSubject(Subject s);
	
	// remove subject
	public int removeSubject(Subject s);

}
