package com.coderscampus.servicetally.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.coderscampus.servicetally.domain.IStudentServiceEvents;
import com.coderscampus.servicetally.domain.ServiceEventActivity;
import com.coderscampus.servicetally.domain.StudentProfile;
import com.coderscampus.servicetally.domain.StudentServiceEventsDto;
import com.coderscampus.servicetally.repository.ServiceEventActivityRepository;
import com.coderscampus.servicetally.repository.StudentProfileRepository;

@Service
public class ServiceEventActivityService {

	private final ServiceEventActivityRepository serviceEventActivityRepo;
	private final StudentProfileRepository studentProfileRepo;

	public ServiceEventActivityService(ServiceEventActivityRepository serviceEventActivityRepo,
			StudentProfileRepository studentProfileRepo) {
		this.serviceEventActivityRepo = serviceEventActivityRepo;
		this.studentProfileRepo = studentProfileRepo;
	}

	public ServiceEventActivity addNew(ServiceEventActivity serviceEventActivity) {
		return serviceEventActivityRepo.save(serviceEventActivity);
	}

	public List<StudentServiceEventsDto> getStudentServiceEvents(int student) {

		List<IStudentServiceEvents> studentServiceEventsDtos = serviceEventActivityRepo
				.getStudentServiceEvents(student);

		List<StudentServiceEventsDto> studentServiceEventsDtoList = new ArrayList<>();

		for (IStudentServiceEvents activity : studentServiceEventsDtos) {
			studentServiceEventsDtoList.add(new StudentServiceEventsDto(activity.getEventId(),
					activity.getServiceTitle(), activity.getCity(), activity.getState(), activity.getStatus(),
					activity.getFirstName(), activity.getLastName()));
		}

		return studentServiceEventsDtoList;
	}

	public List<StudentServiceEventsDto> getAllServiceEventsForSchools(List<Integer> schoolIds) {
		List<IStudentServiceEvents> serviceEvents = serviceEventActivityRepo.findServiceEventsBySchoolIds(schoolIds);
		List<StudentServiceEventsDto> serviceEventsDtoList = new ArrayList<>();

		for (IStudentServiceEvents activity : serviceEvents) {
			serviceEventsDtoList.add(new StudentServiceEventsDto(activity.getEventId(), activity.getServiceTitle(),
					activity.getCity(), activity.getState(), activity.getStatus(), activity.getFirstName(),
					activity.getLastName()));
		}
		return serviceEventsDtoList;
	}

	public ServiceEventActivity getOne(int id) {

		return serviceEventActivityRepo.findById(id).orElseThrow(() -> new RuntimeException("Service Event not found"));
	}

	public void deleteServiceEvent(Integer id) {
		serviceEventActivityRepo.deleteById(id);
	}

	public List<StudentServiceEventsDto> getAllServiceEventsForStudentIdAndSchoolId(Integer studentId,
			Integer schoolId) {
		List<IStudentServiceEvents> serviceEvents = serviceEventActivityRepo
				.findServiceEventsByStudentIdAndSchoolId(studentId, schoolId);
		List<StudentServiceEventsDto> serviceEventsDtoList = new ArrayList<>();

		for (IStudentServiceEvents activity : serviceEvents) {
			serviceEventsDtoList.add(new StudentServiceEventsDto(activity.getEventId(), activity.getServiceTitle(),
					activity.getCity(), activity.getState(), activity.getStatus(), activity.getFirstName(),
					activity.getLastName()));
		}
		return serviceEventsDtoList;
	}

	public List<StudentServiceEventsDto> getAllServiceEventForSchoolId(Integer schoolId) {
		List<IStudentServiceEvents> serviceEvents = serviceEventActivityRepo.findServiceEventsBySchoolId(schoolId);
		List<StudentServiceEventsDto> serviceEventsDtoList = new ArrayList<>();

		for (IStudentServiceEvents activity : serviceEvents) {
			serviceEventsDtoList.add(new StudentServiceEventsDto(activity.getEventId(), activity.getServiceTitle(),
					activity.getCity(), activity.getState(), activity.getStatus(), activity.getFirstName(),
					activity.getLastName()));
		}
		return serviceEventsDtoList;
	}
	
	public List<StudentServiceEventsDto> getAllServiceEventsForStudentId(Integer userAccountId) {

		List<IStudentServiceEvents> serviceEvents = serviceEventActivityRepo.findServiceEventsByPostedById(userAccountId);
		List<StudentServiceEventsDto> serviceEventsDtoList = new ArrayList<>();

		for (IStudentServiceEvents activity : serviceEvents) {
			serviceEventsDtoList.add(new StudentServiceEventsDto(activity.getEventId(), activity.getServiceTitle(),
					activity.getCity(), activity.getState(), activity.getStatus(), activity.getFirstName(),
					activity.getLastName()));
		}
		return serviceEventsDtoList;
	}

}
