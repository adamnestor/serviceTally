package com.coderscampus.servicetally.repository;

import com.coderscampus.servicetally.domain.AdminProfile;
import com.coderscampus.servicetally.domain.School;
import com.coderscampus.servicetally.domain.StudentProfile;
import com.coderscampus.servicetally.domain.Users;
import com.coderscampus.servicetally.repository.AdminProfileRepository;
import com.coderscampus.servicetally.repository.SchoolRepository;
import com.coderscampus.servicetally.repository.StudentProfileRepository;
import com.coderscampus.servicetally.repository.UsersRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class EntityTest {

    @Autowired
    private AdminProfileRepository adminProfileRepository;

    @Autowired
    private StudentProfileRepository studentProfileRepository;

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Test
    @Transactional
    public void testCreateAndRetrieveEntities() {
        // Create User
        Users user = new Users();
        user.setEmail("adminUser@email.com");
        user.setPassword("password");
        usersRepository.save(user);

        // Create School
        School school = new School();
        school.setSchoolName("Test School");
        school.setCity("Test City");
        school.setState("Test State");
        schoolRepository.save(school);

        // Create AdminProfile
        AdminProfile adminProfile = new AdminProfile();
        adminProfile.setUserId(user);
        adminProfile.setFirstName("Admin");
        adminProfile.setLastName("User");
        adminProfile.setCity("Admin City");
        adminProfile.setState("Admin State");
        adminProfile.setSchoolId(school);
        adminProfileRepository.save(adminProfile);

        // Create StudentProfile
        StudentProfile studentProfile = new StudentProfile();
        studentProfile.setUserId(user);
        studentProfile.setFirstName("Student");
        studentProfile.setLastName("User");
        studentProfile.setCity("Student City");
        studentProfile.setState("Student State");
        studentProfile.setGraduationYear("2024");
        studentProfile.setSchoolId(school);
        studentProfileRepository.save(studentProfile);

        // Retrieve and assert AdminProfile
        Optional<AdminProfile> retrievedAdminProfile = adminProfileRepository.findById(adminProfile.getUserAccountId());
        assertThat(retrievedAdminProfile).isPresent();
        assertThat(retrievedAdminProfile.get().getFirstName()).isEqualTo("Admin");

        // Retrieve and assert StudentProfile
        Optional<StudentProfile> retrievedStudentProfile = studentProfileRepository.findById(studentProfile.getUserAccountId());
        assertThat(retrievedStudentProfile).isPresent();
        assertThat(retrievedStudentProfile.get().getFirstName()).isEqualTo("Student");

        // Retrieve and assert School
        Optional<School> retrievedSchool = schoolRepository.findById(school.getSchoolId());
        assertThat(retrievedSchool).isPresent();
        assertThat(retrievedSchool.get().getSchoolName()).isEqualTo("Test School");
    }
}

