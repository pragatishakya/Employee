package com.microservices.employee.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails.Address;
import org.springframework.stereotype.Service;

import com.microservices.employee.entities.Employee;
import com.microservices.employee.payload.EmployeeAddresResponse;
import com.microservices.employee.payload.EmployeeDto;
import com.microservices.employee.repositories.EmployeeRepo;

public interface EmployeeService {
	
	
	 EmployeeDto createEmp(EmployeeDto empDto);
	 EmployeeAddresResponse getEmpById(Integer empId) ;
	 List<EmployeeDto> getAllEmp();
	
}
