package com.revature;

import com.revature.service.CardService;

public class Driver {

	public static void main(String[] args) {

		/*
		 * Note that we do not ordinarily call methods that are present on our
		 * repository directly. We usually pass this data through our service layer.
		 * This means that we should be using our CardService in this layer - NOT our
		 * CardRepository.
		 */

		CardService cardService = new CardService();
//		Card toBeInserted = new Card(0, "Waffle Monster", 1, false, new Date(88888));
//		cri.insert(toBeInserted);

//		System.out.println(cardService.findAll());
		cardService.findAllOmittingType(3);

		// SQL Injection
//		String userInput = "Fire Ant'; drop table card cascade --";
//		System.out.println(cri.findByName(userInput));
	}
}
