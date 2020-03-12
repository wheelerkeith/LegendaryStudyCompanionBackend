package com.revature.daos;

import java.util.List;

import com.revature.models.Blacklist;
import com.revature.models.BlacklistCompositeKey;

public interface BlacklistDao {
	
	// add new entry to blacklist
	public BlacklistCompositeKey addToBlacklist(int resource_id, int subject_id);
	
	// get blacklist entry by ids
	public Blacklist getBlacklistByIds(int resource_id, int subject_id);
	
	// get all blacklist entries
	public List<Blacklist> getAllFromBlacklist();
	
	// update blacklist entry
	public int updateBlacklistEntry(Blacklist b);
	
	// remove blacklist entry
	public int removeBlacklistEntry(Blacklist b);

}
