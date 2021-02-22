package com.revature.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Card;
import com.revature.model.Employee;

public class HelloWorldServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/*
	 * Recall the web container is responsible for managing the life cycle of a
	 * servlet. This means that from instantiation to the time that an instance of a
	 * servlet class is no longer needed (so it becomes eligible for garbage
	 * collection) that our servlet container handles all things related to our
	 * servlets.
	 */

	/*
	 * The servlet container calls the init method after it creates an instance of
	 * our Servlet class. Your web container decides that an instance of a servlet
	 * class is needed when the url pattern for a servlet that is mapped matches
	 * that of the request URL. This method is once.
	 */
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	}

	/*
	 * The servlet container calls the service method as many times as need be. If
	 * there is already a working instance of our servlet class, the web container
	 * can invoke that servlet's service method. During the service method, handler
	 * methods such as doGet, doPost, etc. are called.
	 */
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(req, resp);
	}

	/*
	 * Our web container calls the destroy method once. This method may be called
	 * when the servlet container has been idle for some time or if the server shuts
	 * down.
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

	/*
	 * This method is a "handler method". It handles HTTP Get requests. If there is
	 * no handler method defined for an HTTP verb, the client will get a 400-level
	 * status code back from the server specifying that the method the client has
	 * used is not allowed.
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter writer = response.getWriter();

		System.out.println("GET REQUEST HELLO SERVLET");

		/*
		 * Thus far, we've only written plain text to the client, but we have several
		 * options for the content type which we write to our response body. For
		 * example, we may choose to write:
		 * 
		 * XML JSON (application/json) HTML (text/html) Plain Text (text/plain)
		 */

		response.setContentType("application/json");
		// response.setContentType("text/plain");
		/*
		 * Because our content type is JSON, we have to make sure that we format the
		 * data to the response as valid JSON. As a note about JSON, JSON stands for
		 * JavaScript Object Notation. It is a standard for interchange format. It is
		 * considered easy to parse and it is language-independent.
		 * 
		 * We can format our own JSON, or we can use a third-party tool to handle the
		 * task for us. That is why we are using the Jackson Object Mapper, which will
		 * serialize our Java objects as JSON for us.
		 */

		// We'll send a list of cards back to the client.

		//List<Card> cards = new ArrayList<>();
		//cards.add(new Card(1, "Generic Monster", 1, false, new Date(3423)));
		//cards.add(new Card(2, "Jotaro's Stand", 2, true, new Date(334424234)));
		//cards.add(new Card(3, "Hermit Purple", 1, false, new Date(44423)));

		ObjectMapper objectMapper = new ObjectMapper();

		//final String JSON = objectMapper.writeValueAsString(cards);

		//writer.write(JSON);
		// writer.write("Jello World");
		// writer.println("Jello World");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		response.setContentType("application/json");
		// response.setContentType("text/plain");
		/*
		 * Because our content type is JSON, we have to make sure that we format the
		 * data to the response as valid JSON. As a note about JSON, JSON stands for
		 * JavaScript Object Notation. It is a standard for interchange format. It is
		 * considered easy to parse and it is language-independent.
		 * 
		 * We can format our own JSON, or we can use a third-party tool to handle the
		 * task for us. That is why we are using the Jackson Object Mapper, which will
		 * serialize our Java objects as JSON for us.
		 */

		// We'll send a list of cards back to the client.
		
		//ObjectMapper mapper = new ObjectMapper();
		//xsEmployee e = mapper.readValues(request, Employee.class);
		System.out.println(request);
		
		
		System.out.println(request.getParameter("username"));
		System.out.println(request.getParameter("password"));

		//List<Card> cards = new ArrayList<>();
		//cards.add(new Card(1, "Generic Monster", 1, false, new Date(3423)));
		//cards.add(new Card(2, "Jotaro's Stand", 2, true, new Date(334424234)));
		//cards.add(new Card(3, "Hermit Purple", 1, false, new Date(44423)));

		ObjectMapper objectMapper = new ObjectMapper();

		//final String JSON = objectMapper.writeValueAsString(cards);

		//writer.write(JSON);
		// writer.write("Jello World");
		// writer.println("Jello World");
	}
}


