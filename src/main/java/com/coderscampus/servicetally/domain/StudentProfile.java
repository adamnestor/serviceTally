package com.coderscampus.servicetally.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "student_profile")
public class StudentProfile {

	@Id
	private int studentId;

	@OneToOne
	@JoinColumn(name = "user_id")
	@MapsId
	private Users userId;

	private String firstName;
	private String lastName;
	private String city;
	private String state;
	private String graduationYear;

	@Column(nullable = true, length = 64)
	private String profilePhoto;

	@ManyToOne
	@JoinColumn(name = "school_id", referencedColumnName = "school_id")
	private School schoolId;

}
