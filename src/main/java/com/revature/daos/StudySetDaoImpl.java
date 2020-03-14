/**
 * 
 */
package com.revature.daos;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.Transaction;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.revature.models.StudySet;
import com.revature.util.HibernateUtil;

/**
 * @author fitfour
 *
 */
public class StudySetDaoImpl implements StudySetDao {
	
	@Autowired
	private SessionFactory sf;

	@Override
	public int addStudySet(StudySet studySet) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
		Session s = sf.getCurrentSession();
		int pk = (int) s.save(studySet);
		return pk;
		
//		try (Session s = HibernateUtil.getSession()) {
//			Transaction tx = (Transaction) s.beginTransaction();
//			int pk = (int) s.save(studyset);
//			tx.commit();
//			return pk;
//		}
	}

	@Override
	public StudySet getStudySetById(int id) {
		StudySet studyset = null;
		
		try (Session s = HibernateUtil.getSession()) {
			studyset = s.get(StudySet.class, id);
		}
		
		return studyset;
	}

	@Override
	public List<StudySet> getAllStudySets() {
		List<StudySet> studysets = null;
		
		try (Session s = HibernateUtil.getSession()) {
			String hql = "from StudySet";
			Query<StudySet> q = s.createQuery(hql, StudySet.class);
			studysets = q.list();
		}
		
		return studysets;
	}

	@Override
	public int updateStudySet(StudySet studyset) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
		int didItCommit = 0;
		
		try (Session s = HibernateUtil.getSession()) {
			Transaction tx = (Transaction) s.beginTransaction();
			s.update(studyset);
			tx.commit();
			didItCommit = 1;
		}
		
		return didItCommit;
	}

	@Override
	public int removeStudySet(StudySet studyset) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
		int didItDelete = 0;
		
		try (Session s = HibernateUtil.getSession()) {
			Transaction tx = (Transaction) s.beginTransaction();
			s.delete(studyset);
			tx.commit();
			didItDelete = 1;
		}
		
		return didItDelete;
	}

}
