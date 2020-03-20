package com.revature.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.revature.daos.ResourceDao;
import com.revature.models.Resource;

@Service
public class ResourceService {
	
	private static ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
	private static ResourceDao resourceDao = (ResourceDao) ac.getBean("resourceDaoImpl");
	private static ApiService apiService = (ApiService) ac.getBean("apiService");
	
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
	
	private Boolean checkDuplicateResource(Resource r) {
//		return resourceDao.getResourceByUrl(r.getUrl()) != null;
		return false;
	}
	
	private Resource makeResourceFromMap(Map<String, String> map, int index) {
		Resource r = new Resource();
		
		List<String> keys = new ArrayList<String>(map.keySet());
		
		r.setTitle(keys.get(index));
		r.setUrl(map.get(keys.get(index)));
		
		return r;
	}
	
	public List<Resource> getResourceList(String query, int listSize) {
		List<Resource> resources = new ArrayList<>();
		
		Map<String, String> wikipediaResults = apiService.searchWikipedia(query, listSize, 0);
		Map<String, String> googleBooksResults = apiService.searchGoogleBooks(query, listSize, 0);
		
		int currentIndex = 0;
		
		while(resources.size() < listSize && currentIndex < listSize) {
			
			Resource wikiRes = makeResourceFromMap(wikipediaResults, currentIndex);
			if(!checkDuplicateResource(wikiRes)) {
				resources.add(wikiRes);
			}
			
			Resource googleRes = makeResourceFromMap(googleBooksResults, currentIndex);
			if(!checkDuplicateResource(googleRes)) {
				resources.add(googleRes);
			}
			
			currentIndex++;
		}
		
		return resources;
	}

}
