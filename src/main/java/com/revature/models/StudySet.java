package com.revature.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Entity
@Component
public class StudySet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="studyset_id")
	private int id;
	private String name;
	
	@Autowired
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
			name = "studyset_user",
			joinColumns = { @JoinColumn(name = "studyset_id")},
			inverseJoinColumns = { @JoinColumn(name = "user_id")})
	private List<User> user;
	
	@Autowired
	@ManyToMany(mappedBy="studySet", fetch=FetchType.EAGER)
	private List<Resource> resourceList;
	
	public StudySet() {
		super();
	}
	
	public StudySet(String name, User user) {
		this.name = name;
		
		List<User> newUserList = new ArrayList<>();
		newUserList.add(user);
		
		this.user = newUserList;
		
	}
	
	public StudySet(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public StudySet(int id, String name, User user) {
		super();
		this.id = id;
		this.name = name;
		this.user.add(user);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUser() {
		return null;
	}

	public void setUserId(List<User> user) {
		this.user = user;
	}
	
	public List<Resource> getResourceList() {
		return resourceList;
	}
	
	public void setResourceList(List<Resource> resourceList) {
		this.resourceList = resourceList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((resourceList == null) ? 0 : resourceList.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		StudySet other = (StudySet) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (resourceList == null) {
			if (other.resourceList != null)
				return false;
		} else if (!resourceList.equals(other.resourceList))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StudySet [id=" + id + ", name=" + name + ", user=" + user + ", resourceList=" + resourceList + "]";
	}
	
}
