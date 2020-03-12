package com.revature.daos;

import java.util.List;

import javax.persistence.RollbackException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Blacklist;
import com.revature.models.BlacklistCompositeKey;
import com.revature.models.Resource;
import com.revature.models.Subject;
import com.revature.util.HibernateUtil;

public class BlacklistDaoImpl implements BlacklistDao{

	
	// add entry to blacklist
	
	@Override
	public BlacklistCompositeKey addToBlacklist(int resource_id, int subject_id) {
		
		BlacklistCompositeKey compkey = new BlacklistCompositeKey(resource_id, subject_id);
		
		Resource r = new Resource();
		r.setId(resource_id);
		
		Subject sub = new Subject();
		sub.setId(subject_id);
		
		Blacklist b = new Blacklist(compkey, r, sub);
		
		try (Session s = HibernateUtil.getSession()) {
			Transaction tx = s.beginTransaction();
			BlacklistCompositeKey bCKey = (BlacklistCompositeKey) s.save(b);
						
			tx.commit();
			
			return bCKey;
		}
	}

	
	// get a blacklist entry by composite id
	
	@Override
	public Blacklist getBlacklistByIds(int resource_id, int subject_id) {
		
		BlacklistCompositeKey compkey = new BlacklistCompositeKey(resource_id, subject_id);
		
		Blacklist b = null;
		
		try (Session s = HibernateUtil.getSession()) {
			b = s.get(Blacklist.class, compkey);
		}
		return b;
	}

	
	// get all entries from the blacklist
	
	@Override
	public List<Blacklist> getAllFromBlacklist() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	// update an entry in the blacklist

	@Override
	public int updateBlacklistEntry(Blacklist b) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	// remove an entry from the blacklist

	@Override
	public int removeBlacklistEntry(Blacklist b) {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
