package com.coderscampus.servicetally.domain;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class School {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int schoolId;
	
	private String schoolName;
	private String city;
	private String state;
	
	@OneToMany(mappedBy = "school")
	private Set<AdminProfile> adminProfile = new HashSet<>();
	
	@OneToMany(mappedBy = "school")
	private Set<StudentProfile> studentProfile = new HashSet<>();

	public School() {
	}

	public School(int schoolId, String schoolName, String city, String state, Set<AdminProfile> adminProfile,
			Set<StudentProfile> studentProfile) {
		this.schoolId = schoolId;
		this.schoolName = schoolName;
		this.city = city;
		this.state = state;
		this.adminProfile = adminProfile;
		this.studentProfile = studentProfile;
	}

	public int getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Set<AdminProfile> getAdminProfile() {
		return adminProfile;
	}

	public void setAdminProfile(Set<AdminProfile> adminProfile) {
		this.adminProfile = adminProfile;
	}

	public Set<StudentProfile> getStudentProfile() {
		return studentProfile;
	}

	public void setStudentProfile(Set<StudentProfile> studentProfile) {
		this.studentProfile = studentProfile;
	}

	@Override
	public String toString() {
		return "School [schoolId=" + schoolId + ", schoolName=" + schoolName + ", city=" + city + ", state=" + state
				+ ", adminProfile=" + adminProfile + ", studentProfile=" + studentProfile + "]";
	}

	
	
	
}
