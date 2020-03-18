package com.revature.services;

import java.util.List;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.revature.daos.StudySetDao;
import com.revature.models.StudySet;

@Service
public class StudySetService {
	
	private static ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
	private static StudySetDao studySetDao = (StudySetDao) ac.getBean("studySetDaoImpl");
	
	// add
	public int addStudySet(StudySet studyset) {
		return studySetDao.addStudySet(studyset);
	}
	// get by id
	public StudySet getStudySetById(int id) {
		return studySetDao.getStudySetById(id);
	}
	// get all
	public List<StudySet> getAllStudySets() {
		return studySetDao.getAllStudySets();
	}
	// update
	public int updateStudySet(int id, String name) {
		return studySetDao.updateStudySet(new StudySet(id, name));
	}
	// remove
	public int removeStudySet(int id) {
		return studySetDao.removeStudySet(new StudySet(id, null));
	}
}
