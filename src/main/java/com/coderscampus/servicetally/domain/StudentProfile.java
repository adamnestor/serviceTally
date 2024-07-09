package com.coderscampus.servicetally.domain;

import jakarta.persistence.CascadeType;
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
	private int userAccountId;

	@OneToOne
	@JoinColumn(name = "user_account_id")
	@MapsId
	private Users userId;

	private String firstName;
	private String lastName;
	private String city;
	private String state;
	private String graduationYear;

	@Column(nullable = true, length = 64)
	private String profilePhoto;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "school_id")
	private School school;

	public StudentProfile() {
	}

	public StudentProfile(int userAccountId, Users userId, String firstName, String lastName, String city, String state,
			String graduationYear, String profilePhoto, School school) {
		this.userAccountId = userAccountId;
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.state = state;
		this.graduationYear = graduationYear;
		this.profilePhoto = profilePhoto;
		this.school = school;
	}

	public StudentProfile(Users users) {
		this.userId = users;
	}

	public int getUserAccountId() {
		return userAccountId;
	}

	public void setUserAccountId(int userAccountId) {
		this.userAccountId = userAccountId;
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

	public String getGraduationYear() {
		return graduationYear;
	}

	public void setGraduationYear(String graduationYear) {
		this.graduationYear = graduationYear;
	}

	public String getProfilePhoto() {
		return profilePhoto;
	}

	public void setProfilePhoto(String profilePhoto) {
		this.profilePhoto = profilePhoto;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	@Override
	public String toString() {
		return "StudentProfile [userAccountId=" + userAccountId + ", userId=" + userId + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", city=" + city + ", state=" + state + ", graduationYear="
				+ graduationYear + ", profilePhoto=" + profilePhoto + ", schoolId=" + school + "]";
	}

}
