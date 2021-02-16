package com.revature.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DispatcherServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	//private static final Logger LOG = LogManager.getLogger(DispatcherServlet.class);

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/*
		 * Under ordinary circumstances, you don't typically have entirely open
		 * endpoints. In other words, some resources are protected and clients must
		 * authenticate themselves in order to access those resources. One option we
		 * have for authentication is the HttpSession.
		 */

		//HttpSession session = null;

		//final String TOKEN = request.getParameter("token");

		//if (TOKEN != null) {
		//	session = request.getSession();
		//}
		//System.out.println(session);

		//if (session != null) {
		//	response.getWriter().write(RequestHelper.processGet(request, response));
		//} else {
		//	response.getWriter().write("Client not authorized.");
		//}

		response.getWriter().write(RequestHelper.processGet(request, response));
		
		// response.getWriter().write("Dispatcher is populating repsonse body");

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestHelper.processPost(request, response);

	}

}
