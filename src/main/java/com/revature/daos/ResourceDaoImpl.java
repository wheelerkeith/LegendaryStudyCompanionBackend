package com.revature.daos;

import java.util.Arrays;
import java.util.List;


import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.revature.models.Resource;

@Repository
public class ResourceDaoImpl implements ResourceDao{
	
	@Autowired
	SessionFactory sf;

	// add resource
	@Transactional
	@Override
	public int addResource(Resource r) {
		Session s = sf.getCurrentSession();
		int pk = (int) s.save(r);
		return pk;
	}
	
	
	// get resource by id
	@Transactional
	@Override
	public Resource getResourceById(int id) {
		Session s = sf.getCurrentSession();
		Resource resource = (Resource) s.get(Resource.class, id);
		return resource;
	}
	
	
	// get resource by url
	// maybe I want to get a list here instead?
	@Transactional
	@Override
	public Resource getResourceByUrl(String url) {
		Session s = sf.getCurrentSession();
		String hql = "from Resource where url = :urlVar";
		Query resourceQuery = s.createQuery(hql);
		resourceQuery.setParameter("urlVar", url);
		
		Resource r = (Resource) resourceQuery.uniqueResult();
		return r;
	}
	
	// get resources by subject name
	@Transactional
	@Override
	public List<Resource> getResourcesBySubjectName(String subject, String[] filters) {
//		String hql = "select count(u.userId) as likes, r from Resource r inner join r.subject rs inner join r.userList u where rs.name = :resourceSubjectVar group by r.resourceId order by likes";
		
		String filterStr = "";
		
		if (filters != null) {
			filterStr += "and (r.source = '";	
			for(int i = 0; i < filters.length;i++) {
				filterStr += filters[i] + "' or r.source = '";
			}
			filterStr = filterStr.substring(0, filterStr.length()-15);
			filterStr += ") ";
		}
		
		String hql = "select r from Resource r inner join r.subject rs where rs.name = :resourceSubjectVar " + filterStr + "order by size(r.userList) desc";
		
		Session s = sf.getCurrentSession();
		Query query = s.createQuery(hql);
		query.setParameter("resourceSubjectVar", subject);
		
		final List<Resource> resources = query.list();
		
		return resources;
	}
	
	
	// get all resources
	@Transactional
	@Override
	public List<Resource> getAllResources() {
		Session s = sf.getCurrentSession();
		List<Resource> resources = s.createQuery("from Resource").list();
		return resources;
	}
	
	
	// update resource
	@Transactional
	@Override
	public int updateResource(Resource r) {
		int didItCommit = 0;
		Session s = sf.getCurrentSession();
		s.update(r);
		didItCommit = 1;
		return didItCommit;
	}
	
	
	// remove resource
	@Transactional
	@Override
	public int removeResource(Resource r) {
		int didItDelete = 0;
		Session s = sf.getCurrentSession();
		s.delete(r);
		didItDelete = 1;
		return didItDelete;
	}

}
