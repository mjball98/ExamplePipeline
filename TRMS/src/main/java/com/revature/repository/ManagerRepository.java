package com.revature.repository;

import java.util.List;

import com.revature.model.Manager;

public interface ManagerRepository {
	
	Manager findById(int id);
	List<Manager> findAll();

}
