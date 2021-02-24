package com.revature;

import com.revature.model.Employee;
import com.revature.service.EmployeeService;

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
		
		Employee e1 = new Employee(1, "manager@email.com", "password", "Manager Name", 1, true);
		EmployeeService eService = new EmployeeService();
		eService.insert(e1);
		
		//Manager m1 = new Manager(1,0,"Dummy Manager");
		//ManagerService mService = new ManagerService();
		//mService.insert(m1);

		//System.out.println(cardService.findAll());

		//Reimbursement r1 = new Reimbursement(1, 100, 1, "pending", "no reciept", 0);
		//ReimbursementService rService = new ReimbursementService();
		//rService.insert(r1);
		
		
	}
}
