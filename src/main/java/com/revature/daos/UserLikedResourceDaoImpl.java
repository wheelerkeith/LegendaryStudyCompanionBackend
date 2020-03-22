package com.revature.daos;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

@Repository
public class UserLikedResourceDaoImpl implements UserLikedResourceDao{

	@Autowired
	SessionFactory sf;
	
	// get resource rating (count) - probably good idea to convert to criteria query ??
	@Transactional
	@Override
	public int getResourceRating(int id) {
		String hql = "select count(u.userId) from User u join u.likedResources rl  where rl.resourceId = :resourceIdVar";
		
		Session s = sf.getCurrentSession();
		Query query = s.createQuery(hql);
		query.setParameter("resourceIdVar", id);
		
		Long rating = (Long) query.uniqueResult();
		return rating.intValue();
	}

}
