package com.revature.service;

import com.revature.model.Reimbursement;
import com.revature.repository.ReimbursementRepository;
import com.revature.repository.ReimbursementRepositoryImpl;

public class ReimbursementService {
	
	private ReimbursementRepository reimbursementRepository;

	public ReimbursementService() {
		this.reimbursementRepository = new ReimbursementRepositoryImpl();
	}

	public void insert(Reimbursement reimbursement) {
		this.reimbursementRepository.insert(reimbursement);
	}

}
