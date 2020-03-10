package com.revature.daos;

import java.util.List;

import com.revature.models.Resource;

public interface ResourceDao {
	
	// add new resource
	public int addResource(Resource r);
	
	// get resource by id
	public Resource getResourceById(int id);
	
	// get all resources
	public List<Resource> getAllResources();
	
	// update resource
	public int updateResource(Resource r);
	
	// remove resource
	public int removeResource(Resource r);

}
