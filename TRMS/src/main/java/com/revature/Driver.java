package com.revature;

import com.revature.model.Reimbursement;
import com.revature.service.ReimbursementService;

public class Driver {

	public static void main(String[] args) {

		/*
		 * Note that we do not ordinarily call methods that are present on our
		 * repository directly. We usually pass this data through our service layer.
		 * This means that we should be using our CardService in this layer - NOT our
		 * CardRepository.
		 */

		//CardService cardService = new CardService();

		//Card card1 = new Card(2, "Shiny New Pokemon", true, new Date(323733883838L));
		//cardService.insert(card1);
		
		//Employee e1 = new Employee(1, "base@email.com", "password", "Base Employee", 1, false);
		//EmployeeService eService = new EmployeeService();
		//eService.insert(e1);
		
		//Manager m1 = new Manager(1,3,"Boss Manager");
		//ManagerService mService = new ManagerService();
		//mService.insert(m1);

		//System.out.println(cardService.findAll());

		Reimbursement r1 = new Reimbursement(1, 3300, 4, "denied", "expensive reciept", 1);
		ReimbursementService rService = new ReimbursementService();
		rService.insert(r1);
		
		//modifying a file to see if jenkins pipeline is working
		//another test for jenkins, please work
	}
}
