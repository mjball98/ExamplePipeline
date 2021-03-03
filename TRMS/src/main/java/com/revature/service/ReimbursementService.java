package com.revature.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.model.Reimbursement;
import com.revature.repository.ReimbursementRepository;
import com.revature.repository.ReimbursementRepositoryImpl;

public class ReimbursementService {

	private ReimbursementRepository reimbursementRepository;

	ManagerService managerService = new ManagerService();
	EmployeeService employeeService = new EmployeeService();

	private static final Logger LOG = LogManager.getFormatterLogger(ReimbursementService.class);

	public ReimbursementService() {
		this.reimbursementRepository = new ReimbursementRepositoryImpl();
	}

	public void insert(Reimbursement reimbursement) {
		this.reimbursementRepository.insert(reimbursement);
	}

	public List<Reimbursement> findAll() {
		return this.reimbursementRepository.findAll();
	}
	
	public Reimbursement findById(int id) {
		List<Reimbursement> reimbursements = new ArrayList<>();
		reimbursements = this.reimbursementRepository.findAll();
	
		reimbursements.removeIf(x -> x.getId() != id);
		
		return reimbursements.get(0);
	}

	public List<Reimbursement> findAllPendingForEmployee(String username) {


		List<Reimbursement> reimbursements = new ArrayList<>();
		reimbursements = this.reimbursementRepository.findAll();

		int id = employeeService.findEmployeeIdByUsername(username);

		reimbursements.removeIf(x -> x.getEmployee_id() != id);
		reimbursements.removeIf(x -> !x.getStatus().equals("pending"));

		LOG.debug("The employee id: " + id
				+ " was entered to find all pending reimbursements by employee id. This is the list of reimbursements: "
				+ reimbursements);
		return reimbursements;
	}

	public List<Reimbursement> findAllPendingForEmployeeById(int id) {


		List<Reimbursement> reimbursements = new ArrayList<>();
		reimbursements = this.reimbursementRepository.findAll();


		reimbursements.removeIf(x -> x.getEmployee_id() != id);
		reimbursements.removeIf(x -> !x.getStatus().equals("pending"));

		LOG.debug("The employee id: " + id
				+ " was entered to find all pending reimbursements by employee id. This is the list of reimbursements: "
				+ reimbursements);
		return reimbursements;
	}

	public List<Reimbursement> findAllResolvedForEmployee(String username) {
		List<Reimbursement> reimbursements = new ArrayList<>();
		reimbursements = this.reimbursementRepository.findAll();

		int id = employeeService.findEmployeeIdByUsername(username);

		reimbursements.removeIf(x -> x.getEmployee_id() != id);
		reimbursements.removeIf(x -> x.getStatus().equals("pending"));

		LOG.debug("The employee id: " + id
				+ " was entered to find all resolved reimbursements by employee id. This is the list of reimbursements: "
				+ reimbursements);
		return reimbursements;
	}

	public List<Reimbursement> findAllResolved() {
		List<Reimbursement> reimbursements = new ArrayList<>();
		reimbursements = this.reimbursementRepository.findAll();

		reimbursements.removeIf(x -> x.getStatus().equals("pending"));

		return reimbursements;
	}
	
	public void update(Reimbursement r1) {
		this.reimbursementRepository.update(r1);
	}

}
