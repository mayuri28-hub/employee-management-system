package com.ems.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ems.entity.Employee;
import com.ems.exception.EmployeeNotFoundException;
import com.ems.repository.EmployeeRepository;


@Service
public class EmployeeService {
	
	private final EmployeeRepository employeeRepository;

	
	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	//Create / update employee
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	// Get all employees
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}
	
	// Get employee by Id
	public Employee getEmployeeById(Long id) {

	    return employeeRepository.findById(id)
	            .orElseThrow(() ->
	                new EmployeeNotFoundException(
	                    "Employee not found with id: " + id
	                )
	            );
	}
	
	// Delete employee
	public void deleteEmployee(Long id) {
		if (!employeeRepository.existsById(id)) {
			throw new EmployeeNotFoundException(
					"Employee not found with id: " + id
					);
		}
		
		employeeRepository.deleteById(id);
	}
}
