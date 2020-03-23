package com.revature.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.revature.daos.ResourceDao;
import com.revature.daos.SubjectDao;
import com.revature.services.UserLikedResourceService;
import com.revature.models.Resource;
import com.revature.models.Subject;
import com.revature.models.User;

@Service
public class ResourceService {
	
	private static ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
	private static ResourceDao resourceDao = (ResourceDao) ac.getBean("resourceDaoImpl");
	private static UserLikedResourceService likedResService = (UserLikedResourceService) ac.getBean("userLikedResourceService");
	private static SubjectDao subjectDao = (SubjectDao) ac.getBean("subjectDaoImpl");
	private static ApiService apiService = (ApiService) ac.getBean("apiService");
	private static SubjectService subjectService = (SubjectService) ac.getBean("subjectService");
	
	// add new resource
	public int addResource(Resource r) {
		
		// Check for subject in DB
		Subject sub = subjectService.getSubjectByName(r.getSubject().getName());
		
		// If subject DNE then create it
		if (sub != null) {
			r.setSubject(sub);
		} else {
			r.getSubject().setId(subjectService.addSubjectByName(r.getSubject().getName()));
		}
		
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
	
	private List<Resource> sortResourcesByLikeCount(List<Resource> resources) {
		
		Comparator<Resource> compareByLikeCount = (Resource o1, Resource o2) -> Integer.compare(o1.getLikeCount(), o2.getLikeCount());
		
		Collections.sort(resources, compareByLikeCount.reversed());
		
		return resources;
	}
	
	public List<Resource> getResourceList(String query, int listSize, User u) {
		List<Resource> resources = new ArrayList<>();
		
		// TODO: Add limit to dbResources so not pulling ALL at once
		List<Resource> dbResources = resourceDao.getResourcesBySubjectName(query);
		
		// Populate list from saved resources first
		for(Resource r : dbResources) {
			if(resources.size() < listSize) {
				r.setLikeCount(likedResService.getResourceRating(r.getResourceId()));
				r.setSaved(likedResService.isSaved(u, r.getResourceId()));
				resources.add(r);
			}
		}
		
		resources = sortResourcesByLikeCount(resources);
		
		// If list is still too small then populate from APIs
		if (resources.size() < listSize) {
			
			// Pull subject from DB if it doesnt exist make a new one
			Subject sub = subjectDao.getSubjectByName(query);
			
			if (sub == null) {
				sub = new Subject(query);
			}
			
			Map<String, String> wikipediaResults = apiService.searchWikipedia(query, listSize, 0);
			Map<String, String> googleBooksResults = apiService.searchGoogleBooks(query, listSize, 0);
			
			int currentIndex = 0;
			
			while(resources.size() < listSize && currentIndex < listSize) {
				
				// TODO: Check if resource is blacklisted
				Resource wikiRes = makeResourceFromMap(wikipediaResults, currentIndex);
				if(!checkDuplicateResource(wikiRes)) {
					wikiRes.setSubject(sub);
					wikiRes.setSource("Wikipedia");
					resources.add(wikiRes);
				}
				
				Resource googleRes = makeResourceFromMap(googleBooksResults, currentIndex);
				if(!checkDuplicateResource(googleRes)) {
					googleRes.setSubject(sub);
					googleRes.setSource("GoogleBooks");
					resources.add(googleRes);
				}
				
				currentIndex++;
			}
		}
		
		return resources;
	}

}
