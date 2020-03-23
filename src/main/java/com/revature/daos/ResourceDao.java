package com.revature.daos;

import java.util.List;

import com.revature.models.Resource;

public interface ResourceDao {
	
	// add new resource
	public int addResource(Resource r);
	
	// get resource by id
	public Resource getResourceById(int id);
	
	// get resource by url
	public Resource getResourceByUrl(String url);
	
	// get resources by subject name
	public List<Resource> getResourcesBySubjectName(String subject, String[] filters);
	
	// get all resources
	public List<Resource> getAllResources();
	
	// update resource
	public int updateResource(Resource r);
	
	// remove resource
	public int removeResource(Resource r);

}
