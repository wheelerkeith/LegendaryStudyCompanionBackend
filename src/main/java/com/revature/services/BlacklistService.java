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
	
	// approve blacklist entry
	public int approveBlacklistEntry(Blacklist b) {
		b.setStatus("approved");
		return bd.updateBlacklistEntry(b);
	}
	
	// deny blacklist entry
		public int denyBlacklistEntry(Blacklist b) {
			b.setStatus("denied");
			return bd.updateBlacklistEntry(b);
		}
	
	// remove blacklist entry
	public int removeBlacklistEntry(Blacklist b) {
		return bd.removeBlacklistEntry(b);
	}

}
