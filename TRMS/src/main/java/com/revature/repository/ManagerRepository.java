package com.revature.repository;

import java.util.List;

import com.revature.model.Manager;

public interface ManagerRepository {
	
	List<Manager> findAll();
	void insert(Manager m);

}
