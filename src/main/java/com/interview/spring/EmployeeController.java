package com.interview.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.interview.spring.model.Employee;
import com.interview.spring.service.EmployeeService;

@Controller
public class EmployeeController {
	
	private EmployeeService employeeService;
	
	@Autowired(required=true)
	@Qualifier(value="employeeService")
	public void setEmployeeService(EmployeeService ps){
		this.employeeService = ps;
	}
	
	@RequestMapping(value = "/employees", method = RequestMethod.GET)
	public String listEmployees(Model model) {
		model.addAttribute("employee", new Employee());
		model.addAttribute("listEmployees", this.employeeService.listEmployees());
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
	public String addEmployee(@ModelAttribute("employee") Employee p){
		
		if(p.getId() == 0){
			//new employee, add it
			this.employeeService.addEmployee(p);
		}else{
			//existing employee, call update
			this.employeeService.updateEmployee(p);
		}
		
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
