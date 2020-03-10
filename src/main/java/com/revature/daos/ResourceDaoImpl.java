package com.revature.daos;

import java.util.List;

import javax.persistence.RollbackException;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Resource;
import com.revature.util.HibernateUtil;

public class ResourceDaoImpl implements ResourceDao{

	
	// add resource
	
	@Override
	public int addResource(Resource r) {
		
		try (Session s = HibernateUtil.getSession()) {
			Transaction tx = s.beginTransaction();
			int pk = (int) s.save(r);
			
			tx.commit();
			return pk;
		}
	}
	
	
	// get resource by id

	@Override
	public Resource getResourceById(int id) {
		
		Resource r = null;
		
		try (Session s = HibernateUtil.getSession()) {
			r = s.get(Resource.class, id);
		}
		
		return r;
	}
	
	
	// get all resources

	@Override
	public List<Resource> getAllResources() {
		
		List<Resource> resourceList = null;
		
		try (Session s = HibernateUtil.getSession()) {
			String hql = "from Resource";
			
			Query<Resource> q = s.createQuery(hql, Resource.class);
			resourceList = q.list();
		}
		return resourceList;
	}
	
	
	// update resource

	@Override
	public int updateResource(Resource r) {
		
		int didItCommit = 0;
		
		try (Session s = HibernateUtil.getSession()) {
			Transaction tx = s.beginTransaction();
			s.update(r);
			tx.commit();
			didItCommit = 1;
		} catch (RollbackException e) {
			e.printStackTrace();
			System.out.println("commit from updateResource failed");
			didItCommit = 0;
		}
		return didItCommit;
	}
	
	
	// remove resource

	@Override
	public int removeResource(Resource r) {
		
		int didItDelete = 0;
		
		try (Session s = HibernateUtil.getSession()) {
			Transaction tx = s.beginTransaction();
			s.delete(r);
			tx.commit();
			didItDelete = 1;
		} catch (RollbackException e) {
			e.printStackTrace();
			System.out.println("commit from removeResource failed");
			didItDelete = 0;
		}
		return didItDelete;
	}

}
