package com.revature.employeetest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.revature.model.Employee;
import com.revature.repository.EmployeeRepositoryImpl;
import com.revature.service.EmployeeService;

public class EmployeeServiceTest {

	@InjectMocks
	private static EmployeeService employeeService;

	@Mock
	private EmployeeRepositoryImpl employeeRepositoryImpl;

	@BeforeClass
	public static void setup() {
		employeeService = new EmployeeService();
	}

	@Before
	public void beforeSetup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testEmployeeServiceFindAll() {
		ArrayList<Employee> myEmployees = new ArrayList<>(
				Arrays.asList(new Employee(1, "test@email.com", "testpass", "Employee name", 1, false),
						new Employee(12, "emp@email.com", "pass", "test name", 2, true),
						new Employee(3, "mock@email.com", "test", "fake name", 1, false)));

		Mockito.when(employeeRepositoryImpl.findAll()).thenReturn(myEmployees);

		Assert.assertEquals(myEmployees, employeeService.findAll());

	}

	@Test
	public void testEmployeeAccountLoginSuccess() {

		ArrayList<Employee> myEmployees = new ArrayList<>(
				Arrays.asList(new Employee(1, "test@email.com", "testpass", "Employee name", 1, false),
						new Employee(12, "emp@email.com", "pass", "test name", 2, true),
						new Employee(3, "mock@email.com", "test", "fake name", 1, false)));

		Mockito.when(employeeRepositoryImpl.findAll()).thenReturn(myEmployees);

		boolean validEmployee = employeeService.checkAccount("test@email.com", "testpass");
		Assert.assertEquals(true, validEmployee);
	}

	@Test
	public void testEmployeeAccountLoginFailure() {

		ArrayList<Employee> myEmployees = new ArrayList<>(
				Arrays.asList(new Employee(1, "test@email.com", "testpass", "Employee name", 1, false),
						new Employee(12, "manager@email.com", "pass", "test name", 2, true),
						new Employee(3, "mock@email.com", "test", "fake name", 1, false)));

		Mockito.when(employeeRepositoryImpl.findAll()).thenReturn(myEmployees);

		boolean validEmployee = employeeService.checkAccount("wrong@email", "login");
		Assert.assertEquals(false, validEmployee);
	}

	@Test
	public void testIsEmployeeManagerTrue() {

		ArrayList<Employee> myEmployees = new ArrayList<>(
				Arrays.asList(new Employee(1, "test@email.com", "testpass", "Employee name", 1, false),
						new Employee(12, "manager@email.com", "pass", "test name", 2, true),
						new Employee(3, "mock@email.com", "test", "fake name", 1, false)));

		Mockito.when(employeeRepositoryImpl.findAll()).thenReturn(myEmployees);

		boolean isManager = employeeService.isEmployeeManager("manager@email.com", "pass");
		Assert.assertEquals(true, isManager);
	}

	@Test
	public void testIsEmployeeManagerFalse() {

		ArrayList<Employee> myEmployees = new ArrayList<>(
				Arrays.asList(new Employee(1, "test@email.com", "testpass", "Employee name", 1, false),
						new Employee(12, "manager@email.com", "pass", "test name", 2, true),
						new Employee(3, "mock@email.com", "test", "fake name", 1, false)));

		Mockito.when(employeeRepositoryImpl.findAll()).thenReturn(myEmployees);

		boolean isManager = employeeService.isEmployeeManager("mock@email.com", "test");
		Assert.assertEquals(false, isManager);
	}

	@Test
	public void testEmployeeFindByUsername() {
		ArrayList<Employee> myEmployees = new ArrayList<>(
				Arrays.asList(new Employee(1, "test@email.com", "testpass", "Employee name", 1, false),
						new Employee(12, "manager@email.com", "pass", "test name", 2, true),
						new Employee(3, "mock@email.com", "test", "fake name", 1, false)));

		Mockito.when(employeeRepositoryImpl.findAll()).thenReturn(myEmployees);

		Employee returnedEmployee = null;

		for (Employee e : myEmployees) {
			returnedEmployee = employeeService.findByUsername(e.getUsername());
			Assert.assertEquals(e.getId(), returnedEmployee.getId());
		}

	}

	@Test
	public void testEmployeeFindByManagerId() {
		ArrayList<Employee> myEmployees = new ArrayList<>(
				Arrays.asList(new Employee(1, "test@email.com", "testpass", "Employee name", 1, false),
						new Employee(12, "manager@email.com", "pass", "test name", 2, true),
						new Employee(3, "mock@email.com", "test", "fake name", 1, false)));

		Mockito.when(employeeRepositoryImpl.findAll()).thenReturn(myEmployees);

		List<Employee> returnedEmployee = employeeService.findEmployeesByManagerId(2);

		for (Employee e : myEmployees) {
			if (e.getManager_id() == 2) {
				Assert.assertEquals(e.getId(), returnedEmployee.get(0).getId());
			}
		}

	}

	@Test
	public void testEmployeeFindById() {
		ArrayList<Employee> myEmployees = new ArrayList<>(
				Arrays.asList(new Employee(1, "test@email.com", "testpass", "Employee name", 1, false),
						new Employee(12, "manager@email.com", "pass", "test name", 2, true),
						new Employee(3, "mock@email.com", "test", "fake name", 1, false)));

		Mockito.when(employeeRepositoryImpl.findAll()).thenReturn(myEmployees);

		Employee returnedEmployee = employeeService.findById(3);

		for (Employee e : myEmployees) {
			if (e.getId() == 3) {
				Assert.assertEquals(e.getUsername(), returnedEmployee.getUsername());
			}
		}

	}
	
	@Test
	public void testEmployeeFindByEmail() {
		ArrayList<Employee> myEmployees = new ArrayList<>(
				Arrays.asList(new Employee(1, "test@email.com", "testpass", "Employee name", 1, false),
						new Employee(12, "manager@email.com", "pass", "test name", 2, true),
						new Employee(3, "mock@email.com", "test", "fake name", 1, false)));

		Mockito.when(employeeRepositoryImpl.findAll()).thenReturn(myEmployees);

		Employee returnedEmployee = employeeService.findByEmail("mock@email.com");

		for (Employee e : myEmployees) {
			if (e.getUsername().equals("mock@email.com")) {
				Assert.assertEquals(e.getId(), returnedEmployee.getId());
			}
		}

	}
	
	@Test
	public void testEmployeeFindFullnameByUsername() {
		ArrayList<Employee> myEmployees = new ArrayList<>(
				Arrays.asList(new Employee(1, "test@email.com", "testpass", "Employee name", 1, false),
						new Employee(12, "manager@email.com", "pass", "test name", 2, true),
						new Employee(3, "mock@email.com", "test", "fake name", 1, false)));

		Mockito.when(employeeRepositoryImpl.findAll()).thenReturn(myEmployees);

		String returnedName = employeeService.findFullnameByUsername("mock@email.com");

		Assert.assertEquals(returnedName, "fake name");

	}
	
	
	@Test
	public void testFindEmployeeIdByUsername(){
		ArrayList<Employee> myEmployees = new ArrayList<>(
				Arrays.asList(new Employee(1, "test@email.com", "testpass", "Employee name", 1, false),
						new Employee(12, "manager@email.com", "pass", "test name", 2, true),
						new Employee(3, "mock@email.com", "test", "fake name", 1, false)));

		Mockito.when(employeeRepositoryImpl.findAll()).thenReturn(myEmployees);

		int returned_id = employeeService.findEmployeeIdByUsername("mock@email.com");

		Assert.assertEquals(returned_id, 3);

	}

}
