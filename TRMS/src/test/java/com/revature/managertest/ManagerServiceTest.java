package com.revature.managertest;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.revature.model.Manager;
import com.revature.repository.ManagerRepositoryImpl;
import com.revature.service.ManagerService;

public class ManagerServiceTest {

	@InjectMocks
	private static ManagerService managerService;

	@Mock
	private ManagerRepositoryImpl managerRepositoryImpl;

	@BeforeClass
	public static void setup() {
		managerService = new ManagerService();
	}

	@Before
	public void beforeSetup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testManagerServiceFindAll() {
		ArrayList<Manager> myManagers = new ArrayList<>(Arrays.asList(new Manager(1, 2, "test name"),
				new Manager(12, 3, "manager name"), new Manager(3, 1, "dummy name")));

		Mockito.when(managerRepositoryImpl.findAll()).thenReturn(myManagers);

		Assert.assertEquals(myManagers, managerService.findAll());

	}

	@Test
	public void testFindManagerIdByUsername() {
		ArrayList<Manager> myManagers = new ArrayList<>(Arrays.asList(new Manager(1, 2, "test name"),
				new Manager(12, 3, "manager name"), new Manager(3, 1, "dummy name")));

		Mockito.when(managerRepositoryImpl.findAll()).thenReturn(myManagers);

		int returned_id = managerService.findManagerIdByUsername("dummy name");

		Assert.assertEquals(returned_id, 3);

	}

	@Test
	public void testManagerFindById() {
		ArrayList<Manager> myManagers = new ArrayList<>(Arrays.asList(new Manager(1, 2, "test name"),
				new Manager(12, 3, "manager name"), new Manager(3, 1, "dummy name")));

		Mockito.when(managerRepositoryImpl.findAll()).thenReturn(myManagers);

		Manager returned_manager = managerService.findById(3);

		for (Manager m : myManagers) {
			if (m.getId() == 3) {
				Assert.assertEquals(m.getFull_name(), returned_manager.getFull_name());
			}
		}
	}

}
