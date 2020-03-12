package com.revature.services;

import java.util.List;

import com.revature.daos.BlacklistDao;
import com.revature.daos.BlacklistDaoImpl;
import com.revature.models.Blacklist;
import com.revature.models.BlacklistCompositeKey;
import com.revature.models.Resource;

public class BlacklistService {
	
	private static BlacklistDao bd = new BlacklistDaoImpl();
	
	// add new entry to blacklist
	public BlacklistCompositeKey addToBlacklist(int resource_id, int subject_id) {
		return bd.addToBlacklist(resource_id, subject_id);
	}
	
	// get blacklist entry by ids
	public Blacklist getBlacklistByIds(int resource_id, int subject_id) {
		return bd.getBlacklistByIds(resource_id, subject_id);
	}
	
	
	// get all blacklist entries
	public List<Blacklist> getAllFromBlacklist() {
		return bd.getAllFromBlacklist();
	}
	
	// update blacklist entry
	public int updateBlacklistEntry(Blacklist b) {
		return bd.updateBlacklistEntry(b);
	}
	
	// remove blacklist entry
	public int removeBlacklistEntry(Blacklist b) {
		return bd.removeBlacklistEntry(b);
	}

}
