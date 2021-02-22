package com.revature.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		case "/viewAccountInfo":
			return employeeService.findByEmail((String) session.getAttribute("useremail"));
		case "/viewReimbursements/Resolved":
			return reimbursementService.findAllResolvedForEmployee((String) session.getAttribute("useremail"));
		case "/viewReimbursements/Pending":
			return reimbursementService.findAllPendingForEmployee((String) session.getAttribute("useremail"));
		case "/logout":
			HttpSession session2 = request.getSession(false);
			if (session2 != null) {
				session2.invalidate();
			}
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

		System.out.println("IN HERE!!!!!");

		switch (RESOURCE) {
		case "/login":
			final String email = request.getParameter("useremail");
			final String password = request.getParameter("userpass");

			if (employeeService.checkAccount(email, password)) {
				// If the user credentials are valid, I'll grant the client a session
				// and perhaps redirect the client to a new resource.

//				response.sendRedirect("/ServletReview/Pages/home.html");

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
