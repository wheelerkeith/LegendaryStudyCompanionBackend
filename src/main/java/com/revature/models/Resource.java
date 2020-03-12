package com.revature.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Resource implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="resource_id")
	private int id;
	
	private String url;
	
	@ManyToOne
	@JoinColumn(name="subject_id")
	private Subject subject;
	
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
			name = "studyset_resources",
			joinColumns = { @JoinColumn(name = "resource_id")},
			inverseJoinColumns = { @JoinColumn(name = "studyset_id")})
	private List<StudySet> studySet;
	
	
	public Resource() {
		super();
	}
	
	public Resource(String url, Subject subject) {
		this.url = url;
		this.subject = subject;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public Subject getSubject() {
		return subject;
	}


	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public List<StudySet> getStudySet() {
		return studySet;
	}

	public void setStudySet(List<StudySet> studySet) {
		this.studySet = studySet;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((studySet == null) ? 0 : studySet.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
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
		Resource other = (Resource) obj;
		if (id != other.id)
			return false;
		if (studySet == null) {
			if (other.studySet != null)
				return false;
		} else if (!studySet.equals(other.studySet))
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Resource [id=" + id + ", url=" + url + ", subject=" + subject + ", studySet=" + studySet + "]";
	}

}