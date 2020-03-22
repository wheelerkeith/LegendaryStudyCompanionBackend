package com.revature.models;

import java.io.Serializable;
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
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Entity
@Component
public class Resource implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="resource_id")
	private int resourceId;
	
	private String title;
	private String source;
	private String url;
	
	@Transient
	private int likeCount = 0;
	
	@Autowired
	@ManyToOne
	@JoinColumn(name="subject_id")
	private Subject subject;
	
	@Autowired
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
			name = "studyset_resources",
			joinColumns = { @JoinColumn(name = "resource_id")},
			inverseJoinColumns = { @JoinColumn(name = "studyset_id")})
	private List<StudySet> studySet;
	
	
	public Resource() {
		super();
	}
	
	public Resource(String url, Subject subject, String source) {
		this.url = url;
		this.subject = subject;
		this.source = source;
	}

	public int getResourceId() {
		return resourceId;
	}


	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public Subject getSubject() {
		return subject;
	}


	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public List<StudySet> getStudySet() {
		return null;
	}

	public void setStudySet(List<StudySet> studySet) {
		this.studySet = studySet;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + likeCount;
		result = prime * result + resourceId;
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		result = prime * result + ((studySet == null) ? 0 : studySet.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		if (likeCount != other.likeCount)
			return false;
		if (resourceId != other.resourceId)
			return false;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
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
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
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
		return "Resource [resourceId=" + resourceId + ", title=" + title + ", source=" + source + ", url=" + url
				+ ", likeCount=" + likeCount + ", subject=" + subject + ", studySet=" + studySet + "]";
	}

}