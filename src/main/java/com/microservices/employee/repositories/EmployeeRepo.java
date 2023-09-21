package com.microservices.employee.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.microservices.employee.entities.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer>{

}
