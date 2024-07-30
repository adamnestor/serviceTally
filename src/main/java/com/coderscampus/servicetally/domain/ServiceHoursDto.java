package com.coderscampus.servicetally.domain;

public class ServiceHoursDto {

	private float submittedHours;
	private float approvedHours;
	private float requiredHours;
	
	public ServiceHoursDto(float submittedHours, float approvedHours, float requiredHours) {
		this.submittedHours = submittedHours;
		this.approvedHours = approvedHours;
		this.requiredHours = requiredHours;
	}
	public float getSubmittedHours() {
		return submittedHours;
	}
	public void setSubmittedHours(float submittedHours) {
		this.submittedHours = submittedHours;
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
