package com.microservices.employee.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails.Address;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.microservices.employee.entities.Employee;
import com.microservices.employee.payload.EmployeeDto;
import com.microservices.employee.repositories.EmployeeRepo;
import com.microservices.employee.payload.AddressResponse;
import com.microservices.employee.payload.EmployeeAddresResponse;
import com.microservices.employee.services.EmployeeService;

import reactor.core.publisher.Mono;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepo empRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private WebClient webClient;
	
	
	@Override
	public EmployeeDto createEmp(EmployeeDto empDto) {
		Employee emp=this.dtoToEmp(empDto);
		Employee savedEmp=this.empRepo.save(emp);
		return this.empToDto(savedEmp);
	}

	@Override
	public EmployeeAddresResponse getEmpById(Integer empId) {
		Employee emp = this.empRepo.findById(empId).orElseThrow();
		EmployeeDto employeeDto= this.empToDto(emp);
		
		AddressResponse addressResponse = webClient.get()
				.uri("http://localhost:9292/address/" + empId)
				.retrieve()
				.bodyToMono(AddressResponse.class)
				.block();
		
		EmployeeAddresResponse combinedResponse = new EmployeeAddresResponse();
		combinedResponse.setEmployeeDto(employeeDto);
		combinedResponse.setAddressResponse(addressResponse);
		
		return combinedResponse  ;
	}

	@Override
	public List<EmployeeDto> getAllEmp() {
		List<Employee> emp=this.empRepo.findAll();
		List<EmployeeDto> empDto= emp.stream().map(e-> this.empToDto(e)).collect(Collectors.toList());
		return empDto;
	}
	
	public EmployeeDto empToDto(Employee emp) {
		EmployeeDto empDto = this.modelMapper.map(emp, EmployeeDto.class);
		return empDto;
	}
	
	public Employee dtoToEmp(EmployeeDto empDto) {
		Employee emp= this.modelMapper.map(empDto , Employee.class) ;
		return emp;
	}
	
	
	

}
