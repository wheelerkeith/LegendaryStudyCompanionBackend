/**
 * 
 */
package com.revature.daos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.revature.models.StudySet;

/**
 * @author fitfour
 *
 */
@Repository
public class StudySetDaoImpl implements StudySetDao {
	
	@Autowired
	private SessionFactory sf;
	
	// add study set
	@Transactional
	@Override
	public int addStudySet(StudySet studySet) {
		Session s = sf.getCurrentSession();
		int pk = (int) s.save(studySet);
		return pk;		
	}

	// get study set by id
	@Transactional
	@Override
	public StudySet getStudySetById(int id) {
		Session s = sf.getCurrentSession();
		StudySet studySet = (StudySet) s.get(StudySet.class, id);
		return studySet;
	}

	// get all study sets in a list
	@Transactional
	@Override
	public List<StudySet> getAllStudySets() {
		Session s = sf.getCurrentSession();
		List<StudySet> studySets = s.createQuery("from StudySet").list();
		return studySets;
	}

	// update study set
	@Transactional
	@Override
	public int updateStudySet(StudySet studySet) {
		int didItCommit = 0;
		
		Session s = sf.getCurrentSession();
		s.saveOrUpdate(studySet);
		didItCommit = 1;
				
		return didItCommit;
	}
	
	// remove study set
	@Transactional
	@Override
	public int removeStudySet(StudySet studySet) {
		int didItDelete = 0;
		
		Session s = sf.getCurrentSession();
		s.delete(studySet);
		didItDelete = 1;
		
		return didItDelete;
	}

}
