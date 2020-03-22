package com.revature.daos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.revature.models.Blacklist;
import com.revature.models.BlacklistCompositeKey;
import com.revature.models.Resource;
import com.revature.models.Subject;

@Repository
public class BlacklistDaoImpl implements BlacklistDao{

	@Autowired
	SessionFactory sf;
	
	// add entry to blacklist
	@Transactional
	@Override
	public BlacklistCompositeKey addToBlacklist(int resource_id, int subject_id) {
		BlacklistCompositeKey compKey = new BlacklistCompositeKey(resource_id, subject_id);
		
		Resource r = new Resource();
		r.setResourceId(resource_id);
		
		Subject sub = new Subject();
		sub.setId(subject_id);
		
		Blacklist b = new Blacklist(compKey, r, sub);
		b.setStatus("Pending");
		
		Session s = sf.getCurrentSession();
		BlacklistCompositeKey blacklistCompositeKey = (BlacklistCompositeKey) s.save(b);
		return blacklistCompositeKey;
	}

	
	// get a blacklist entry by composite id
	@Transactional
	@Override
	public Blacklist getBlacklistByIds(int resource_id, int subject_id) {
		BlacklistCompositeKey compKey = new BlacklistCompositeKey(resource_id, subject_id);
		
		Session s = sf.getCurrentSession();
		Blacklist blacklist = (Blacklist) s.get(Blacklist.class, compKey);
		return blacklist;
	}

	
	// get all entries from the blacklist
	@Transactional
	@Override
	public List<Blacklist> getAllFromBlacklist() {
		Session s = sf.getCurrentSession();
		return s.createQuery("from Blacklist").list();
	}
	
	
	// update an entry in the blacklist
	@Transactional
	@Override
	public int updateBlacklistEntry(Blacklist b) {
		int didItUpdate = 0;
		Session s = sf.getCurrentSession();
		s.update(b);
		didItUpdate = 1;
		return didItUpdate;
	}
	
	
	// remove an entry from the blacklist
	@Transactional
	@Override
	public int removeBlacklistEntry(Blacklist b) {
		int didItDelete = 0;
		
		Session s = sf.getCurrentSession();
		s.delete(b);
		didItDelete = 1;
		return didItDelete;
	}

}
