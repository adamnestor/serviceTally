package com.coderscampus.servicetally.domain;

public class GraduationYearDto {

	private int userAccountId;
	private String firstName;
	private String lastName;
	private String graduationYear;
	private CompletedStatus completedStatus;
	private float approvedHours;
	private float requiredHours;
	
	public GraduationYearDto(int userAccountId, String firstName, String lastName, String graduationYear,
			CompletedStatus completedStatus, float approvedHours, float requiredHours) {
		this.userAccountId = userAccountId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.graduationYear = graduationYear;
		this.completedStatus = completedStatus;
		this.approvedHours = approvedHours;
		this.requiredHours = requiredHours;
	}

	public int getUserAccountId() {
		return userAccountId;
	}

	public void setUserAccountId(int userAccountId) {
		this.userAccountId = userAccountId;
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

	public String getGraduationYear() {
		return graduationYear;
	}

	public void setGraduationYear(String graduationYear) {
		this.graduationYear = graduationYear;
	}

	public CompletedStatus getCompletedStatus() {
		return completedStatus;
	}

	public void setCompletedStatus(CompletedStatus completedStatus) {
		this.completedStatus = completedStatus;
	}

	public float getApprovedHours() {
		return approvedHours;
	}

	public void setApprovedHours(float approvedHours) {
		this.approvedHours = approvedHours;
	}

	public float getRequiredHours() {
		return requiredHours;
	}

	public void setRequiredHours(float requiredHours) {
		this.requiredHours = requiredHours;
	}
	
	
	
}
