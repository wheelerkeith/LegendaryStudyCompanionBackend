package com.revature.daos;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.RollbackException;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.revature.models.User;

@Repository
public class UserDaoImpl implements UserDao{
	
	@Autowired
	private SessionFactory sf;
	
	// add new user
	@Transactional
	@Override
	public int addUser(User u) {
		Session s = sf.getCurrentSession();
		int pk = (int) s.save(u);
		return pk;
	}
	
	
	// validate user with log in info
	@Transactional
	@Override
	public User validateUser(User u) {
		
		if (u.getUserName() == null) {
			u.setUserName("");
		}
		
		if (u.getEmail() == null) {
			u.setEmail("");
		}
		
		Session s = sf.getCurrentSession();
		String hql = "from User where (user_name = :userNameVar or email = :emailVar) and (password = :passVar)";
		Query userQuery = s.createQuery(hql);
		userQuery.setParameter("userNameVar", u.getUserName());
		userQuery.setParameter("emailVar", u.getEmail());
		userQuery.setParameter("passVar", u.getPassword());
		
		List<User> u2 = userQuery.list();
		
		if (u2.isEmpty()) {
			System.out.println("MESSAGE FROM UserDaoImpl.validateUser: no users found in db - add logic to handle this");
			return null;
		} else if (u2.size() > 1) {
			System.out.println("MESSAGE FROM UserDaoImpl.validateUser: more than one user found with credentials, something is not right");
		}
		
		return u2.get(0);
	}
	
	
	// get user by id
	@Transactional
	@Override
	public User getUserById(int id) {
		Session s = sf.getCurrentSession();
		User user = (User) s.get(User.class, id);
		return user;
	}
	
	
	// get all users
	@Transactional
	@Override
	public List<User> getAllUsers() {
		Session s = sf.getCurrentSession();
		List<User> users = s.createQuery("from User").list();
		return users;
	}
	
	
	// update user information
	@Transactional
	@Override
	public int updateUser(User u) {
		int didItCommit = 0;
		
		if (u.getPassword() == "") {
			User ghostUser = new User();
			ghostUser.setFullName(u.getFullName());
			ghostUser.setEmail(u.getEmail());
			ghostUser.setUserName(u.getUserName());
			
			u = getUserById(u.getUserId());
			
			u.setFullName(ghostUser.getFullName());
			u.setEmail(ghostUser.getEmail());
			u.setUserName(ghostUser.getUserName());
		}
		
		Session s = sf.getCurrentSession();
		
		s.update(u);
		didItCommit = 1;
		
		return didItCommit;
	}
	
	
	// remove user
	// https://www.codejava.net/frameworks/hibernate/hibernate-basics-3-ways-to-delete-an-entity-from-the-datastore
	@Transactional
	@Override
	public int removeUser(int id) {
		int didItDelete = 0;
		
		Session s = sf.getCurrentSession();
		User user = (User) s.load(User.class, id);
		s.delete(user);
		didItDelete = 1;
		
		return didItDelete;
	}

}
