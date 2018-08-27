package com.interview.spring.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.interview.spring.dao.EmployeeDAO;
import com.interview.spring.exception.InvalidEmployeeException;
import com.interview.spring.model.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	
	private EmployeeDAO employeeDAO;

	public void setEmployeeDAO(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}

	@Override
	@Transactional(readOnly = false)
	public void addEmployee(Employee p) throws InvalidEmployeeException {
		logger.info("test");
		if (StringUtils.isEmpty(p.getFirstName())) {
			logger.info("empty");
            throw new InvalidEmployeeException(
                    "Employee first name cannot be empty: "
                            + p.getFirstName());
        }
		this.employeeDAO.addEmployee(p);
	}

	@Override
	@Transactional(readOnly = false)
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
