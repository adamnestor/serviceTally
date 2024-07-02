package com.coderscampus.servicetally.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "school")
public class School {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int schoolId;
	
	private String schoolName;
	private String city;
	private String state;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "admin_profile")
	private AdminProfile adminProfile;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "student_profile")
	private StudentProfile studentProfile;

	public School() {
	}

	public School(int schoolId, String schoolName, String city, String state, AdminProfile adminProfile,
			StudentProfile studentProfile) {
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

	public AdminProfile getAdminProfile() {
		return adminProfile;
	}

	public void setAdminProfile(AdminProfile adminProfile) {
		this.adminProfile = adminProfile;
	}

	public StudentProfile getStudentProfile() {
		return studentProfile;
	}

	public void setStudentProfile(StudentProfile studentProfile) {
		this.studentProfile = studentProfile;
	}

	@Override
	public String toString() {
		return "School [schoolId=" + schoolId + ", schoolName=" + schoolName + ", city=" + city + ", state=" + state
				+ ", adminProfile=" + adminProfile + ", studentProfile=" + studentProfile + "]";
	}
	
	
}
