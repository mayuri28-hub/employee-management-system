package com.ems.controller;

import java.util.List;

import jakarta.validation.Valid;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ems.entity.Employee;
import com.ems.service.EmployeeService;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/employees")
public class EmployeeController {
	
	private final EmployeeService employeeService;
	
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	// Get all employees
	@GetMapping
	public List<Employee> getAllEmployees() {
		return employeeService.getAllEmployees();
	}
	
	//Get employee by Id
	@GetMapping("/{id}")
	public Employee getEmployeeById(@PathVariable Long id) {
		return employeeService.getEmployeeById(id);
	}
	
	// Create employee
	@PostMapping
	public Employee createEmployee(@Valid @RequestBody Employee employee) {
	    return employeeService.saveEmployee(employee);
	}
	
	//Update employee
	@PutMapping("/{id}")
	public Employee updateEmployee(@PathVariable Long id,
			                       @Valid @RequestBody Employee employee) {
		
		Employee existingEmployee = employeeService.getEmployeeById(id);
		
		
		
		
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setEmail(employee.getEmail());
		existingEmployee.setDepartment(employee.getDepartment());
		existingEmployee.setDesignation(employee.getDesignation());
		existingEmployee.setSalary(employee.getSalary());
		
		return employeeService.saveEmployee(existingEmployee);
	}
	
	
	//Delete employee
	@DeleteMapping("/{id}")
	public String deleteEmployee(@PathVariable Long id) {
		
		employeeService.deleteEmployee(id);
		
		return "Employee deleted successfully";
		
			
	}
	
	
}




