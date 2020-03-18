package com.revature.services;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.revature.daos.ResourceDao;
import com.revature.models.Resource;

@Service
public class ResourceService {
	
	private static ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
	private static ResourceDao resourceDao = (ResourceDao) ac.getBean("resourceDaoImpl");
	
	// add new resource
	public int addResource(Resource r) {
		return resourceDao.addResource(r);
	}
	
	// get resource by id
	public Resource getResourceById(int id) {
		return resourceDao.getResourceById(id);
	}
	
	// get resource by URL
	public Resource getResourceByUrl(String url) {
		return resourceDao.getResourceByUrl(url);
	}
	
	// get all resources
	public List<Resource> getAllResources() {
		return resourceDao.getAllResources();
	}
	
	// update resource
	public int updateResource(Resource r) {
		return resourceDao.updateResource(r);
	}
	
	// remove resource
	public int removeResource(Resource r) {
		return resourceDao.removeResource(r);
	}

}
