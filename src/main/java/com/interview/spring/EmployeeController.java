package com.interview.spring;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.interview.spring.exception.InvalidEmployeeException;
import com.interview.spring.model.Employee;
import com.interview.spring.service.EmployeeService;
import com.interview.spring.service.EmployeeServiceImpl;

@Controller
public class EmployeeController {
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	
	private EmployeeService employeeService;
	
	@Autowired(required=true)
	@Qualifier(value="employeeService")
	public void setEmployeeService(EmployeeService ps){
		this.employeeService = ps;
	}
	
	@RequestMapping(value = "/employees", method = RequestMethod.GET)
	public String listEmployees(Model model) {
		Map employeeHash = new HashMap<Integer,Employee>();
		Set employeeSet = new HashSet<Employee>();
		List<Employee> employees = this.employeeService.listEmployees();
		model.addAttribute("employee", new Employee());
		model.addAttribute("listEmployees", employees);
		for(Employee e :employees ){
			employeeSet.add(e);
			employeeHash.put(e.getId(),e);
		}
		model.addAttribute("employeeSet", employeeSet);
		model.addAttribute("employeeHash", employeeHash);
		return "employee";
	}
	
	
	@RequestMapping(value = "/employeeSearch", method = RequestMethod.GET)
	public String searchEmployees(Model model,
			@RequestParam String name ){
		model.addAttribute("employee", new Employee());
		model.addAttribute("listEmployees", this.employeeService.searchEmployees(name));
		return "employee";
	}
	
	//For add and update employee both
	@RequestMapping(value= "/employee/add", method = RequestMethod.POST)
	public String addEmployee(@ModelAttribute("employee") Employee p,Model model) {
		logger.info("addEmployee");
		try{
		if(p.getId() == 0){
			//new employee, add it
			this.employeeService.addEmployee(p);
		}else{
			//existing employee, call update
			this.employeeService.updateEmployee(p);
		}
		}catch(Exception e){
			logger.info("rollbackMsg");
			model.addAttribute("rollbackMsg", "Transaction hasbeen rolled back due to Empty name  :::::::::::::::::::           " + e);
			return "rollback";
		}
		logger.info("redirect");
		return "redirect:/employees";
		
	}
	
	@RequestMapping("/remove/{id}")
    public String removeEmployee(@PathVariable("id") int id){
		
        this.employeeService.removeEmployee(id);
        return "redirect:/employees";
    }
 
    @RequestMapping("/edit/{id}")
    public String editEmployee(@PathVariable("id") int id, Model model){
        model.addAttribute("employee", this.employeeService.getEmployeeById(id));
        model.addAttribute("listEmployees", this.employeeService.listEmployees());
        return "employee";
    }
	
}
