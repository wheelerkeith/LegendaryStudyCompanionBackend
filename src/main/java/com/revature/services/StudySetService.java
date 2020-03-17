package com.revature.services;

import java.util.List;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.revature.daos.StudySetDao;
import com.revature.models.StudySet;

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
	public int updateStudySet(StudySet studyset) {
		return studySetDao.updateStudySet(studyset);
	}
	// remove
	public int removeStudySet(StudySet studyset) {
		return studySetDao.removeStudySet(studyset);
	}
}
