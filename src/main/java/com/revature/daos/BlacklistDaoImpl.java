package com.revature.daos;

import java.util.List;

import org.hibernate.query.Query;
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
		b.setStatus("pending");
		
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
		
		List<Blacklist> blacklistList = null;
		
		try (Session s = HibernateUtil.getSession()) {
			String hql = "from Blacklist";
			
			Query<Blacklist> q = s.createQuery(hql, Blacklist.class);
			blacklistList = q.list();
		}
		
		return blacklistList;
	}
	
	
	// update an entry in the blacklist

	@Override
	public int updateBlacklistEntry(Blacklist b) {
		
		int didItUpdate = 0;
		
		try (Session s = HibernateUtil.getSession()) {
			Transaction tx = s.beginTransaction();
			s.update(b);
			tx.commit();
			didItUpdate = 1;
		}
		return didItUpdate;
	}
	
	
	// remove an entry from the blacklist

	@Override
	public int removeBlacklistEntry(Blacklist b) {
		
		int didItDelete = 0;
		
		try (Session s = HibernateUtil.getSession()) {
			Transaction tx = s.beginTransaction();
			s.delete(b);
			tx.commit();
			didItDelete = 1;
		}
		return didItDelete;
	}

}
