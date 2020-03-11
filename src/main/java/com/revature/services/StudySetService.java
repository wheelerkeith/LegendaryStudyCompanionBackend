package com.revature.services;

import java.util.List;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import com.revature.daos.StudySetDao;
import com.revature.daos.StudySetDaoImpl;
import com.revature.models.StudySet;

public class StudySetService {
	private static StudySetDao sdao = new StudySetDaoImpl();
	
	// add
	public int addStudySet(StudySet studyset) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
		return sdao.addStudySet(studyset);
	}
	// get by id
	public StudySet getStudySetById(int id) {
		return sdao.getStudySetById(id);
	}
	// get all
	public List<StudySet> getAllStudySets() {
		return sdao.getAllStudySets();
	}
	// update
	public int updateStudySet(StudySet studyset) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
		return sdao.updateStudySet(studyset);
	}
	// remove
	public int removeStudySet(StudySet studyset) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
		return sdao.removeStudySet(studyset);
	}
}
