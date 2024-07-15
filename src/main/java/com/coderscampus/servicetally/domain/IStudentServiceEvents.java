package com.coderscampus.servicetally.domain;

import java.util.Date;

public interface IStudentServiceEvents {

	int getEventsId();
	String getServiceTitle();
	String getDescriptionOfEvent();
	String getCity();
	String getState();
	Date getDateOfService();
	float getHoursServed();
	String getStatus();
	int getPostedById();
	String getFirstName();
	String getLastName();
}
