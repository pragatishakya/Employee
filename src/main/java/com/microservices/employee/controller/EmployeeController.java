package com.microservices.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails.Address;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.employee.payload.EmployeeAddresResponse;
import com.microservices.employee.payload.EmployeeDto;
import com.microservices.employee.services.EmployeeService;

import jakarta.validation.Valid;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService empService;
	
	
	@PostMapping("/addUser")
	public ResponseEntity<EmployeeDto> createEmp(@Valid @RequestBody EmployeeDto empDto){
		EmployeeDto createdEmpDto = this.empService.createEmp(empDto);
		return new ResponseEntity<>(createdEmpDto , HttpStatus.CREATED);
	}
	
	
	@GetMapping("/address/{empId}")
	public ResponseEntity<EmployeeAddresResponse> getEmpById(@PathVariable Integer empId){
		return ResponseEntity.ok(this.empService.getEmpById(empId));
	}
	
	@GetMapping("/")
	public ResponseEntity<List<EmployeeDto>> getAllEmp(){
		return ResponseEntity.ok(this.empService.getAllEmp());
	}
	

	
}
