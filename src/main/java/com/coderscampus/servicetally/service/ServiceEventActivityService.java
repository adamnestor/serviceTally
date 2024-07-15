package com.coderscampus.servicetally.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.coderscampus.servicetally.domain.IStudentServiceEvents;
import com.coderscampus.servicetally.domain.ServiceEventActivity;
import com.coderscampus.servicetally.domain.StudentServiceEventsDto;
import com.coderscampus.servicetally.repository.ServiceEventActivityRepository;

@Service
public class ServiceEventActivityService {

	private final ServiceEventActivityRepository serviceEventActivityRepo;

	public ServiceEventActivityService(ServiceEventActivityRepository serviceEventActivityRepo) {
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
			studentServiceEventsDtoList.add(new StudentServiceEventsDto(activity.getEventsId(),
					activity.getServiceTitle(), activity.getDescriptionOfEvent(), activity.getCity(),
					activity.getState(), activity.getDateOfService(), activity.getHoursServed(), activity.getStatus(),
					activity.getPostedById(), activity.getFirstName(), activity.getLastName()));
		}

		return studentServiceEventsDtoList;
	}
}
