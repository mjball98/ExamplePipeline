package com.revature.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
		String RESOURCE = URI.replace("/TRMS/api", "");
		RESOURCE = RESOURCE.replace("/TRMS", "");
		RESOURCE = RESOURCE.replace("/api", "");
		System.out.println("RESOURCE !!!! " + RESOURCE);

		HttpSession session = request.getSession();

		switch (RESOURCE) {

		case "/employeeHome":
			response.sendRedirect("http://localhost:8080/TRMS/employeeHome.html");
			return 0;

		case "/managerHome":
			response.sendRedirect("http://localhost:8080/TRMS/managerHome.html");
			return 0;

		case "/user/all":
			response.setStatus(200);
			return employeeService.findAll();

		case "/viewAccountInfoEmployee":
			response.sendRedirect("http://localhost:8080/TRMS/accountInfo.html");
			return 0;

		case "/viewAccountInfoManager":
			response.sendRedirect("http://localhost:8080/TRMS/accountInfoManager.html");
			return 0;

		case "/viewReimbursements/Resolved":
			System.out.println("VIEWING RESOLVED!!!!!");
			response.sendRedirect("http://localhost:8080/TRMS/resolvedForEmployee.html");
			return reimbursementService.findAllResolvedForEmployee((String) session.getAttribute("useremail"));
		case "/Reimbursement/ResolvedForEmployee":
			// HttpSession sessionR = request.getSession();

			List<Reimbursement> r2 = new ArrayList<>();
			r2 = reimbursementService.findAllResolvedForEmployee((String) session.getAttribute("useremail"));

			PrintWriter writer = response.getWriter();
			response.setContentType("application/json");
			ObjectMapper objectMapper = new ObjectMapper();

			String JSON = objectMapper.writeValueAsString(r2);
			writer.write(JSON);
			System.out.println(JSON);
			return 0;

		case "/viewReimbursements/managerResolved":
			System.out.println("VIEWING RESOLVED!!!!!");
			response.sendRedirect("http://localhost:8080/TRMS/resolvedForManager.html");
			return reimbursementService.findAllResolvedForEmployee((String) session.getAttribute("useremail"));
		case "/Reimbursement/ResolvedForManager":
			// HttpSession sessionR = request.getSession();

			// List<Reimbursement> r2 = new ArrayList<>();
			r2 = reimbursementService.findAllResolvedForEmployee((String) session.getAttribute("useremail"));

			PrintWriter writer4 = response.getWriter();
			response.setContentType("application/json");
			ObjectMapper objectMapper4 = new ObjectMapper();

			JSON = objectMapper4.writeValueAsString(r2);
			writer4.write(JSON);
			System.out.println(JSON);
			return 0;

		case "/viewReimbursements/Pending":
			response.sendRedirect("http://localhost:8080/TRMS/pendingForEmployee.html");
			return reimbursementService.findAllPendingForEmployee((String) session.getAttribute("useremail"));
		case "/Reimbursement/PendingForEmployee":
			// List<Reimbursement> r2 = new ArrayList<>();
			r2 = reimbursementService.findAllPendingForEmployee((String) session.getAttribute("useremail"));

			PrintWriter writer2 = response.getWriter();
			response.setContentType("application/json");
			ObjectMapper objectMapper2 = new ObjectMapper();

			JSON = objectMapper2.writeValueAsString(r2);
			writer2.write(JSON);
			System.out.println(JSON);
			return 0;

		case "/viewReimbursements/managerPending":
			response.sendRedirect("http://localhost:8080/TRMS/pendingForManager.html");
			return reimbursementService.findAllPendingForEmployee((String) session.getAttribute("useremail"));
		case "/Reimbursement/PendingForManager":
			// List<Reimbursement> r2 = new ArrayList<>();
			r2 = reimbursementService.findAllPendingForEmployee((String) session.getAttribute("useremail"));

			PrintWriter writer3 = response.getWriter();
			response.setContentType("application/json");
			ObjectMapper objectMapper3 = new ObjectMapper();

			JSON = objectMapper3.writeValueAsString(r2);
			writer3.write(JSON);
			System.out.println(JSON);
			return 0;

		case "/Employee/submitReimbursement":
			response.sendRedirect("http://localhost:8080/TRMS/submitReimbursement.html");
			return "o";
		case "/Manager/submitReimbursement":
			response.sendRedirect("http://localhost:8080/TRMS/managerSubmitReimbursement.html");
			return "o";

		case "/viewAllResolved":
			response.sendRedirect("http://localhost:8080/TRMS/managerViewAllResolved.html");
			return reimbursementService.findAllResolved();
		case "/Reimbursement/managerViewAllResolved":
			Map<Reimbursement, String> r6 = reimbursementService.findAllResolved();

			PrintWriter writer6 = response.getWriter();
			response.setContentType("application/json");
			ObjectMapper objectMapper6 = new ObjectMapper();

			JSON = objectMapper6.writeValueAsString(r6);
			writer6.write(JSON);
			System.out.println(JSON);
			return 0;

		case "/viewEmployeesAndManagers":
			response.sendRedirect("http://localhost:8080/TRMS/managerViewEmployeesAndManagers.html");
			return employeeService.findAllEmployeeManagers();
		case "/Reimbursement/viewEmployeesAndManagers":
			Map<String, String> e7 = employeeService.findAllEmployeeManagers();

			PrintWriter writer7 = response.getWriter();
			response.setContentType("application/json");
			ObjectMapper objectMapper7 = new ObjectMapper();

			JSON = objectMapper7.writeValueAsString(e7);
			writer7.write(JSON);
			System.out.println(JSON);
			return 0;

		case "/viewPendingForEmployees":
			response.sendRedirect("http://localhost:8080/TRMS/viewPendingForManagersEmployees.html");
			return 0;
		case "/Reimbursement/viewPendingForManagersEmployees":
			List<Employee> employees = new ArrayList<>();
			List<Reimbursement> pending = new ArrayList<>();

			// finding manager name, use that name to find manager id
			String manager_name = employeeService
					.findManagerNameByEmployeeUsername((String) session.getAttribute("useremail"));
			int manager_id = managerService.findManagerIdByUsername(manager_name);
			employees = employeeService.findEmployeesByManagerId(manager_id);
			for (Employee e : employees) {
				pending.addAll(reimbursementService.findAllPendingForEmployee(e.getUsername()));
			}

			PrintWriter writer5 = response.getWriter();
			response.setContentType("application/json");
			ObjectMapper objectMapper5 = new ObjectMapper();

			JSON = objectMapper5.writeValueAsString(pending);
			writer5.write(JSON);
			System.out.println(JSON);
			return 0;

		// employees = employeeService.findEmployeesByManagerId(0);
		// return
		// reimbursementService.findAllPendingForEmployee(employeeService.findManagerNameByEmployeeUsername((String)
		// session.getAttribute("useremail")));

		case "/managerViewPendingForEmployee":
			//request.setAttribute("employeeId", Integer.parseInt(request.getParameter("employeeId")));
			session.setAttribute("employeeId", Integer.parseInt(request.getParameter("employeeId")));
			response.sendRedirect("http://localhost:8080/TRMS/managerViewPendingForEmployee.html");
			return 0;
		case "/Reimbursement/managerViewPendingForEmployee":
			final int employee_id = (int) session.getAttribute("employeeId");
			List<Reimbursement> r9 = new ArrayList<>();
			r9 = reimbursementService.findAllPendingForEmployeeById(employee_id);

			PrintWriter writer9 = response.getWriter();
			response.setContentType("application/json");
			ObjectMapper objectMapper9 = new ObjectMapper();

			JSON = objectMapper9.writeValueAsString(r9);
			writer9.write(JSON);
			System.out.println(JSON);
			return 0;

		case "/logout":
			HttpSession session2 = request.getSession(false);
			if (session2 != null) {
				// RequestDispatcher dispatcher2 =
				// request.getRequestDispatcher("/Pages/EmployeeHome.html");
				// dispatcher2.forward(request, response);
				// session2.invalidate();
			}
			// RequestDispatcher dispatcher2 = request.getRequestDispatcher("/index.html");
			// dispatcher2.forward(request, response);
			System.out.println("Literally trying to do nothing");
			return "Your session has been invalidated.";

		case "/logout2":
			HttpSession session3 = request.getSession(false);
			if (session3 != null) {
				// RequestDispatcher dispatcher2 =
				// request.getRequestDispatcher("/Pages/EmployeeHome.html");
				// dispatcher2.forward(request, response);
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

		// System.out.println("IN HERE!!!!!");

		switch (RESOURCE) {

		case "/Employee/insertReimbursement":

			System.out.println("insert reimbursement!!!!!");
			float amount = Float.parseFloat(request.getParameter("amount"));
			String reciept = request.getParameter("reciept");

			// need to get the employee id from session somehow
			reimbursementService.insert(new Reimbursement(0, amount, 1, "pending", reciept, 0));
			// response.sendRedirect("http://localhost:8080/TRMS/employeeHome.html");

		case "/Manager/insertReimbursement":

			System.out.println("insert reimbursement!!!!!");
			amount = Float.parseFloat(request.getParameter("amount"));
			reciept = request.getParameter("reciept");

			// need to get the employee id from session somehow
			reimbursementService.insert(new Reimbursement(0, amount, 1, "pending", reciept, 0));
			response.sendRedirect("http://localhost:8080/TRMS/managerHome.html");

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
			response.sendRedirect("http://localhost:8080/TRMS/managerHome.html");
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
			response.sendRedirect("http://localhost:8080/TRMS/managerHome.html");
			break;

		case "/login":
			final String email = request.getParameter("useremail");
			final String password = request.getParameter("userpass");

			if (employeeService.checkAccount(email, password)) {
				// If the user credentials are valid, I'll grant the client a session
				// and perhaps redirect the client to a new resource.

//				response.sendRedirect("/ServletReview/Pages/home.html");

				if (employeeService.isEmployeeManager(email, password)) {
					RequestDispatcher dispatcher = request.getRequestDispatcher("/managerHome.html");
					dispatcher.forward(request, response);
				} else {
					RequestDispatcher dispatcher = request.getRequestDispatcher("/employeeHome.html");
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
