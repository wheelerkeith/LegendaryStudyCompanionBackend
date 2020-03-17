package com.revature.services;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.revature.daos.BlacklistDao;
import com.revature.models.Blacklist;
import com.revature.models.BlacklistCompositeKey;

@Service
public class BlacklistService {
	
	private static ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
	private static BlacklistDao blacklistDao = (BlacklistDao) ac.getBean("blacklistDaoImpl");
		
	// add new entry to blacklist
	public BlacklistCompositeKey addToBlacklist(int resource_id, int subject_id) {
		return blacklistDao.addToBlacklist(resource_id, subject_id);
	}
	
	// get blacklist entry by ids
	public Blacklist getBlacklistByIds(int resource_id, int subject_id) {
		return blacklistDao.getBlacklistByIds(resource_id, subject_id);
	}
	
	
	// get all blacklist entries
	public List<Blacklist> getAllFromBlacklist() {
		return blacklistDao.getAllFromBlacklist();
	}
	
	// update blacklist entry
	public int updateBlacklistEntry(int resourceId, int subjectId, String newStatus) {
		return blacklistDao.updateBlacklistEntry(new Blacklist(new BlacklistCompositeKey(resourceId, subjectId), newStatus));
	}
	
//	// deny blacklist entry
//	public int denyBlacklistEntry(Blacklist b) {
//		b.setStatus("denied");
//		return blacklistDao.updateBlacklistEntry(b);
//	}
	
	// remove blacklist entry
	public int removeBlacklistEntry(int resourceId, int subjectId) {
		return blacklistDao.removeBlacklistEntry(new Blacklist(new BlacklistCompositeKey(resourceId, subjectId)));
	}

}
