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
	@JoinColumn(name = "school_admin_id", referencedColumnName = "user_account_id")
	private AdminProfile schoolAdminId;

	private String schoolName;
	private String city;
	private String state;
	private float hoursRequired;

	public School() {
	}

	public School(int schoolId, AdminProfile schoolAdminId, String schoolName, String city, String state,
			float hoursRequired) {
		this.schoolId = schoolId;
		this.schoolAdminId = schoolAdminId;
		this.schoolName = schoolName;
		this.city = city;
		this.state = state;
		this.hoursRequired = hoursRequired;
	}

	public int getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}

	public AdminProfile getSchoolAdminId() {
		return schoolAdminId;
	}

	public void setSchoolAdminId(AdminProfile schoolAdminId) {
		this.schoolAdminId = schoolAdminId;
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

	public float getHoursRequired() {
		return hoursRequired;
	}

	public void setHoursRequired(float hoursRequired) {
		this.hoursRequired = hoursRequired;
	}

	@Override
	public String toString() {
		return "School [schoolId=" + schoolId + ", schoolAdminId=" + schoolAdminId + ", schoolName=" + schoolName
				+ ", city=" + city + ", state=" + state + ", hoursRequired=" + hoursRequired + "]";
	}

	

}
