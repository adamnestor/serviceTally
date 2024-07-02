package com.coderscampus.servicetally.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.coderscampus.servicetally.domain.AdminProfile;
import com.coderscampus.servicetally.domain.School;
import com.coderscampus.servicetally.domain.StudentProfile;

@DataJpaTest
public class RepositoryTests {

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private AdminProfileRepository adminProfileRepository;

    @Autowired
    private StudentProfileRepository studentProfileRepository;

    @Test
    public void testCreateAndRetrieveEntities() {
        School school = new School();
        school.setSchoolName("Test High");
        school.setCity("Test City");
        school.setState("TS");
        school = schoolRepository.save(school);

        AdminProfile admin = new AdminProfile();
        admin.setFirstName("Alice");
        admin.setLastName("Admin");
        admin.setCity("Test City");
        admin.setState("TS");
        admin.setProfilePhoto("photo.jpg");
        admin.setSchoolId(school);
        admin = adminProfileRepository.save(admin);

        StudentProfile student = new StudentProfile();
        student.setFirstName("Bob");
        student.setLastName("Student");
        student.setCity("Test City");
        student.setState("TS");
        student.setGraduationYear("2023");
        student.setSchoolId(school);
        student = studentProfileRepository.save(student);

        Optional<School> foundSchool = schoolRepository.findById(school.getSchoolId());
        assertNotNull(foundSchool);
        assertEquals("Test High", foundSchool.get().getSchoolName());

        Optional<AdminProfile> foundAdmin = adminProfileRepository.findById(admin.getAdminId());
        assertNotNull(foundAdmin);
        assertEquals("Alice", foundAdmin.get().getFirstName());
        assertEquals(school.getSchoolId(), foundAdmin.get().getSchoolId().getSchoolId());

        Optional<StudentProfile> foundStudent = studentProfileRepository.findById(student.getStudentId());
        assertNotNull(foundStudent);
        assertEquals("Bob", foundStudent.get().getFirstName());
        assertEquals(school.getSchoolId(), foundStudent.get().getSchoolId().getSchoolId());
    }
}
