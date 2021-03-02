package com.revature.repository;

import java.util.List;

import com.revature.model.Reimbursement;

public interface ReimbursementRepository {
	
	void insert(Reimbursement reimbursement);
	List<Reimbursement> findAll();
	void update(Reimbursement r1);
}
