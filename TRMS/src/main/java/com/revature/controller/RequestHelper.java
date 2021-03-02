package com.revature.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Employee;
import com.revature.model.Reimbursement;
import com.revature.service.EmployeeService;
import com.revature.service.ManagerService;
import com.revature.service.ReimbursementService;

public class RequestHelper {

	private static EmployeeService employeeService = new EmployeeService();
	private static ReimbursementService reimbursementService = new ReimbursementService();
	private static ManagerService managerService = new ManagerService();

	public static Object processGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		final String URI = request.getRequestURI();
		System.out.println("URI - " + URI);
		final String RESOURCE = URI.replace("/TRMS/api", "");
		//System.out.println(RESOURCE);
		
		HttpSession session = request.getSession();

		switch (RESOURCE) {
		case "/user/all":
			response.setStatus(200);
			return employeeService.findAll();
		case "/accountInfoShow":
			return employeeService.findByEmail((String) session.getAttribute("useremail"));
		
		

		case "/viewAccountInfo":
			System.out.println("VIEW ACCOUNT INFO SECTION!!!!!");
			return employeeService.findByEmail((String) session.getAttribute("useremail"));
		
			
		case"/updateAccount9090":
			System.out.println("UPDATE ACCOUNT 9090");
			//response.sendRedirect("TRMS/Pages/accountInfo.html");
			RequestDispatcher dispatcher3 = request.getRequestDispatcher("/Pages/accountInfo.html");
			dispatcher3.forward(request, response);
			return 0;
		
		
		//case "/updateAccount":
		//	RequestDispatcher dispatcher3 = request.getRequestDispatcher("/Pages/accountInfo.html");
		//	dispatcher3.forward(request, response);
			//this is the info i want displayed on the /pages/accountInfo page
			//return employeeService.findByEmail((String) session.getAttribute("useremail"));
		
		
		case "/viewReimbursements/Resolved":
			System.out.println("VIEWING RESOLVED!!!!!");
			return reimbursementService.findAllResolvedForEmployee((String) session.getAttribute("useremail"));
		case "/viewReimbursements/Pending":
			return reimbursementService.findAllPendingForEmployee((String) session.getAttribute("useremail"));
		case "/submitReimbursement":
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Pages/submitReimbursement.html");
			dispatcher.forward(request, response);
			//what do i return???
			return "o";
		case "/viewAllResolved":
			return reimbursementService.findAllResolved();
		case "/viewEmployeesAndManagers":
			return employeeService.findAllEmployeeManagers();
		case "/viewPendingForEmployees":
			List<Employee> employees = new ArrayList<>();
			List<Reimbursement> pending = new ArrayList<>();
			
			//finding manager name, use that name to find manager id
			String manager_name = employeeService.findManagerNameByEmployeeUsername((String) session.getAttribute("useremail"));
			int manager_id = managerService.findManagerIdByUsername(manager_name);
			employees = employeeService.findEmployeesByManagerId(manager_id);
			for(Employee e: employees){
				pending.addAll(reimbursementService.findAllPendingForEmployee(e.getUsername()));
			}
				
			return pending;
			
			//employees = employeeService.findEmployeesByManagerId(0);
			//return reimbursementService.findAllPendingForEmployee(employeeService.findManagerNameByEmployeeUsername((String) session.getAttribute("useremail")));
			
			
		case "/viewPendingForEmployee":
			
			final int employee_id = Integer.parseInt(request.getParameter("employeeId"));
			return reimbursementService.findAllPendingForEmployeeById(employee_id);				
		
			
		case "/logout":
			HttpSession session2 = request.getSession(false);
			if (session2 != null) {
				//RequestDispatcher dispatcher2 = request.getRequestDispatcher("/Pages/EmployeeHome.html");
				//dispatcher2.forward(request, response);
				//session2.invalidate();
			}
			//RequestDispatcher dispatcher2 = request.getRequestDispatcher("/index.html");
			//dispatcher2.forward(request, response);
			System.out.println("Literally trying to do nothing");
			return "Your session has been invalidated.";

		
		
		case"/logout2":
			HttpSession session3 = request.getSession(false);
			if (session3 != null) {
				//RequestDispatcher dispatcher2 = request.getRequestDispatcher("/Pages/EmployeeHome.html");
				//dispatcher2.forward(request, response);
				session3.invalidate();
			}
			RequestDispatcher dispatcher2 = request.getRequestDispatcher("/index.html");
			dispatcher2.forward(request, response);
			System.out.println("Literally trying to do nothing");
			return "Your session has been invalidated.";
		default:
			
			response.setStatus(404);
			return "Sorry. The resource you have requested does not exist.";
		}

	}

	public static void processPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		final String URI = request.getRequestURI();
		final String RESOURCE = URI.replace("/TRMS/api", "");

		//System.out.println("IN HERE!!!!!");

		switch (RESOURCE) {
		case"/insertReimbursement":
			
			System.out.println("insert reimbursement!!!!!");
			final float amount = Float.parseFloat(request.getParameter("amount"));
			final String reciept = request.getParameter("reciept");
			
			//need to get the employee id from session somehow
			reimbursementService.insert(new Reimbursement(0, amount, 1, "pending", reciept, 0));
			
			
		case "/updateAccountInfo/password":
			HttpSession session4 = request.getSession(false);
			final String new_password = request.getParameter("password");
			String username = (String) session4.getAttribute("useremail");
			System.out.println(username);
			Employee employee2 = employeeService.findByUsername(username);
			employee2.setPassword(new_password);
			employeeService.update(employee2);
			break;
			
		case "/updateAccountInfo/fullname":
			HttpSession session5 = request.getSession(false);
			final String new_name = request.getParameter("fullname");
			username = (String) session5.getAttribute("useremail");
			System.out.println(username);
			employee2 = employeeService.findByUsername(username);
			employee2.setFull_name(new_name);
			employeeService.update(employee2);
			break;
			
			
		case "/request/deny":
			HttpSession session6 = request.getSession(false);
			int r_id = Integer.parseInt(request.getParameter("id"));
			username = (String) session6.getAttribute("useremail");
			
			String manager_name = employeeService.findFullnameByUsername(username);
			int manager_id = managerService.findManagerIdByUsername(manager_name);
			
			System.out.println(username);
			Reimbursement reimbursement = reimbursementService.findById(r_id);
			reimbursement.setStatus("denied");
			reimbursement.setResolving_manager_id(manager_id);
			reimbursementService.update(reimbursement);
			break;
			
			
		case "/request/approve":
			HttpSession session7 = request.getSession(false);
			r_id = Integer.parseInt(request.getParameter("id"));
			username = (String) session7.getAttribute("useremail");
			
			manager_name = employeeService.findFullnameByUsername(username);
			manager_id = managerService.findManagerIdByUsername(manager_name);

			
			System.out.println(username);
			reimbursement = reimbursementService.findById(r_id);
			reimbursement.setStatus("approved");
			reimbursement.setResolving_manager_id(manager_id);

			reimbursementService.update(reimbursement);
			break;
			
			
		case "/login":
			final String email = request.getParameter("useremail");
			final String password = request.getParameter("userpass");

			if (employeeService.checkAccount(email, password)) {
				// If the user credentials are valid, I'll grant the client a session
				// and perhaps redirect the client to a new resource.

//				response.sendRedirect("/ServletReview/Pages/home.html");
				
				if(employeeService.isEmployeeManager(email, password)) {
					RequestDispatcher dispatcher = request.getRequestDispatcher("/Pages/managerHome.html");
					dispatcher.forward(request, response);
				}
				else {
					RequestDispatcher dispatcher = request.getRequestDispatcher("/Pages/employeeHome.html");
					dispatcher.forward(request, response);
				}

				// Let's also grant the client a session
				HttpSession session = request.getSession();
				session.setAttribute("useremail", email);
				
			}
			break;
		default:
			response.setStatus(404);
		}

	}

}
