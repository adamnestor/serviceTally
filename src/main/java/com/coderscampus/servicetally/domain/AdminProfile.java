package com.coderscampus.servicetally.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "admin_profile")
public class AdminProfile {

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

	@OneToMany(mappedBy = "schoolAdmin", cascade = CascadeType.ALL)
	private List<School> schoolsManaged = new ArrayList<>();

	@Column(nullable = true, length = 64)
	private String profilePhoto;

	public AdminProfile() {
	}
	
	public AdminProfile(Users users) {
		this.userId = users;
	}

	public AdminProfile(int userAccountId, Users userId, String firstName, String lastName, String city, String state,
			List<School> schoolsManaged, String profilePhoto) {
		this.userAccountId = userAccountId;
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.state = state;
		this.schoolsManaged = schoolsManaged;
		this.profilePhoto = profilePhoto;
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

	public List<School> getSchoolsManaged() {
		return schoolsManaged;
	}

	public void setSchoolsManaged(List<School> schoolsManaged) {
		this.schoolsManaged = schoolsManaged;
	}

	public String getProfilePhoto() {
		return profilePhoto;
	}

	public void setProfilePhoto(String profilePhoto) {
		this.profilePhoto = profilePhoto;
	}

	@Transient
	public String getPhotosImagePath() {
		if (profilePhoto == null)
			return null;
		return "/photos/admin/" + userAccountId + "/" + profilePhoto;
	}

	@Override
	public String toString() {
		return "AdminProfile [userAccountId=" + userAccountId + ", userId=" + userId + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", city=" + city + ", state=" + state + ", schoolsManaged="
				+ schoolsManaged + ", profilePhoto=" + profilePhoto + "]";
	}

	

}
