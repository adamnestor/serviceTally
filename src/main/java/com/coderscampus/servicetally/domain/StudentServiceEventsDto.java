package com.coderscampus.servicetally.domain;

import java.util.Date;

public class StudentServiceEventsDto {

	private int eventId;
	private String serviceTitle;
	private String descriptionOfEvent;
	private String city;
	private String state;
	private Date dateOfService;
	private float hoursServed;
	private String status;
	private int postedById;
	private String firstName;
	private String lastName;
	
	public StudentServiceEventsDto(int eventId, String serviceTitle, String descriptionOfEvent, String city,
			String state, Date dateOfService, float hoursServed, String status, int postedById, String firstName,
			String lastName) {
		this.eventId = eventId;
		this.serviceTitle = serviceTitle;
		this.descriptionOfEvent = descriptionOfEvent;
		this.city = city;
		this.state = state;
		this.dateOfService = dateOfService;
		this.hoursServed = hoursServed;
		this.status = status;
		this.postedById = postedById;
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

	public String getDescriptionOfEvent() {
		return descriptionOfEvent;
	}

	public void setDescriptionOfEvent(String descriptionOfEvent) {
		this.descriptionOfEvent = descriptionOfEvent;
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

	public Date getDateOfService() {
		return dateOfService;
	}

	public void setDateOfService(Date dateOfService) {
		this.dateOfService = dateOfService;
	}

	public float getHoursServed() {
		return hoursServed;
	}

	public void setHoursServed(float hoursServed) {
		this.hoursServed = hoursServed;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getPostedById() {
		return postedById;
	}

	public void setPostedById(int postedById) {
		this.postedById = postedById;
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
