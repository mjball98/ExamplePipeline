package com.revature.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Employee;
import com.revature.service.EmployeeService;

public class HelloWorldServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static EmployeeService employeeService = new EmployeeService();

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(req, resp);
	}


	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		Employee e2 = new Employee();
		e2 = employeeService.findByEmail((String) session.getAttribute("useremail"));
		
		PrintWriter writer = response.getWriter();
		response.setContentType("application/json");
		ObjectMapper objectMapper = new ObjectMapper();

		final String JSON = objectMapper.writeValueAsString(e2);
		writer.write(JSON);
	}
	
	
}
