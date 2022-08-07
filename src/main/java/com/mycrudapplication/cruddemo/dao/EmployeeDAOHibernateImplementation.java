package com.mycrudapplication.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mycrudapplication.cruddemo.entity.Employee;


@Repository
public class EmployeeDAOHibernateImplementation implements EmployeeDAO {

	//define field for entitymanager
	private EntityManager entityManager;
	
	//setup constructor injection
	@Autowired
	public EmployeeDAOHibernateImplementation(EntityManager theEntityManager)
	{
		entityManager = theEntityManager;
	}
	
	@Override
	public List<Employee> findAll() {

		//get hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		//create query
		Query<Employee> query = currentSession.createQuery("from Employee", Employee.class); 
				
		//execute query and get result list
		List<Employee> employees = query.getResultList();
		
		//return results
		return employees;
	}

	@Override
	public Employee findById(int theID) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		Employee employee = currentSession.get(Employee.class, theID);
		
		return employee;
	}

	@Override
	public void save(Employee theEmp) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		currentSession.saveOrUpdate(theEmp);
		
	}

	@Override
	public void deleteById(int theId) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query theQuery = currentSession.createQuery("delete from Employee where id=:employeeId");
		
		theQuery.setParameter("employeeId", theId);
		
		theQuery.executeUpdate();
		
	}

}
