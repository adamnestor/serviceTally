package com.coderscampus.servicetally.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.coderscampus.servicetally.domain.GraduationYearDto;
import com.coderscampus.servicetally.domain.School;
import com.coderscampus.servicetally.domain.StudentProfile;

public interface StudentProfileRepository extends JpaRepository<StudentProfile, Integer> {

	List<StudentProfile> findBySchoolInOrderByLastName(List<School> schools);

	@Query("SELECT sp FROM StudentProfile sp WHERE sp.school.id = :schoolId AND sp.graduationYear = :graduationYear")
	List<StudentProfile> findBySchoolIdAndGraduationYear(@Param("schoolId") Integer schoolId, @Param("graduationYear") String graduationYear);

	@Query("SELECT DISTINCT sp.graduationYear FROM StudentProfile sp")
	List<String> findDistinctGraduationYears();

	@Query("SELECT sp FROM StudentProfile sp WHERE sp.school.id IN :schoolIds")
	List<StudentProfile> findAllBySchoolIds(List<Integer> schoolIds);
}
