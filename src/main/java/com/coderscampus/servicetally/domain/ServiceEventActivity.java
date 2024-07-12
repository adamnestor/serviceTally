package com.coderscampus.servicetally.domain;

import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "service_event_activity")
public class ServiceEventActivity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int eventId;

	@ManyToOne
	@JoinColumn(name = "postedById", referencedColumnName = "userId")
	private Users postedById;

	private String serviceTitle;

	@Length(max = 10000)
	private String descriptionOfEvent;

	private String city;
	private String state;

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date postedDate;

	private Float hoursServed;

	@Enumerated(EnumType.STRING)
	private ServiceEventStatus status;

	public ServiceEventActivity() {
	}

	public ServiceEventActivity(int eventId, Users postedById, String serviceTitle,
			@Length(max = 10000) String descriptionOfEvent, String city, String state, Date postedDate,
			Float hoursServed, ServiceEventStatus status) {
		this.eventId = eventId;
		this.postedById = postedById;
		this.serviceTitle = serviceTitle;
		this.descriptionOfEvent = descriptionOfEvent;
		this.city = city;
		this.state = state;
		this.postedDate = postedDate;
		this.hoursServed = hoursServed;
		this.status = status;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public Users getPostedById() {
		return postedById;
	}

	public void setPostedById(Users postedById) {
		this.postedById = postedById;
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

	public Date getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}

	public Float getHoursServed() {
		return hoursServed;
	}

	public void setHoursServed(Float hoursServed) {
		this.hoursServed = hoursServed;
	}

	public ServiceEventStatus getStatus() {
		return status;
	}

	public void setStatus(ServiceEventStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ServiceEventActivity [eventId=" + eventId + ", postedById=" + postedById + ", serviceTitle="
				+ serviceTitle + ", descriptionOfEvent=" + descriptionOfEvent + ", city=" + city + ", state=" + state
				+ ", postedDate=" + postedDate + ", hoursServed=" + hoursServed + ", status=" + status + "]";
	}

}
