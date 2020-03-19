package com.revature.daos;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.revature.models.Subject;

@Repository
public class SubjectDaoImpl implements SubjectDao{

	@Autowired
	private SessionFactory sf;
	
	// add subject
	@Transactional
	@Override
	public int addSubject(Subject sub) {
		Session s = sf.getCurrentSession();
		int pk = (int) s.save(sub);
		return pk;	
	}
	
	
	// get subject by id
	@Transactional
	@Override
	public Subject getSubjectById(int id) {
		Session s = sf.getCurrentSession();
		Subject subject = (Subject) s.get(Subject.class, id);
		
		return subject;
	}
	
	
	// get subject by name
	@Transactional
	@Override
	public Subject getSubjectByName(String name) {
		Session s = sf.getCurrentSession();
		String hql = "from Subject where name = :nameVar";
		Query subjectQuery = s.createQuery(hql);
		subjectQuery.setParameter("nameVar", name);
		return (Subject) subjectQuery.uniqueResult();
	}
	
	
	// get all subjects
	@Transactional
	@Override
	public List<Subject> getAllSubjects() {
		Session s = sf.getCurrentSession();
		List<Subject> subjects = s.createQuery("from Subject").list();
		return subjects;
	}
	
	
	// update subject name
	@Transactional
	@Override
	public int updateSubject(Subject sub) {
		int didItCommit = 0;
		
		Session s = sf.getCurrentSession();
		s.update(sub);
		didItCommit = 1;
		
		return didItCommit;
	}
	
	
	// remove subject
	@Transactional
	@Override
	public int removeSubject(Subject sub) {
		
		int didItDelete = 0;
		
		Session s = sf.getCurrentSession();
		s.delete(sub);
		didItDelete = 1;
		
		return didItDelete;
	}

}
