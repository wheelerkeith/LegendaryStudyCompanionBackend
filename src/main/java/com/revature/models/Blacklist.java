package com.revature.models;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class Blacklist implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	BlacklistCompositeKey compKey;
	
	@MapsId("resource")
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumns({
		@JoinColumn(name="resource_id", referencedColumnName="resource_id")
	})
	Resource resource;
	
	@MapsId("subject")
	@JoinColumns({
		@JoinColumn(name="subject_id", referencedColumnName="subject_id")
	})
	@ManyToOne(fetch=FetchType.EAGER)
	Subject subject;
	
	private String status;

	public Blacklist() {
		super();
	}
	
	public Blacklist(Resource resource, Subject subject) {
		this.resource = resource;
		this.subject = subject;
	}
	
	public Blacklist(BlacklistCompositeKey compKey, Resource resource, Subject subject) {
		this.compKey = compKey;
		this.resource = resource;
		this.subject = subject;
	}
	
	
	// setters and getters

	public BlacklistCompositeKey getCompKey() {
		return compKey;
	}

	public void setCompKey(BlacklistCompositeKey compKey) {
		this.compKey = compKey;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((compKey == null) ? 0 : compKey.hashCode());
		result = prime * result + ((resource == null) ? 0 : resource.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
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
		Blacklist other = (Blacklist) obj;
		if (compKey == null) {
			if (other.compKey != null)
				return false;
		} else if (!compKey.equals(other.compKey))
			return false;
		if (resource == null) {
			if (other.resource != null)
				return false;
		} else if (!resource.equals(other.resource))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Blacklist [compKey=" + compKey + ", resource=" + resource + ", subject=" + subject + ", status="
				+ status + "]";
	}
	
}
