package com.revature.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DispatcherServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	// private static final Logger LOG =
	// LogManager.getLogger(DispatcherServlet.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ObjectMapper om = new ObjectMapper();
		PrintWriter writer = response.getWriter();
		final String JSON = om.writeValueAsString(RequestHelper.processGet(request, response));
		writer.write(JSON);

		/*
		 * This line of code does the same thing as the four lines of code above.
		 */
//		response.getWriter().write(new ObjectMapper().writeValueAsString(RequestHelper.processGet(request, response)));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestHelper.processPost(request, response);
	}

}
