package com.interview.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.interview.spring.dao.EmployeeDAO;
import com.interview.spring.model.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	private EmployeeDAO employeeDAO;

	public void setEmployeeDAO(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}

	@Override
	@Transactional
	public void addEmployee(Employee p) {
		this.employeeDAO.addEmployee(p);
	}

	@Override
	@Transactional
	public void updateEmployee(Employee p) {
		this.employeeDAO.updateEmployee(p);
	}

	@Override
	@Transactional
	public List<Employee> listEmployees() {
		return this.employeeDAO.listEmployees();
	}
	
	@Override
	@Transactional
	public List<Employee> searchEmployees(String name) {
		return this.employeeDAO.searchEmployees(name);
	}
	

	@Override
	@Transactional
	public Employee getEmployeeById(int id) {
		return this.employeeDAO.getEmployeeById(id);
	}

	@Override
	@Transactional
	public void removeEmployee(int id) {
		this.employeeDAO.removeEmployee(id);
	}

}
