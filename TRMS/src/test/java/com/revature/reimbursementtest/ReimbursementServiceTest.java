package com.revature.reimbursementtest;

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

import com.revature.model.Reimbursement;
import com.revature.repository.ReimbursementRepositoryImpl;
import com.revature.service.ReimbursementService;

public class ReimbursementServiceTest {
	@InjectMocks
	private static ReimbursementService reimbursementService;

	@Mock
	private ReimbursementRepositoryImpl reimbursementRepositoryImpl;

	@BeforeClass
	public static void setup() {
		reimbursementService = new ReimbursementService();
	}

	@Before
	public void beforeSetup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testReimbursementServiceFindAll() {
		ArrayList<Reimbursement> myReimbursements = new ArrayList<>(Arrays.asList(
				new Reimbursement(1, 2000, 3, "denied", "none", 1), new Reimbursement(12, 350, 1, "pending", "none", 0),
				new Reimbursement(3, 99999, 15, "approved", "none", 2)));

		Mockito.when(reimbursementRepositoryImpl.findAll()).thenReturn(myReimbursements);

		Assert.assertEquals(myReimbursements, reimbursementService.findAll());

	}

	@Test
	public void testReimbursementFindById() {
		ArrayList<Reimbursement> myReimbursements = new ArrayList<>(Arrays.asList(
				new Reimbursement(1, 2000, 3, "denied", "none", 1), new Reimbursement(12, 350, 1, "pending", "none", 0),
				new Reimbursement(3, 99999, 15, "approved", "none", 2)));

		Mockito.when(reimbursementRepositoryImpl.findAll()).thenReturn(myReimbursements);

		Reimbursement returnedReimbursement = reimbursementService.findById(3);

		for (Reimbursement r : myReimbursements) {
			if (r.getId() == 3) {
				Assert.assertEquals(r.getEmployee_id(), returnedReimbursement.getEmployee_id());
			}
		}

	}

	@Test
	public void testReimbursementfindAllPendingForEmployeebyId() {
		ArrayList<Reimbursement> myReimbursements = new ArrayList<>(Arrays.asList(
				new Reimbursement(1, 2000, 3, "denied", "none", 1), new Reimbursement(12, 350, 1, "pending", "none", 0),
				new Reimbursement(3, 99999, 15, "approved", "none", 2)));

		Mockito.when(reimbursementRepositoryImpl.findAll()).thenReturn(myReimbursements);

		
		
		List<Reimbursement> reimbursements = reimbursementService.findAllPendingForEmployeeById(3);

		for (Reimbursement r : myReimbursements) {
			if (r.getId() == 3) {
				Assert.assertEquals(r.getEmployee_id(), reimbursements.get(0).getEmployee_id());
			}
		}

	}
}
