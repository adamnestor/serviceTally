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
@Table(name = "admin_profile")
public class AdminProfile {

	@Id
	private int adminId;

	@OneToOne
	@JoinColumn(name = "user_id")
	@MapsId
	private Users userId;

	private String firstName;
	private String lastName;
	private String city;
	private String state;

	@ManyToOne
	@JoinColumn(name = "school_id")
	private School school;

	@Column(nullable = true, length = 64)
	private String profilePhoto;

	public AdminProfile() {
	}

	public AdminProfile(int adminId, Users userId, String firstName, String lastName, String city, String state,
			School schoolId, String profilePhoto) {
		this.adminId = adminId;
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.state = state;
		this.school = schoolId;
		this.profilePhoto = profilePhoto;
	}

	public AdminProfile(Users users) {
		this.userId=users;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public Users getUserId() {
		return userId;
	}

	public void setUserId(Users userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public School getSchoolId() {
		return school;
	}

	public void setSchoolId(School schoolId) {
		this.school = schoolId;
	}

	public String getProfilePhoto() {
		return profilePhoto;
	}

	public void setProfilePhoto(String profilePhoto) {
		this.profilePhoto = profilePhoto;
	}

	@Override
	public String toString() {
		return "AdminProfile [adminId=" + adminId + ", userId=" + userId + ", firstName=" + firstName + ", lastName="
				+ lastName + ", city=" + city + ", state=" + state + ", schoolId=" + school + ", profilePhoto="
				+ profilePhoto + "]";
	}
	
	

}
