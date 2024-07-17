package com.coderscampus.servicetally.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.coderscampus.servicetally.domain.IStudentServiceEvents;
import com.coderscampus.servicetally.domain.ServiceEventActivity;

public interface ServiceEventActivityRepository extends JpaRepository<ServiceEventActivity, Integer> {

	@Query(value = "SELECT sea.event_id AS eventId, sea.service_title AS serviceTitle, sea.city AS city, sea.state AS state, sea.status AS status, sp.first_name AS firstName, sp.last_name AS lastName "
			+ "FROM service_event_activity sea " + "JOIN users u ON sea.posted_by_id = u.user_id "
			+ "JOIN student_profile sp ON u.user_id = sp.user_account_id" + " WHERE sea.posted_by_id = :student "
			+ " GROUP BY sea.event_id", nativeQuery = true)
	List<IStudentServiceEvents> getStudentServiceEvents(@Param("student") int student);

	@Query(value = "SELECT sea.event_id AS eventId, sea.service_title AS serviceTitle, sea.city AS city, sea.state AS state, sea.status AS status, sp.first_name AS firstName, sp.last_name AS lastName "
			+ "FROM service_event_activity sea " + "JOIN users u ON sea.posted_by_id = u.user_id "
			+ "JOIN student_profile sp ON u.user_id = sp.user_account_id "
			+ "JOIN school s ON sp.school_id = s.school_id " + "WHERE s.school_id IN :schoolIds "
			+ "GROUP BY sea.event_id", nativeQuery = true)
	List<IStudentServiceEvents> findServiceEventsBySchoolIds(@Param("schoolIds") List<Integer> schoolIds);

	////////////////////////////////////////////////////////////
	// Methods to filter service events on the admin dashboard
	////////////////////////////////////////////////////////////

	@Query(value = "SELECT sea.event_id AS eventId, sea.service_title AS serviceTitle, sea.city AS city, sea.state AS state, sea.status AS status, sp.first_name AS firstName, sp.last_name AS lastName "
			+ "FROM service_event_activity sea " + "JOIN users u ON sea.posted_by_id = u.user_id "
			+ "JOIN student_profile sp ON u.user_id = sp.user_account_id "
			+ "WHERE sea.posted_by_id = :studentId AND sp.school_id = :schoolId AND sea.status = :status "
			+ "GROUP BY sea.event_id", nativeQuery = true)
	List<IStudentServiceEvents> findServiceEventsByStudentIdAndSchoolIdAndStatus(@Param("studentId") Integer studentId,
			@Param("schoolId") Integer schoolId, @Param("status") String status);

	@Query(value = "SELECT sea.event_id AS eventId, sea.service_title AS serviceTitle, sea.city AS city, sea.state AS state, sea.status AS status, sp.first_name AS firstName, sp.last_name AS lastName "
			+ "FROM service_event_activity sea " + "JOIN users u ON sea.posted_by_id = u.user_id "
			+ "JOIN student_profile sp ON u.user_id = sp.user_account_id "
			+ "WHERE sea.posted_by_id = :studentId AND sp.school_id = :schoolId "
			+ "GROUP BY sea.event_id", nativeQuery = true)
	List<IStudentServiceEvents> findServiceEventsByStudentIdAndSchoolId(@Param("studentId") Integer studentId,
			@Param("schoolId") Integer schoolId);

	@Query(value = "SELECT sea.event_id AS eventId, sea.service_title AS serviceTitle, sea.city AS city, sea.state AS state, sea.status AS status, sp.first_name AS firstName, sp.last_name AS lastName "
			+ "FROM service_event_activity sea " + "JOIN users u ON sea.posted_by_id = u.user_id "
			+ "JOIN student_profile sp ON u.user_id = sp.user_account_id "
			+ "WHERE sea.posted_by_id = :studentId AND sea.status = :status "
			+ "GROUP BY sea.event_id", nativeQuery = true)
	List<IStudentServiceEvents> findServiceEventsByStudentIdAndStatus(@Param("studentId") Integer studentId,
			@Param("status") String status);

	@Query(value = "SELECT sea.event_id AS eventId, sea.service_title AS serviceTitle, sea.city AS city, sea.state AS state, sea.status AS status, sp.first_name AS firstName, sp.last_name AS lastName "
			+ "FROM service_event_activity sea " + "JOIN users u ON sea.posted_by_id = u.user_id "
			+ "JOIN student_profile sp ON u.user_id = sp.user_account_id "
			+ "JOIN school s ON sp.school_id = s.school_id "
			+ "WHERE sp.school_id = :schoolId AND sea.status = :status " + "GROUP BY sea.event_id", nativeQuery = true)
	List<IStudentServiceEvents> findServiceEventsBySchoolIdAndStatus(@Param("schoolId") Integer schoolId,
			@Param("status") String status);

	@Query(value = "SELECT sea.event_id AS eventId, sea.service_title AS serviceTitle, sea.city AS city, sea.state AS state, sea.status AS status, sp.first_name AS firstName, sp.last_name AS lastName "
			+ "FROM service_event_activity sea " + "JOIN users u ON sea.posted_by_id = u.user_id "
			+ "JOIN student_profile sp ON u.user_id = sp.user_account_id " + "WHERE sea.posted_by_id = :studentId "
			+ "GROUP BY sea.event_id", nativeQuery = true)
	List<IStudentServiceEvents> findServiceEventsByStudentId(@Param("studentId") Integer studentId);

	@Query(value = "SELECT sea.event_id AS eventId, sea.service_title AS serviceTitle, sea.city AS city, sea.state AS state, sea.status AS status, sp.first_name AS firstName, sp.last_name AS lastName "
			+ "FROM service_event_activity sea " + "JOIN users u ON sea.posted_by_id = u.user_id "
			+ "JOIN student_profile sp ON u.user_id = sp.user_account_id "
			+ "JOIN school s ON sp.school_id = s.school_id " + "WHERE sp.school_id = :schoolId "
			+ "GROUP BY sea.event_id", nativeQuery = true)
	List<IStudentServiceEvents> findServiceEventsBySchoolId(@Param("schoolId") Integer schoolId);

	@Query(value = "SELECT sea.event_id AS eventId, sea.service_title AS serviceTitle, sea.city AS city, sea.state AS state, sea.status AS status, sp.first_name AS firstName, sp.last_name AS lastName "
			+ "FROM service_event_activity sea " + "JOIN users u ON sea.posted_by_id = u.user_id "
			+ "JOIN student_profile sp ON u.user_id = sp.user_account_id " + "WHERE sea.status = :status "
			+ "GROUP BY sea.event_id", nativeQuery = true)
	List<IStudentServiceEvents> findServiceEventsByStatus(@Param("status") String status);

	@Query(value = "SELECT sea.event_id AS eventId, sea.service_title AS serviceTitle, sea.city AS city, sea.state AS state, sea.status AS status, sp.first_name AS firstName, sp.last_name AS lastName "
			+ "FROM service_event_activity sea " + "JOIN users u ON sea.posted_by_id = u.user_id "
			+ "JOIN student_profile sp ON u.user_id = sp.user_account_id "
			+ "GROUP BY sea.event_id", nativeQuery = true)
	List<ServiceEventActivity> findAll();
}
