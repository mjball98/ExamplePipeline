package com.revature.repository;

import java.util.List;

import com.revature.model.Employee;

public interface EmployeeRepository {
	
	List<Employee> findAll();
	void updateUsername(Employee employee, String username);
	void updatePassword(Employee employee, String password);
	void updateFullName(Employee employee, String fullname);

}
