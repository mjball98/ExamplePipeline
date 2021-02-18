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

	public List<Reimbursement> findAllPendingForEmployee(int id) {
		List<Reimbursement> reimbursements = new ArrayList<>();
		reimbursements = this.reimbursementRepository.findAll();

		reimbursements.removeIf(x -> x.getEmployee_id() != id);
		reimbursements.removeIf(x -> !x.getStatus().equals("pending"));

		LOG.debug("The employee id: " + id
				+ " was entered to find all pending reimbursements by employee id. This is the list of reimbursements: "
				+ reimbursements);
		return reimbursements;
	}

	public List<Reimbursement> findAllResolvedForEmployee(int id) {
		List<Reimbursement> reimbursements = new ArrayList<>();
		reimbursements = this.reimbursementRepository.findAll();

		reimbursements.removeIf(x -> x.getEmployee_id() != id);
		reimbursements.removeIf(x -> x.getStatus().equals("pending"));

		LOG.debug("The employee id: " + id
				+ " was entered to find all resolved reimbursements by employee id. This is the list of reimbursements: "
				+ reimbursements);
		return reimbursements;
	}

	public Map<Reimbursement, String> findAllResolved() {
		List<Reimbursement> reimbursements = new ArrayList<>();
		reimbursements = this.reimbursementRepository.findAll();

		reimbursements.removeIf(x -> x.getStatus().equals("pending"));

		Map<Reimbursement, String> returnMap = new HashMap<Reimbursement, String>();
		for (Reimbursement r : reimbursements) {
			returnMap.put(r, "Manager Name: " + managerService.findById(r.getResolving_manager_id()).getFull_name());
		}

		LOG.debug(
				"A manager wants to see all of the resolved reimbursements, and the manager which resolved it. This is the list of reimbursements: "
						+ returnMap);

		return returnMap;
	}
	
	public void reimburesemntApprove(int id) {
		this.reimbursementRepository.update(id, "approved");
	}
	
	public void reimbursementDeny(int id) {
		this.reimbursementRepository.update(id, "denied");
	}

}
