package com.coderscampus.servicetally.domain;

public class ServiceHoursDto {

	private float totalHours;
	private float requiredHours;
	
	public ServiceHoursDto(float totalHours, float requiredHours) {
		this.totalHours = totalHours;
		this.requiredHours = requiredHours;
	}

	public float getTotalHours() {
		return totalHours;
	}

	public void setTotalHours(float totalHours) {
		this.totalHours = totalHours;
	}

	public float getRequiredHours() {
		return requiredHours;
	}

	public void setRequiredHours(float requiredHours) {
		this.requiredHours = requiredHours;
	}
	
}
