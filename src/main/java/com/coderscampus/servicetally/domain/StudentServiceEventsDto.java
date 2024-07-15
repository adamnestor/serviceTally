package com.coderscampus.servicetally.domain;

public class StudentServiceEventsDto {

	private int eventId;
	private String serviceTitle;
	private String city;
	private String state;
	private String status;
	private String firstName;
	private String lastName;
	
	
	public StudentServiceEventsDto(int eventId, String serviceTitle, String city, String state, String status,
			String firstName, String lastName) {
		this.eventId = eventId;
		this.serviceTitle = serviceTitle;
		this.city = city;
		this.state = state;
		this.status = status;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public String getServiceTitle() {
		return serviceTitle;
	}
	public void setServiceTitle(String serviceTitle) {
		this.serviceTitle = serviceTitle;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	
	
}
