package com.coderscampus.servicetally.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class School {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int schoolId;
	
	@ManyToOne
	@JoinColumn(name = "schoolAdminId", referencedColumnName = "userId")
	private Users schoolAdminId;

	private String schoolName;
	private String city;
	private String state;
	private float hoursRequired;

	public School() {
	}

	

	

}
