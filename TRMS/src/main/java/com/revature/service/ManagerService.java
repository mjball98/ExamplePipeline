package com.revature.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.model.Manager;
import com.revature.repository.ManagerRepository;
import com.revature.repository.ManagerRepositoryImpl;

public class ManagerService {
	
	private static final Logger LOG = LogManager.getFormatterLogger(ManagerService.class);
	
	private ManagerRepository managerRepository;

	public ManagerService() {
		this.managerRepository = new ManagerRepositoryImpl();
	}
	
	public List<Manager> findAll(){
		return this.managerRepository.findAll();
	}
	
	
	public Manager findById(int id) {
		List<Manager> managers = new ArrayList<>();
		managers = this.managerRepository.findAll();
		managers.removeIf(x -> x.getId() != id);
		
		LOG.debug("The manager id: "+id+" was entered to find an Manager by Id. This is the manager found: "+managers.get(0));
		return managers.get(0);
	}

}
