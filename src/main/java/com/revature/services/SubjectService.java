package com.revature.services;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.revature.daos.SubjectDao;
import com.revature.models.Subject;

public class SubjectService {
	
	private static ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
	private static SubjectDao subjectDao = (SubjectDao) ac.getBean("subjectDaoImpl");
	
	// add new subject
	public int addSubject(Subject s) {
		return subjectDao.addSubject(s);
	}
	
	// get subject by id
	public Subject getSubjectById(int id) {
		return subjectDao.getSubjectById(id);
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
	public int removeSubject(Subject s) {
		return subjectDao.removeSubject(s);
	}

}
