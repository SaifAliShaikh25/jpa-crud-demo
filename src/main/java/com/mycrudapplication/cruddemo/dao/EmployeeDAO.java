package com.mycrudapplication.cruddemo.dao;

import java.util.List;

import com.mycrudapplication.cruddemo.entity.Employee;

public interface EmployeeDAO {
	public List<Employee> findAll();
	
	public Employee findById(int theID);
	
	public void save(Employee theEmp);
	
	public void deleteById(int theId);
}
