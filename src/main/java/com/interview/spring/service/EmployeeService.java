package com.interview.spring.service;

import java.util.List;

import com.interview.spring.model.Employee;

public interface EmployeeService {

	public void addEmployee(Employee p);
	public void updateEmployee(Employee p);
	public List<Employee> listEmployees();
	public List<Employee> searchEmployees(String name);
	public Employee getEmployeeById(int id);
	public void removeEmployee(int id);
	
}
