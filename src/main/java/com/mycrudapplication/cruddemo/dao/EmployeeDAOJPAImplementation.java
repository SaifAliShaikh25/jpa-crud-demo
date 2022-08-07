package com.mycrudapplication.cruddemo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mycrudapplication.cruddemo.entity.Employee;

@Repository
public class EmployeeDAOJPAImplementation implements EmployeeDAO {
	
	private EntityManager entityManager;
	
	@Autowired
	public EmployeeDAOJPAImplementation(EntityManager theEntityManager)
	{
		entityManager = theEntityManager;
	}

	@Override
	public List<Employee> findAll() {
		
		List<Employee> employees = new ArrayList<>();
		Query query = entityManager.createQuery("from Employee");
		employees = query.getResultList();
		return employees;
	}

	@Override
	public Employee findById(int theID) {
		
		Employee emp = entityManager.find(Employee.class, theID);
		if(emp == null)
			throw new RuntimeException("Employee with "+theID+" not found in JPA");
		return emp;
	}

	@Override
	public void save(Employee theEmp) {
		
		//using persist
		//entityManager.persist(theEmp);
		
		//using merge
		Employee emp = entityManager.merge(theEmp);
		theEmp.setId(emp.getId());

	}

	@Override
	public void deleteById(int theId) {
		Query query = entityManager.createQuery("delete from Employee where id=:employeeId");
		query.setParameter("employeeId", theId);
		int i = query.executeUpdate();
		if(i==0)
			throw new RuntimeException("Employee with id "+theId+" not able to delete in JPA");
		

	}

}
