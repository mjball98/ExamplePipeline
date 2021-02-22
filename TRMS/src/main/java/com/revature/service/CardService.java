package com.revature.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.model.Card;
import com.revature.repository.CardRepository;
import com.revature.repository.CardRepositoryImpl;

/*
 * Our service layer is reserved for our business logic. This makes it easier
 * to debug issues that may arise in our application. Typically, all data is passed
 * through the service layer of the application.
 */
public class CardService {

	private CardRepository cardRepository;

	public CardService() {
		cardRepository = new CardRepositoryImpl();
	}

	public void insert(Card c) {
		this.cardRepository.insert(c);
	}

	public void delete(Card c) {
		this.cardRepository.delete(c);
	}

	public List<Card> findAll() {
		return this.cardRepository.findAll();
	}
}
