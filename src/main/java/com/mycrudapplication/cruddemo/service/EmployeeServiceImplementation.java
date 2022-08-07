package com.mycrudapplication.cruddemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycrudapplication.cruddemo.dao.EmployeeDAO;
import com.mycrudapplication.cruddemo.entity.Employee;

@Service
public class EmployeeServiceImplementation implements EmployeeService {
	
	private EmployeeDAO employeedao;
	
	@Autowired
	public EmployeeServiceImplementation(@Qualifier("employeeDAOJPAImplementation") EmployeeDAO theEmployeedao)
	{
		employeedao = theEmployeedao;
	}

	@Override
	@Transactional
	public List<Employee> findAll() {
		
		return employeedao.findAll();
	}

	@Override
	@Transactional
	public Employee findById(int theId) {
		
		return employeedao.findById(theId);
	}

	@Override
	@Transactional
	public void save(Employee theEmployee) {
		
		employeedao.save(theEmployee);
	}

	@Override
	@Transactional
	public void deleteById(int theId) {
		
		employeedao.deleteById(theId);
	}

}
