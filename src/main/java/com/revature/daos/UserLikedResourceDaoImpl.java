package com.revature.daos;

import org.hibernate.query.Query;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.revature.util.HibernateUtil;

public class UserLikedResourceDaoImpl implements UserLikedResourceDao{

	
	// get resource rating (count) - probably good idea to convert to criteria query
	
	@Override
	public int getResourceRating(int id) {
		
		List<BigInteger> rating = new ArrayList<>();
		
		try (Session s = HibernateUtil.getSession()) {
			String sql = "select count(user_id) from user_liked_resource where resource_id = ?";
			Query q = s.createNativeQuery(sql);
			
			q.setParameter(1, id);
			
			rating = q.getResultList();
			
		}
		
		
		return rating.get(0).intValue();
	}

}
