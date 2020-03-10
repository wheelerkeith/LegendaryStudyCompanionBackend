package com.revature.services;

import java.util.List;

import com.revature.daos.ResourceDao;
import com.revature.daos.ResourceDaoImpl;
import com.revature.models.Resource;

public class ResourceService {
	
	private static ResourceDao rd = new ResourceDaoImpl(); 
	
	// add new resource
	public int addResource(Resource r) {
		return rd.addResource(r);
	}
	
	// get resource by id
	public Resource getResourceById(int id) {
		return rd.getResourceById(id);
	}
	
	// get all resources
	public List<Resource> getAllResources() {
		return rd.getAllResources();
	}
	
	// update resource
	public int updateResource(Resource r) {
		return rd.updateResource(r);
	}
	
	// remove resource
	public int removeResource(Resource r) {
		return rd.removeResource(r);
	}

}
