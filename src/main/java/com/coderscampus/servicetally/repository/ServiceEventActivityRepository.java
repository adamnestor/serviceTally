package com.coderscampus.servicetally.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.coderscampus.servicetally.domain.IStudentServiceEvents;
import com.coderscampus.servicetally.domain.ServiceEventActivity;

public interface ServiceEventActivityRepository extends JpaRepository<ServiceEventActivity, Integer>{

	@Query(value = "SELECT sea.event_id AS eventId, sea.service_title AS serviceTitle, sea.description_of_event AS descriptionOfEvent, " +
            "sea.city AS city, sea.state AS state, sea.date_of_service AS dateOfService, sea.hours_served AS hoursServed, " +
            "sea.status AS status, sea.posted_by_id AS postedById, sp.first_name AS firstName, sp.last_name AS lastName " +
            "FROM service_event_activity sea " +
            "JOIN user u ON sea.posted_by_id = u.user_id " +
            "JOIN student_profile sp ON u.user_account_id = sp.user_account_id " +
            "WHERE sea.posted_by_id = :student " +
            "GROUP BY sea.posted_by_id", nativeQuery = true)
	List<IStudentServiceEvents> getStudentServiceEvents(@Param("student") int student);
	
}
