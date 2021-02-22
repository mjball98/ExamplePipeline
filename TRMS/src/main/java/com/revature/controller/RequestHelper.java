package com.revature.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.model.Reimbursement;
import com.revature.service.EmployeeService;
import com.revature.service.ReimbursementService;

public class RequestHelper {

	private static EmployeeService employeeService = new EmployeeService();
	private static ReimbursementService reimbursementService = new ReimbursementService();

	public static Object processGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		final String URI = request.getRequestURI();
		System.out.println(URI);
		final String RESOURCE = URI.replace("/TRMS/api", "");
		System.out.println(RESOURCE);
		
		HttpSession session = request.getSession();

		switch (RESOURCE) {
		case "/user/all":
			response.setStatus(200);
			return employeeService.findAll();
		case "/accountInfoShow":
			return employeeService.findByEmail((String) session.getAttribute("useremail"));
		case "/viewAccountInfo":
			//RequestDispatcher dispatcher = request.getRequestDispatcher("/Pages/accountInfo.html");
			//dispatcher.forward(request, response);
			return employeeService.findByEmail((String) session.getAttribute("useremail"));
		case "/viewReimbursements/Resolved":
			return reimbursementService.findAllResolvedForEmployee((String) session.getAttribute("useremail"));
		case "/viewReimbursements/Pending":
			return reimbursementService.findAllPendingForEmployee((String) session.getAttribute("useremail"));
		case "/submitReimbursement":
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Pages/submitReimbursement.html");
			dispatcher.forward(request, response);
			//what do i return???
			return "o";
		case "/logout":
			HttpSession session2 = request.getSession(false);
			if (session2 != null) {
				session2.invalidate();
			}
			RequestDispatcher dispatcher2 = request.getRequestDispatcher("/TRMS");
			dispatcher2.forward(request, response);
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
		case "/login":
			final String email = request.getParameter("useremail");
			final String password = request.getParameter("userpass");

			if (employeeService.checkAccount(email, password)) {
				// If the user credentials are valid, I'll grant the client a session
				// and perhaps redirect the client to a new resource.

//				response.sendRedirect("/ServletReview/Pages/home.html");
				
				System.out.println("IN HERE IN LOGIN");

				RequestDispatcher dispatcher = request.getRequestDispatcher("/Pages/home.html");
				dispatcher.forward(request, response);

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
