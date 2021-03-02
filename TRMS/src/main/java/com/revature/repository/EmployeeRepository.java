package com.revature.repository;

import java.util.List;

import com.revature.model.Employee;

public interface EmployeeRepository {
	
	List<Employee> findAll();
	void insert(Employee e);
	void update(Employee e1);

}
