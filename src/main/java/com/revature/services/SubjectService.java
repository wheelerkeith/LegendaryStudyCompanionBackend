package com.revature.services;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.revature.daos.SubjectDao;
import com.revature.models.Subject;

@Service
public class SubjectService {
	
	private static ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
	private static SubjectDao subjectDao = (SubjectDao) ac.getBean("subjectDaoImpl");
	
	// add new subject
	public int addSubjectObject(Subject s) {
		return subjectDao.addSubject(s);
	}
	
	// add new subject
	public int addSubjectByName(String name) {
		return subjectDao.addSubject(new Subject(name));
	}
	
	// get subject by id
	public Subject getSubjectById(int id) {
		return subjectDao.getSubjectById(id);
	}
	
	// get subject by name
	public Subject getSubjectByName(String name) {
		return subjectDao.getSubjectByName(name);
	}
	
	// get all subjects
	public List<Subject> getAllSubjects() {
		return subjectDao.getAllSubjects();
	}
	
	// update subject name
	public int updateSubject(Subject s) {
		return subjectDao.updateSubject(s);
	}
	
	// remove subject
	public int removeSubject(int id) {
		Subject s = new Subject();
		s.setId(id);
		return subjectDao.removeSubject(s);
	}

}
