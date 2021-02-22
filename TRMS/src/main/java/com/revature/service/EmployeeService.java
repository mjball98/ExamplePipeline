package com.revature.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.model.Employee;
import com.revature.repository.EmployeeRepository;
import com.revature.repository.EmployeeRepositoryImpl;

public class EmployeeService {

	private static final Logger LOG = LogManager.getFormatterLogger(EmployeeService.class);
	ManagerService managerService = new ManagerService();

	private EmployeeRepository employeeRepository;

	public EmployeeService() {
		this.employeeRepository = new EmployeeRepositoryImpl();
	}

	public List<Employee> findAll() {
		return this.employeeRepository.findAll();
	}

	public Map<String, String> findAllEmployeeManagers() {
		List<Employee> employees = new ArrayList<>();
		employees = this.employeeRepository.findAll();
		Map<String, String> returnMap = new HashMap<String, String>();
		for (Employee e : employees) {
			returnMap.put(e.getFull_name(), managerService.findById(e.getManager_id()).getFull_name());
		}
		System.out.println(returnMap);
		return returnMap;
	}

	public boolean checkAccount(String username, String password) {
		boolean valid = false;

		List<Employee> accounts = new ArrayList<>();
		accounts = this.employeeRepository.findAll();
		for (Employee e : accounts) {
			if (e.getUsername().equals(username) && e.getPassword().equals(password)) {
				valid = true;
			}
		}

		LOG.debug("The username: " + username + " and password: " + password
				+ " were entered. Is this a valid employee account: " + valid);

		return valid;
	}

	public List<Employee> findEmployeesByManagerId(int id) {
		List<Employee> employees = new ArrayList<>();
		employees = this.employeeRepository.findAll();
		employees.removeIf(x -> x.getManager_id() != id);

		LOG.debug("The manager id: " + id
				+ " was entered to find an Emloyees by their manager's id. This is the employee list found: "
				+ employees);
		return employees;
	}

	public Employee findByUsername(String username) {
		List<Employee> employees = new ArrayList<>();
		employees = this.employeeRepository.findAll();
		employees.removeIf(x -> !x.getUsername().equals(username));

		LOG.debug("The employee username: " + username
				+ " was entered to find an Emloyee by Username. This is the employee found: " + employees.get(0));
		return employees.get(0);
	}

	public Employee findById(int id) {
		List<Employee> employees = new ArrayList<>();
		employees = this.employeeRepository.findAll();
		employees.removeIf(x -> x.getId() != id);

		LOG.debug("The employee id: " + id + " was entered to find an Emloyee by id. This is the employee found: "
				+ employees.get(0));
		return

		employees.get(0);
	}

	public Employee findByEmail(String email) {
		List<Employee> employees = new ArrayList<>();
		employees = this.employeeRepository.findAll();
		employees.removeIf(x -> !x.getUsername().equals(email));
		
		System.out.println(employees);

		LOG.debug("The employee email: " + email + " was entered to find an Emloyee by Email. This is the employee found: "
				+ employees.get(0));
		return employees.get(0);
	}
	
	public int findEmployeeIdByUsername(String username) {
		return this.employeeRepository.findEmployeeIdByUsername(username);
		
	}

	// need some type of id or way to get specific employee, easy to change
	public void updateUsername(Employee employee, String username) {
		this.employeeRepository.updateUsername(employee, username);
	}

	public void updatePassword(Employee employee, String password) {
		this.employeeRepository.updatePassword(employee, password);
	}

	public void updateFullName(Employee employee, String fullname) {
		this.employeeRepository.updateFullName(employee, fullname);
	}
	
	public void insert(Employee e) {
		this.employeeRepository.insert(e);
	}

}
