package com.revature.daos;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.RollbackException;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.User;
import com.revature.util.HibernateUtil;

public class UserDaoImpl implements UserDao{
	
	
	// add new user

	@Override
	public int addUser(User u) {
		
		try(Session s = HibernateUtil.getSession()) {
			Transaction tx = s.beginTransaction();
			int pk = (int) s.save(u);
			
			tx.commit();
			return pk;
		}
	}
	
	
	// validate user with log in info

	@Override
	public User validateUser(User u) {
		
		if (u.getUserName() == null) {
			u.setUserName("");
		}
		
		if (u.getEmail() == null) {
			u.setEmail("");
		}
		
		try(Session s = HibernateUtil.getSession()) {
			String hql = "from User where (username = :userNameVar or email = :emailVar) and (password = :passVar)";
			Query<User> userQuery = s.createQuery(hql, User.class);
			userQuery.setParameter("userNameVar", u.getUserName());
			userQuery.setParameter("emailVar", u.getEmail());
			userQuery.setParameter("passVar", u.getPassword());
			
			User u2 = userQuery.getSingleResult();
			
			return u2;
		} catch (NoResultException e) {
			e.printStackTrace();
		}
		
		return null;

	}
	
	
	// get user by id

	@Override
	public User getUserById(int id) {
		
		User u = null;
		
		try(Session s = HibernateUtil.getSession()) {
			u = s.get(User.class, id);
		}
		
		return u;
	}
	
	
	// get all users

	@Override
	public List<User> getAllUsers() {
		
		List<User> userList = null;
		
		try(Session s = HibernateUtil.getSession()) {
			String hql = "from User";
			Query<User> q = s.createQuery(hql, User.class);
			userList = q.list();
		}
		
		return userList;
	}
	
	
	// update user information

	@Override
	public int updateUser(User u) {
		
		int didItCommit = 0;
		
		try(Session s = HibernateUtil.getSession()) {
			Transaction tx = s.beginTransaction();
			s.update(u);
			tx.commit();
			didItCommit = 1;
		} catch (RollbackException e) {
			e.printStackTrace();
			System.out.println("commit from updateUser failed");
			didItCommit = 0;
		}
		
		return didItCommit;
	}
	
	
	// remove user

	@Override
	public int removeUser(User u) {
		
		int didItDelete = 0;
		
		try(Session s = HibernateUtil.getSession()) {
			Transaction tx = s.beginTransaction();
			s.delete(u);
			tx.commit();
			didItDelete = 1;
		} catch (RollbackException e) {
			e.printStackTrace();
			System.out.println("commit from removeUser failed");
			didItDelete = 0;
		}
		
		return didItDelete;
		
	}

}
