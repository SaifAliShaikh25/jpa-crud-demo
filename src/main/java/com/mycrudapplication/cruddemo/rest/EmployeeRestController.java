package com.mycrudapplication.cruddemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycrudapplication.cruddemo.entity.Employee;
import com.mycrudapplication.cruddemo.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
	
	
	//using DAO
	/*
	 * private EmployeeDAO employeeDAO;
	 * 
	 * //inject employee dao
	 * 
	 * @Autowired public EmployeeRestController(EmployeeDAO theEmployeeDAO) {
	 * employeeDAO = theEmployeeDAO; }
	 */
	
	//using service
	private EmployeeService employeeService;
	
	@Autowired
	public EmployeeRestController(EmployeeService theEmployeeService)
	{
		employeeService = theEmployeeService;
	}
	
	//expose "/employees" that will return all the employees
	@GetMapping("/employees")
	public List<Employee> findAll()
	{
		return employeeService.findAll();
	}
	
	//add mapping for get employee
	@GetMapping("/employees/{employeeId}")
	public Employee getEmployee(@PathVariable int employeeId)
	{
		Employee theEmployee = employeeService.findById(employeeId);
		if(theEmployee == null)
			throw new RuntimeException("Employee Id not found - "+employeeId);
		return theEmployee;
	}
	
	//add mapping to post employee
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee theEmployee)
	{
		theEmployee.setId(0);
		employeeService.save(theEmployee);
		return theEmployee;
	}
	
	//update employee so put mapping
	@PutMapping("/employees")
	public Employee  updateEmployee(@RequestBody Employee employee)
	{
		employeeService.save(employee);		
		return employee;
	}
	
	//delete employee so put deletemapping
	@DeleteMapping("/employees/{employeeId}")
	public String deleteEmployee(@PathVariable int employeeId)
	{
		Employee employee = employeeService.findById(employeeId);
		if(employee == null)
			throw new RuntimeException("Employee with id "+employeeId+" not found");
		employeeService.deleteById(employeeId);
		return "Employee with id "+employeeId+" deleted";
	}

}
