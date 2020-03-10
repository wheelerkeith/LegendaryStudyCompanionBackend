package com.revature.services;

import com.revature.daos.UserLikedResourceDao;
import com.revature.daos.UserLikedResourceDaoImpl;

public class UserLikedResourceService {
	
	private static UserLikedResourceDao ulrd = new UserLikedResourceDaoImpl();
	
	// get resource rating (count)
	public int getResourceRating(int id) {
		return ulrd.getResourceRating(id);
	}

}
