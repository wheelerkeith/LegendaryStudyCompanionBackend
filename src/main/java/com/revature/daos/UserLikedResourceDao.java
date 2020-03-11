package com.revature.daos;

public interface UserLikedResourceDao {
	
	// get resource rating (count) - probably good idea to convert to criteria query
	public int getResourceRating(int id);

}
