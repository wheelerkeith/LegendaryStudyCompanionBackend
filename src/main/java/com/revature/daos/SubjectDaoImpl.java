package com.revature.daos;

import java.util.List;

import javax.persistence.RollbackException;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Subject;
import com.revature.util.HibernateUtil;

public class SubjectDaoImpl implements SubjectDao{

	
	// add subject
	
	@Override
	public int addSubject(Subject sub) {
		
		try (Session s = HibernateUtil.getSession()) {
			Transaction tx = s.beginTransaction();
			int pk = (int) s.save(sub);
			
			tx.commit();
			return pk;
		}
	}
	
	
	// get subject by id

	@Override
	public Subject getSubjectById(int id) {
		
		Subject sub = null;
		
		try (Session s = HibernateUtil.getSession()) {
			sub = s.get(Subject.class, id);
		}
		
		return sub;
	}
	
	
	// get all subjects

	@Override
	public List<Subject> getAllSubjects() {
		
		List<Subject> subjectList = null;
		
		try (Session s = HibernateUtil.getSession()) {
			String hql = "from Subject";
			Query<Subject> q = s.createQuery(hql, Subject.class);
			subjectList = q.list();
		}
		
		return subjectList;
	}
	
	
	// update subject name

	@Override
	public int updateSubject(Subject sub) {
		
		int didItCommit = 0;
		
		try (Session s = HibernateUtil.getSession()) {
			Transaction tx = s.beginTransaction();
			s.update(sub);
			tx.commit();
			didItCommit = 1;
		} catch (RollbackException e) {
			e.printStackTrace();
			System.out.println("commit from updateSubject failed");
			didItCommit = 0;
		}
		return didItCommit;
	}
	
	
	// remove subject

	@Override
	public int removeSubject(Subject sub) {
		
		int didItDelete = 0;
		
		try (Session s = HibernateUtil.getSession()) {
			Transaction tx = s.beginTransaction();
			s.delete(sub);
			tx.commit();
			didItDelete = 1;
		} catch (RollbackException e) {
			e.printStackTrace();
			System.out.println("commit from removeSubject failed");
			didItDelete = 0;
		}
		return didItDelete;
	}

}
