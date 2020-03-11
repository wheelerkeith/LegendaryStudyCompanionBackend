package com.revature.daos;

import java.util.List;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import com.revature.models.StudySet;

public interface StudySetDao {
	
	// add to database
	public int addStudySet(StudySet studyset) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException;
	// get studyset by id
	public StudySet getStudySetById(int id);
	// get all studysets
	public List<StudySet> getAllStudySets();
	// update studyset
	public int updateStudySet(StudySet studyset) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException;
	// remove studyset
	public int removeStudySet(StudySet studyset) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException;
}
