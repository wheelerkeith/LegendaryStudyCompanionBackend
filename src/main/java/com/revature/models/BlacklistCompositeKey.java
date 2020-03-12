package com.revature.models;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class BlacklistCompositeKey implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int resource;
	private int subject;
	
	public BlacklistCompositeKey() {
		super();
	}
	
	public BlacklistCompositeKey(int resource, int subject) {
		this.resource = resource;
		this.subject = subject;
		
	}
	
	
	// setter and getters
	
	public int getResource() {
		return resource;
	}

	public void setResource(int resource) {
		this.resource = resource;
	}

	public int getSubject() {
		return subject;
	}

	public void setSubject(int subject) {
		this.subject = subject;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + resource;
		result = prime * result + subject;
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
		BlacklistCompositeKey other = (BlacklistCompositeKey) obj;
		if (resource != other.resource)
			return false;
		if (subject != other.subject)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BlacklistCompositeKey [resource=" + resource + ", subject=" + subject + "]";
	}
	
	
	
}
