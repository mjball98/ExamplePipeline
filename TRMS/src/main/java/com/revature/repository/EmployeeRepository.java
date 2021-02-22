package com.revature.repository;

import java.util.List;

import com.revature.model.Employee;

public interface EmployeeRepository {
	
	List<Employee> findAll();
	int findEmployeeIdByUsername(String username);
	void updateUsername(Employee employee, String username);
	void updatePassword(Employee employee, String password);
	void updateFullName(Employee employee, String fullname);
	void insert(Employee e);

}
