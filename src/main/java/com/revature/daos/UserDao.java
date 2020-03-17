package com.revature.daos;

import java.util.List;

import com.revature.models.User;

public interface UserDao {
	
	// add new user
	public int addUser(User u);
	
	// validate user with log in info
	public User validateUser(User u);

	// get user by id
	public User getUserById(int id);
	
	// get all users
	public List<User> getAllUsers();
	
	// update user information
	public int updateUser(User u);
	
	// remove user
	public int removeUser(int id);

}