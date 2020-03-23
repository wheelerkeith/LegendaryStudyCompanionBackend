package com.revature.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.JoinColumn;

@Entity
@Component
@Table(name="app_user")
public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private int userId;
	
	@Column(name="user_name", unique=true, nullable=false)
	private String userName;
	@Column(nullable=false)
	private String password;
	@Column(unique=true, nullable=false)
	private String email;
	@Column(name="full_name", nullable=false)
	private String fullName;
	private int role;
	
	// bideractional many-to-many (the join is in the studyset class)
	//
	// https://www.javaworld.com/article/3387643/java-persistence-with-jpa-and-hibernate-part-2-many-to-many-relationships.html
//	@Autowired
//	@ManyToMany(mappedBy = "user", fetch=FetchType.EAGER)
//	private List<StudySet> studySets;
	
	@Autowired
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
			name="user_liked_resource",
			joinColumns= {@JoinColumn(name="user_id")},
			inverseJoinColumns= {@JoinColumn(name="resource_id")})
	private Set<Resource> resourceList = new HashSet<>();
	
	
	public User() {
		super();
	}
	
	public User(String userName, String password, String email, String fullName, int role) {
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.fullName = fullName;
		this.role = role;
	}
	
	public User(String userName, String password, String email) {
		this.userName = userName;
		this.password = password;
		this.email = email;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}
	
//	public List<StudySet> getStudySets() {
//		return studySets;
//	}
//
//
//	public void setStudySets(List<StudySet> studySets) {
//		this.studySets = studySets;
//	}

	public Set<Resource> getResourceList() {
		return resourceList;
	}

	public void setResourceList(Set<Resource> resourceList) {
		this.resourceList = resourceList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((fullName == null) ? 0 : fullName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((resourceList == null) ? 0 : resourceList.hashCode());
		result = prime * result + role;
//		result = prime * result + ((studySets == null) ? 0 : studySets.hashCode());
		result = prime * result + userId;
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (fullName == null) {
			if (other.fullName != null)
				return false;
		} else if (!fullName.equals(other.fullName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (resourceList == null) {
			if (other.resourceList != null)
				return false;
		} else if (!resourceList.equals(other.resourceList))
			return false;
		if (role != other.role)
			return false;
//		if (studySets == null) {
//			if (other.studySets != null)
//				return false;
//		} else if (!studySets.equals(other.studySets))
//			return false;
		if (userId != other.userId)
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + ", email=" + email
				+ ", fullName=" + fullName + ", role=" + role + /*", studySets=" + studySets + */", resourceList="
				+ resourceList + "]";
	}

}
