package com.revature.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.model.Card;
import com.revature.service.CardService;

public class RequestHelper {

	private static CardService cardService = new CardService();

	public static String processGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*
		 * We can use our HttpServletRequest to access information about the HTTP
		 * request.
		 */
		final String URI = request.getRequestURI();
		final String RESOURCE = URI.replace("/TRMS/dispatcher", "");

		switch (RESOURCE) {
		case "/hello":
			return "Hello there! I'm a Java app!";
		case "/goodbye":
			return "See ya! Come back soon!";
		default:
			return "That is not a supported operation!";
		}

	}

	public static void processPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		final String URI = request.getRequestURI();
		final String RESOURCE = URI.replace("/TRMS/dispatcher", "");

		switch (RESOURCE) {
		case "/card/new":
			/*
			 * As this is a handler method for Http Post requests, I'm expecting the client
			 * to send data with the request. How do I access this data?
			 */
			final String cardname = request.getParameter("cardname");
			final int typeid = Integer.parseInt(request.getParameter("typeid"));
			final boolean faceup = Boolean.parseBoolean(request.getParameter("faceup"));

			Card newCard = new Card(1, cardname, typeid, faceup, new Date(3333));

			cardService.insert(newCard);

			break;

		default:
			response.setStatus(404);
			response.getWriter().write("Operation not supported.");
		}
	}
}
