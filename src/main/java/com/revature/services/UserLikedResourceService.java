package com.revature.services;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.revature.daos.UserLikedResourceDao;

@Service
public class UserLikedResourceService {
	
	private static ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
	private static UserLikedResourceDao userLikedResourceDao = (UserLikedResourceDao) ac.getBean("userLikedResourceDaoImpl");
	
	// get resource rating (count)
	public int getResourceRating(int id) {
		return userLikedResourceDao.getResourceRating(id);
	}

}
