package com.coderscampus.servicetally.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.servicetally.domain.IStudentServiceEvents;
import com.coderscampus.servicetally.domain.ServiceEventActivity;
import com.coderscampus.servicetally.domain.StudentServiceEventsDto;
import com.coderscampus.servicetally.repository.ServiceEventActivityRepository;
import com.coderscampus.servicetally.repository.StudentProfileRepository;

@Service
public class ServiceEventActivityService {

	private final ServiceEventActivityRepository serviceEventActivityRepo;

	@Autowired
	public ServiceEventActivityService(ServiceEventActivityRepository serviceEventActivityRepo,
			StudentProfileRepository studentProfileRepo) {
		this.serviceEventActivityRepo = serviceEventActivityRepo;
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

	public List<StudentServiceEventsDto> getAllServiceEventsFiltered(Integer studentId, Integer schoolId,
			String status) {
		List<IStudentServiceEvents> serviceEvents = new ArrayList<>();

		if (studentId != null && schoolId != null && status != null) {
			serviceEvents = serviceEventActivityRepo.findServiceEventsByStudentIdAndSchoolIdAndStatus(studentId,
					schoolId, status);
		} else if (studentId != null && schoolId != null) {
			serviceEvents = serviceEventActivityRepo.findServiceEventsByStudentIdAndSchoolId(studentId, schoolId);
		} else if (studentId != null && status != null) {
			serviceEvents = serviceEventActivityRepo.findServiceEventsByStudentIdAndStatus(studentId, status);
		} else if (schoolId != null && status != null) {
			serviceEvents = serviceEventActivityRepo.findServiceEventsBySchoolIdAndStatus(schoolId, status);
		} else if (studentId != null) {
			serviceEvents = serviceEventActivityRepo.findServiceEventsByStudentId(studentId);
		} else if (schoolId != null) {
			serviceEvents = serviceEventActivityRepo.findServiceEventsBySchoolId(schoolId);
		} else if (status != null) {
			serviceEvents = serviceEventActivityRepo.findServiceEventsByStatus(status);
		}

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

}
