package com.revature.daos;

import com.revature.models.User;

public interface UserLikedResourceDao {
	
	// get resource rating (count) - probably good idea to convert to criteria query
	public int getResourceRating(int id);
	
	// get if resource is saved
		public boolean isSaved(User u, int id);

}
