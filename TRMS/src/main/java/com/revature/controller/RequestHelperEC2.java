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

public class RequestHelperEC2 {

	private static EmployeeService employeeService = new EmployeeService();
	private static ReimbursementService reimbursementService = new ReimbursementService();
	private static ManagerService managerService = new ManagerService();

	public static Object processGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		final String URI = request.getRequestURI();
		String RESOURCE = URI.replace("/TRMS/api", "");
		RESOURCE = RESOURCE.replace("/TRMS", "");
		RESOURCE = RESOURCE.replace("/api", "");

		HttpSession session = request.getSession();

		switch (RESOURCE) {

		case "/employeeHome":
			response.sendRedirect("http://18.219.228.37:8088/TRMS/employeeHome.html");
			return 0;

		case "/managerHome":
			response.sendRedirect("http://18.219.228.37:8088/TRMS/managerHome.html");
			return 0;

		case "/user/all":
			response.setStatus(200);
			return employeeService.findAll();

		case "/viewAccountInfoEmployee":
			response.sendRedirect("http://18.219.228.37:8088/TRMS/accountInfo.html");
			return 0;

		case "/viewAccountInfoManager":
			response.sendRedirect("http://18.219.228.37:8088/TRMS/accountInfoManager.html");
			return 0;

		case "/viewReimbursements/Resolved":
			response.sendRedirect("http://18.219.228.37:8088/TRMS/resolvedForEmployee.html");
			return reimbursementService.findAllResolvedForEmployee((String) session.getAttribute("useremail"));
		case "/Reimbursement/ResolvedForEmployee":

			List<Reimbursement> r2 = new ArrayList<>();
			r2 = reimbursementService.findAllResolvedForEmployee((String) session.getAttribute("useremail"));

			PrintWriter writer = response.getWriter();
			response.setContentType("application/json");
			ObjectMapper objectMapper = new ObjectMapper();

			String JSON = objectMapper.writeValueAsString(r2);
			writer.write(JSON);
			return 0;

		case "/viewReimbursements/managerResolved":
			response.sendRedirect("http://18.219.228.37:8088/TRMS/resolvedForManager.html");
			return reimbursementService.findAllResolvedForEmployee((String) session.getAttribute("useremail"));
		case "/Reimbursement/ResolvedForManager":
			r2 = reimbursementService.findAllResolvedForEmployee((String) session.getAttribute("useremail"));

			PrintWriter writer4 = response.getWriter();
			response.setContentType("application/json");
			ObjectMapper objectMapper4 = new ObjectMapper();

			JSON = objectMapper4.writeValueAsString(r2);
			writer4.write(JSON);
			return 0;

		case "/viewReimbursements/Pending":
			response.sendRedirect("http://18.219.228.37:8088/TRMS/pendingForEmployee.html");
			return reimbursementService.findAllPendingForEmployee((String) session.getAttribute("useremail"));
		case "/Reimbursement/PendingForEmployee":
			r2 = reimbursementService.findAllPendingForEmployee((String) session.getAttribute("useremail"));

			PrintWriter writer2 = response.getWriter();
			response.setContentType("application/json");
			ObjectMapper objectMapper2 = new ObjectMapper();

			JSON = objectMapper2.writeValueAsString(r2);
			writer2.write(JSON);
			return 0;

		case "/viewReimbursements/managerPending":
			response.sendRedirect("http://18.219.228.37:8088/TRMS/pendingForManager.html");
			return reimbursementService.findAllPendingForEmployee((String) session.getAttribute("useremail"));
		case "/Reimbursement/PendingForManager":
			r2 = reimbursementService.findAllPendingForEmployee((String) session.getAttribute("useremail"));

			PrintWriter writer3 = response.getWriter();
			response.setContentType("application/json");
			ObjectMapper objectMapper3 = new ObjectMapper();

			JSON = objectMapper3.writeValueAsString(r2);
			writer3.write(JSON);
			return 0;

		case "/Employee/submitReimbursement":
			response.sendRedirect("http://18.219.228.37:8088/TRMS/submitReimbursement.html");
			return "o";
		case "/Manager/submitReimbursement":
			response.sendRedirect("http://18.219.228.37:8088/TRMS/managerSubmitReimbursement.html");
			return "o";

		case "/viewAllResolved":
			response.sendRedirect("http://18.219.228.37:8088/TRMS/managerViewAllResolved.html");
			return reimbursementService.findAllResolved();
		case "/Reimbursement/managerViewAllResolved":
			//Map<Reimbursement, String>
			r2 = reimbursementService.findAllResolved();

			PrintWriter writer6 = response.getWriter();
			response.setContentType("application/json");
			ObjectMapper objectMapper6 = new ObjectMapper();

			JSON = objectMapper6.writeValueAsString(r2);
			writer6.write(JSON);
			return 0;

		case "/viewEmployeesAndManagers":
			response.sendRedirect("http://18.219.228.37:8088/TRMS/managerViewEmployeesAndManagers.html");
			return employeeService.findAllEmployeeManagers();
		case "/Reimbursement/viewEmployeesAndManagers":
			Map<String, String> e7 = employeeService.findAllEmployeeManagers();

			PrintWriter writer7 = response.getWriter();
			response.setContentType("application/json");
			ObjectMapper objectMapper7 = new ObjectMapper();

			JSON = objectMapper7.writeValueAsString(e7);
			writer7.write(JSON);
			return 0;

		case "/viewPendingForEmployees":
			response.sendRedirect("http://18.219.228.37:8088/TRMS/viewPendingForManagersEmployees.html");
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
			return 0;


		case "/managerViewPendingForEmployee":
			session.setAttribute("employeeId", Integer.parseInt(request.getParameter("employeeId")));
			response.sendRedirect("http://18.219.228.37:8088/TRMS/managerViewPendingForEmployee.html");
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


		switch (RESOURCE) {

		case "/Employee/insertReimbursement":

			float amount = Float.parseFloat(request.getParameter("amount"));
			String reciept = request.getParameter("reciept");

			HttpSession session17 = request.getSession(false);
			int emp_id = employeeService.findEmployeeIdByUsername((String) session17.getAttribute("useremail"));
			
			reimbursementService.insert(new Reimbursement(0, amount, emp_id, "pending", reciept, 0));
			response.sendRedirect("http://18.219.228.37:8088/TRMS/employeeHome.html");
			break;

		case "/Manager/insertReimbursement":

			amount = Float.parseFloat(request.getParameter("amount"));
			reciept = request.getParameter("reciept");
			
			HttpSession session16 = request.getSession(false);
			emp_id = employeeService.findEmployeeIdByUsername((String) session16.getAttribute("useremail"));

			reimbursementService.insert(new Reimbursement(0, amount, emp_id, "pending", reciept, 0));
			response.sendRedirect("http://18.219.228.37:8088/TRMS/managerHome.html");
			break;

		case "/Employee/updateAccountInfo/password":
			HttpSession session4 = request.getSession(false);
			String new_password = request.getParameter("password");
			String username = (String) session4.getAttribute("useremail");
			Employee employee2 = employeeService.findByUsername(username);
			employee2.setPassword(new_password);
			employeeService.update(employee2);
			response.sendRedirect("http://18.219.228.37:8088/TRMS/employeeHome.html");
			break;

		case "/Employee/updateAccountInfo/fullname":
			HttpSession session5 = request.getSession(false);
			String new_name = request.getParameter("fullname");
			username = (String) session5.getAttribute("useremail");
			employee2 = employeeService.findByUsername(username);
			employee2.setFull_name(new_name);
			employeeService.update(employee2);
			response.sendRedirect("http://18.219.228.37:8088/TRMS/employeeHome.html");
			break;
			
		case "/Manager/updateAccountInfo/password":
			HttpSession session24 = request.getSession(false);
			new_password = request.getParameter("password");
			username = (String) session24.getAttribute("useremail");
			employee2 = employeeService.findByUsername(username);
			employee2.setPassword(new_password);
			employeeService.update(employee2);
			response.sendRedirect("http://18.219.228.37:8088/TRMS/managerHome.html");
			break;

		case "/Manager/updateAccountInfo/fullname":
			HttpSession session25 = request.getSession(false);
			new_name = request.getParameter("fullname");
			username = (String) session25.getAttribute("useremail");
			employee2 = employeeService.findByUsername(username);
			employee2.setFull_name(new_name);
			employeeService.update(employee2);
			response.sendRedirect("http://18.219.228.37:8088/TRMS/managerHome.html");
			break;			
			


		case "/request/deny":
			HttpSession session6 = request.getSession(false);
			int r_id = Integer.parseInt(request.getParameter("id"));
			username = (String) session6.getAttribute("useremail");

			String manager_name = employeeService.findFullnameByUsername(username);
			int manager_id = managerService.findManagerIdByUsername(manager_name);

			Reimbursement reimbursement = reimbursementService.findById(r_id);
			reimbursement.setStatus("denied");
			reimbursement.setResolving_manager_id(manager_id);
			reimbursementService.update(reimbursement);
			response.sendRedirect("http://18.219.228.37:8088/TRMS/managerHome.html");
			break;

		case "/request/approve":
			HttpSession session7 = request.getSession(false);
			r_id = Integer.parseInt(request.getParameter("id"));
			username = (String) session7.getAttribute("useremail");

			manager_name = employeeService.findFullnameByUsername(username);
			manager_id = managerService.findManagerIdByUsername(manager_name);

			reimbursement = reimbursementService.findById(r_id);
			reimbursement.setStatus("approved");
			reimbursement.setResolving_manager_id(manager_id);

			reimbursementService.update(reimbursement);
			response.sendRedirect("http://18.219.228.37:8088/TRMS/managerHome.html");
			break;

		case "/login":
			final String email = request.getParameter("useremail");
			final String password = request.getParameter("userpass");

			if (employeeService.checkAccount(email, password)) {
				// If the user credentials are valid, I'll grant the client a session
				// and perhaps redirect the client to a new resource.


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
